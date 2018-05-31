package com.logic.arithmetic.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

public class FileUtil {

    static Properties properties = new Properties();
    static ClassLoader loader = FileUtil.class.getClassLoader();
    static InputStream inputStream = loader.getResourceAsStream("");

    static {
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String path = properties.getProperty("");
    static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static File createFile(String path, String fileName) {
        String absoluteLocation = path + File.separator + fileName;
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        File file1 = new File(absoluteLocation);
        if (!file1.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.info("make file:" + file1 + " fail");
                e.printStackTrace();
            }
        }
        return file1;
    }

    public static File searchFile(String path, String fileName) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            logger.error("not a directory");
        } else {
            File[] files = dir.listFiles();
            for (File file1 : files) {
                if (file1.getName().equals(fileName)) {
                    return file1;
                }
            }
        }
        return null;
    }

    public static String readFile(File file) {
        StringBuffer stringBuffer = new StringBuffer();
        String fileContent = null;
        try {
//            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            FileLock fileLock = fileChannel.tryLock();
//            if (fileLock.isValid())
//                System.out.println("file lock");
            if (!file.exists()) {
                logger.error("no digitalIdentity file");
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuffer.append(temp);
            }
            fileContent = stringBuffer.toString();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    public static boolean fileExist(String path, String fileName) {
        String absoluteLocation = path + File.separator + fileName;
        File file = new File(absoluteLocation);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static void writeFile(File file, String digital) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(digital);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] AES_Encrypt(byte[] content, byte[] key, byte[] ivParam) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(key));
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivParam));
            byte[] encrypt = cipher.doFinal(content);
            return encrypt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] AES_Decrypt(byte[] content, byte[] key, byte[] ivParam) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(key));
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivParam));
            byte[] decrypt = cipher.doFinal(content);
            return decrypt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer(bytes.length);
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(0xFF & bytes[i]);
            if (temp.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(temp.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static String encrypt(String s) {
        String key = "unix";
        String ivParam = "myheartwillgoon!";
        try {
            return byteToHexString(AES_Encrypt(s.getBytes("utf-8"), key.getBytes("utf-8"), ivParam.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
