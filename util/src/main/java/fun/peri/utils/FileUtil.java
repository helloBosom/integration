package fun.peri.utils;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class FileUtil {
    // ~ Static fields/initializers
    // ---------------------------------------------

    /**
     * 打印日志
     */
    public static final Logger Log = Logger.getLogger(FileUtil.class);

    // ~ Methods
    // ----------------------------------------------------------------

    /**
     * 创建文件
     *
     * @param filename 文件名称(全路径)
     * @return 是否成功
     */
    public static boolean createFile(String filename) {
        File f = new File(filename);
        boolean res = false;
        if(!f.exists()) {
            try {
                res = f.createNewFile();
                if(!res) {
                    Log.error("[method: createFile] [filename: " + filename + "] createNewFile fail!");
                }
            } catch(IOException e) {
                Log.error("[method: createFile] [filename: " + filename + "] exception ", e);
            }
        } else if(f.isFile()) {
            res = true;
        } else {
            Log.error("[method: createFile] [filename: " + filename + "] not a file!");
        }

        return res;
    }

    /**
     * 创建文件
     *
     * @param filename 文件名称(全路径)
     * @return 文件对象
     */
    public static File createNewFile(String filename) {
        if(createFile(filename)) {
            return new File(filename);
        } else {
            return null;
        }
    }

    /**
     * 创建目录
     *
     * @param folderPath 目录名称(全路径)
     * @return 是否成功
     */
    public static boolean createFolder(String folderPath) {
        File f = new File(folderPath);
        boolean res = false;
        if(!f.exists()) {
            res = f.mkdirs();
            if(!res) {
                Log.error("[method: createFolder] [folderPath: " + folderPath + "] mkdirs fail");
            }
        } else if(!f.isDirectory()) {
            Log.error("[method: createFolder] [folderPath: " + folderPath + "] not a directory!");
            res = false;
        } else {
            res = true;
        }

        return res;
    }

    /**
     * 批量删除文件
     *
     * @param files 文件路径的集合
     * @return 是否删除成功
     */
    public static boolean deleteFiles(List<String> files) {
        boolean result = false;
        if((files != null) && (files.size() > 0)) {
            for(String path : files) {
                File file = new File(path);
                if(file.exists()) {
                    file.delete();
                }
            }

            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        File file = new File("D:\\res\\download\\netdisk\\8355366");
        FileUtil.delFileList(file);
    }

    /**
     * 删除文件或者文件夹及其子文件
     */
    public static void delFileList(File file) {

        if(file.exists()) {
            if(file.isFile()) {
                file.delete();
            } else if(file.isDirectory()) {
                File[] files = file.listFiles();
                for(int i = 0; i < files.length; i++) {
                    delFileList(files[i]);
                }
            }

            file.delete();
        } else {
            Log.error("[method: delFileList] [file: " + file.getName() + " not exist");
        }
    }

    public static void compressExe(String srcPathName, File zipFile) {
        File file = new File(srcPathName);
        if(!file.exists()) {
            throw new RuntimeException(srcPathName + "不存在！");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compressByType(file, out, basedir);
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
            Log.error("[method: compressExe] [folderPath: " + srcPathName + "] compress fail");
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是目录还是文件，根据类型（文件/文件夹）执行不同的压缩方法
     *
     * @param file
     * @param out
     * @param basedir
     */
    private static void compressByType(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if(file.isDirectory()) {
            Log.info("[method: compressExe]" + basedir + file.getName());
            compressDirectory(file, out, basedir);
        } else {
            Log.info("[method: compressExe]" + basedir + file.getName());
            compressFile(file, out, basedir);
        }
    }

    /**
     * 压缩一个文件
     *
     * @param file
     * @param out
     * @param basedir
     */
    private static void compressFile(File file, ZipOutputStream out, String basedir) {
        if(!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[8192];
            while((count = bis.read(data, 0, 8192)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 压缩一个目录
     *
     * @param dir
     * @param out
     * @param basedir
     */
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if(!dir.exists()) {
            return;
        }

        File[] files = dir.listFiles();
        for(int i = 0; i < files.length; i++) {
            /* 递归 */
            compressByType(files[i], out, basedir + dir.getName() + "/");
        }
    }

    /**
     * 压缩文件
     *
     * @param files   文件路径的集合
     * @param zipname 压缩后的名称（全路径）
     * @return 是否压缩成功
     */
    public static boolean zipCompress(List<String> files, String zipname) {
        if((files == null) || (zipname == null) || (files.size() < 1)) {
            return false;
        }

        BufferedOutputStream out = null;
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipname));
            out = new BufferedOutputStream(zos);
            File file = null;
            FileInputStream fin = null;
            BufferedInputStream bis = null;
            for(String filepath : files) {
                try {
                    file = new File(filepath);
                    fin = new FileInputStream(file);
                    bis = new BufferedInputStream(fin);
                    zos.putNextEntry(new ZipEntry(file.getName()));

                    int c;
                    byte[] b = new byte[16 * 1024];
                    while((c = bis.read(b)) != -1) {
                        out.write(b, 0, c);
                    }
                } catch(IOException e) {
                    throw (e);
                } finally {
                    if(bis != null) {
                        bis.close();
                    }
                    out.flush();
                }
            }

            return true;
        } catch(FileNotFoundException e) {
            Log.error("[method: zipCompress] FileNotFoundException ", e);
        } catch(IOException e) {
            Log.error("[method: zipCompress] IOException ", e);
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch(IOException e) {
                    Log.error("[method: zipCompress] IOException ", e);
                }
            }
        }

        return false;
    }

    /**
     * 将字符串写入指定文件
     *
     * @param content 数据内容 (字符串不能过大)
     * @param content 文件名称(带路径)
     * @return 是否成功
     */
    public static boolean writeStr(String content, String filename) {
        if(createFile(filename)) {
            BufferedWriter bw = null;
            try {
                FileWriter fw = new FileWriter(filename);
                bw = new BufferedWriter(fw);
                bw.write(content);
                return true;
            } catch(IOException e) {
                Log.error("[method: writeStr] IOException ", e);
            } finally {
                if(bw != null) {
                    try {
                        bw.flush();
                        bw.close();
                    } catch(IOException e) {
                        Log.error("[method: writeStr] IOException ", e);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 将字符串用指定流输出
     *
     * @param content 数据内容 (字符串不能过大)
     * @param w       输出流
     * @throws IOException
     */
    public static void writeStr(String content, Writer w) throws IOException {
        w.write(content);
    }

    /**
     * 判断文件是否为指定后缀
     *
     * @param fileName 文件名
     * @param suffix   后缀名集合
     * @return 是否成功
     */
    public static boolean isSuffixAble(String fileName, String[] suffix) {
        boolean result = false;
        if(fileName != null) {
            int lastIndex = fileName.lastIndexOf(".");
            if(lastIndex != -1 && lastIndex < (fileName.length() - 1)) {
                String curSuffix = fileName.substring(lastIndex + 1);
                for(String temp : suffix) {
                    if(curSuffix.equalsIgnoreCase(temp)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 获取后缀名
     *
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getSuffix(String fileName) {
        String result = "";
        if(fileName != null) {
            int lastIndex = fileName.lastIndexOf(".");
            if(lastIndex != -1 && lastIndex < (fileName.length() - 1)) {
                result = fileName.substring(lastIndex + 1);
            }
        }
        return result;
    }

    /**
     * 从一个路径中截取文件名称
     *
     * @param path 路径
     * @return 文件名称
     */
    public static String getFileName(String path) {
        String result = "";
        if(path != null) {
            if(path.lastIndexOf("\\") != -1) {
                int lastIndex = path.lastIndexOf("\\");
                if(lastIndex < (path.length() - 1)) {
                    result = path.substring(lastIndex + 1);
                }
            } else if(path.lastIndexOf("/") != -1) {
                int lastIndex = path.lastIndexOf("/");
                if(lastIndex < (path.length() - 1)) {
                    result = path.substring(lastIndex + 1);
                }
            } else {
                result = path;
            }
        }

        return result;
    }

    /**
     * 获取已经存在的文件
     *
     * @param filename 文件名称(全路径)
     * @return 文件对象
     */
    public static File getExistFile(String filename) {
        File f = new File(filename);
        if(f.exists() && f.isFile()) {
            return f;
        } else {
            return null;
        }
    }

    /**
     * 方法：downloadFile
     * 描述：下载文件
     * 参数和返回值：
     *
     * @param uri 下载URI地址
     * @return byte[]
     */
    public static byte[] downloadFile(String uri) {

        URL url = null;
        HttpURLConnection urlConn = null;
        InputStream in = null;
        byte[] fileByte = null;

        if(uri.indexOf("tmpfile") != -1) {
            Log.info("downloadFile(uri) is illegal");
            return fileByte;
        }
        //打开连接、获取响应信息
        try {
            url = new URL(uri);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();
            in = urlConn.getInputStream();
            fileByte = readContent(in, urlConn.getContentLength());

        } catch(MalformedURLException e) {
            e.printStackTrace();
            Log.info("FileServerUtils.downloadFile() is failre,the reason is " + e.fillInStackTrace());
            fileByte = null;
        } catch(IOException e) {
            e.printStackTrace();
            Log.info("FileServerUtils.downloadFile() is failre,the reason is " + e.fillInStackTrace());
            fileByte = null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(urlConn != null) {
                urlConn.disconnect();
            }
        }
        return fileByte;
    }

    /**
     * 方法：readContent
     * 描述：读取流内容
     * 参数和返回值：
     *
     * @param in         输入流
     * @param bufferSize 字符大小
     * @return byte[]
     * @throws IOException 异常
     */
    public static byte[] readContent(final InputStream in, int bufferSize) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buf = new byte[bufferSize];
        int c = 0;
        int b = 0;
        while((c < buf.length) && (b = in.read(buf, c, buf.length - c)) >= 0) {
            c += b;
            if(c == bufferSize) {
                bout.write(buf);
                buf = new byte[bufferSize];
                c = 0;
            }
        }
        if(c != 0) {
            bout.write(buf, 0, c);
        }
        return bout.toByteArray();
    }
}
