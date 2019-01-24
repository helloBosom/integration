package fun.peri.arithmetic.util;

import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.ObjectPool;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FtpUtil {

    private static final Logger log = Logger.getLogger(FtpUtil.class);

    /**
     * ftpClient连接池初始化标志
     */
    private static volatile boolean hasInit = false;
    /**
     * ftpClient连接池
     */
    private static ObjectPool<FTPClient> ftpClientPool;

    /**
     * 初始化ftpClientPool
     *
     * @param ftpClientPool
     */
    public static void init(ObjectPool<FTPClient> ftpClientPool) {
        if (!hasInit) {
            synchronized (FtpUtil.class) {
                if (!hasInit) {
                    FtpUtil.ftpClientPool = ftpClientPool;
                    hasInit = true;
                }
            }
        }
    }

    /**
     * 获取ftpClient
     *
     * @return
     */
    private static FTPClient getFtpClient() {
        checkFtpClientPoolAvailable();
        FTPClient ftpClient = null;
        Exception ex = null;
        // 获取连接最多尝试3次
        for (int i = 0; i < 3; i++) {
            try {
                ftpClient = ftpClientPool.borrowObject();
                ftpClient.changeWorkingDirectory("/");
                break;
            } catch (Exception e) {
                ex = e;
            }
        }
        if (ftpClient == null) {
            throw new RuntimeException("Could not get a ftpClient from the pool", ex);
        }
        return ftpClient;
    }

    /**
     * 释放ftpClient
     */
    private static void releaseFtpClient(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }

        try {
            ftpClientPool.returnObject(ftpClient);
        } catch (Exception e) {
            log.error("Could not return the ftpClient to the pool", e);
            // destoryFtpClient
            if (ftpClient.isAvailable()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException io) {
                }
            }
        }
    }

    /**
     * 获取指定路径下FTP文件名称
     *
     * @param remotePath 路径
     * @return ftp文件名称列表
     * @throws IOException
     */
    public static List<String> retrieveFileNames(String remotePath) throws IOException {
        List<FTPFile> ftpFiles = retrieveFTPFiles(remotePath);
        if (CollectionUtils.isEmpty(ftpFiles)) {
            return new ArrayList<String>();
        }
        List<String> fileNameList = new ArrayList<String>();
        for (FTPFile file : ftpFiles) {
            if (file != null) {
                fileNameList.add(file.getName());
            }
        }

        return fileNameList;
    }

    /**
     * 获取指定路径下FTP文件
     *
     * @param remotePath 路径
     * @return FTPFile数组
     * @throws IOException
     */
    public static List<FTPFile> retrieveFTPFiles(String remotePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        List<FTPFile> rtnList = new ArrayList<FTPFile>();
        try {
            FTPFile[] files = ftpClient.listFiles(encodingPath(remotePath + "/"));
            for (FTPFile file : files) {
                if (file != null && file.isDirectory()) {
                    rtnList.addAll(retrieveFTPFiles(remotePath + "/" + file.getName()));
                } else {
                    if (file != null && file.getSize() > 0) {
                        rtnList.add(file);
                    }
                }
            }
            return rtnList;
        } finally {
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 获取指定路径下FTP文件
     *
     * @param remotePath 路径
     * @return FTPFile数组
     * @throws IOException
     */
    public static List<JSONObject> retrieveFTPFilesPath(String remotePath) throws IOException {
        FTPClient ftpClient = getFtpClient();
        List<JSONObject> rtnList = new ArrayList<JSONObject>();
        JSONObject obj = null;
        Long time = null;
        try {
            FTPFile[] files = ftpClient.listFiles(encodingPath(remotePath + "/"));
            for (FTPFile file : files) {
                obj = new JSONObject();
                time = file.getTimestamp().getTimeInMillis();
                if (file != null && file.isDirectory()) {
                    rtnList.addAll(retrieveFTPFilesPath(remotePath + "/" + file.getName()));
                } else {
                    if (file != null && file.getSize() > 0) {
                        obj.put("path", remotePath + "/" + file.getName());
                        obj.put("time", time);
                        rtnList.add(obj);
                    }
                }
            }
            return rtnList;
        } finally {
            releaseFtpClient(ftpClient);
        }
    }

    /**
     * 检查ftpClientPool是否可用
     */
    private static void checkFtpClientPoolAvailable() {
        Assert.state(hasInit, "FTP未启用或连接失败！");
    }

    /**
     * 编码文件路径
     */
    private static String encodingPath(String path) throws UnsupportedEncodingException {
        // FTP协议里面，规定文件名编码为iso-8859-1，所以目录名或文件名需要转码
        return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
    }

    private static boolean deleteFile(String filePath) {
        FTPClient ftpClient = getFtpClient();
        boolean ret = true;
        try {
            ftpClient.deleteFile(filePath);
        } catch (IOException e) {
            log.error(e.toString());
            ret = false;
        } finally {
            releaseFtpClient(ftpClient);
            return ret;
        }
    }

    /**
     * 下载文件
     */
    public static boolean downFile(String remoteFilePath, String localPath) throws IOException {
        String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
        FTPClient ftpClient = getFtpClient();
        String filePath = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("/"));
        boolean ret = true;
        OutputStream out = null;
        try {
            InputStream in = ftpClient.retrieveFileStream(encodingPath(remoteFilePath));
            File file = new File(localPath + filePath + "/" + fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file);
            int size = 0;
            byte[] buf = new byte[10240];
            while ((size = in.read(buf)) > 0) {
                out.write(buf, 0, size);
                out.flush();
            }
        } catch (IOException e) {
            log.error(e.toString());
            ret = false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1) {
            }
            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
            return ret;
        }
    }

    /**
     * 下载文件
     */
    public static boolean downFileAndDelete(String remoteFilePath, String localPath) throws IOException {
        boolean ret = true;
        if (remoteFilePath.contains("Face") && !remoteFilePath.contains("TG") && !remoteFilePath.contains(".tmp")) {
            ret = downFile(remoteFilePath, localPath);
        }
        if (ret) {
            ret = deleteFile(remoteFilePath);
        }
        return ret;
    }

    public static JSONObject fdfsUpload(File file, String fileUrl, String deviceId, FastFileStorageClient storageClient) {
        JSONObject resultJson = new JSONObject();

        // 上传文件服务器
        String fileName = file.getName();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            StorePath storePath = storageClient.uploadFile(is, file.length(),
                    FilenameUtils.getExtension(fileName), null);

            if (storePath.getFullPath().length() > 1) {
                JSONObject data = new JSONObject();
                String path = storePath.getFullPath();
                data.put("deviceId", deviceId);
                data.put("url", fileUrl + "/" + path);
                JSONObject ftpDbObj = new JSONObject();
                ftpDbObj.put("path", storePath.getPath());
                ftpDbObj.put("group", storePath.getGroup());
                ftpDbObj.put("imgurl", storePath.getFullPath());
            } else {

            }
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e1) {
            }
        }
        return resultJson;
    }
}
