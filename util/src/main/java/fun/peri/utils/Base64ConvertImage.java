package fun.peri.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Base64;

public class Base64ConvertImage {

    /**
     * 图片转化成base64字符串
     */
    public static String imageToBase64(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        //返回Base64编码过的字节数组字符串
        return encoder.encodeToString(data);
    }

    /**
     * base64字符串转化成图片
     */
    public static boolean base64ToImage(String imgStr, String imgFilePath) {
        //对字节数组字符串进行Base64解码并生成图片
        //图像数据为空
        if (imgStr == null) {
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            //Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 图片转字符串
     *
     * @return
     */
    public static String imageToBase64(String imagePath, String imageType) {
        Base64.Encoder encoder = Base64.getEncoder();
        File f = new File(imagePath);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bi, imageType, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return encoder.encodeToString(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转图片
     *
     * @param base64String
     */
    public static boolean base64ToImage(String base64String, String toImagePath, String imageType) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes1 = decoder.decode(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            RenderedImage bi1 = ImageIO.read(bais);
            // 可以是jpg,png,gif格式
            File w2 = new File(toImagePath);
            if (!w2.exists()) {
                System.out.println("can't find file:" + w2);
            }
            // 不管输出什么格式图片，此处不需改动
            return ImageIO.write(bi1, imageType, w2);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
