package fun.peri;


import org.junit.jupiter.api.Test;

import java.io.*;

public class think {

    private static final String CHARSET = "utf-8";

    @Test
    public void TestOne() {
        File file = new File("D:" + File.separator + "think.txt");
        boolean result = false;
        try {
            if (!file.exists()) {
                result = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            String server = "javaServer";
            String app = "app";
            String weChat = "weChat";
            int i = 20;
            int j = server.length();
            int k = app.length();
            int m, d, f;
            m = d = f = i + j / 2;
            int n = 10;
            for (int b = 0; b < i; b++) {
                stringBuffer.append(" ");
            }
            stringBuffer.append(server);
            stringBuffer.append('\n');
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m - 1; b++) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append("/");
                for (int b = m; b < d + 1; b++) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append("\\");
                stringBuffer.append("\n");
                m--;
                d++;
            }
            for (int c = 0; c < m - k / 2; c++) {
                stringBuffer.append(" ");
            }
            stringBuffer.append(app);
            for (int a = m + k; a < 2 * f - m - 1; a++) {
                stringBuffer.append("-");
            }
            stringBuffer.append(weChat);
            String data = "";
            try {
                data = new String(stringBuffer.toString().getBytes(), CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(data);
            int index = 0;
            int length = 1024;
            String buffer;
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                while (index < data.length()) {
                    if (data.length() < length) {
                        buffer = data.substring(index, data.length());
                    } else {
                        buffer = data.substring(index, index + length);
                    }
                    index = index + length;
                    outputStream.write(buffer.getBytes(CHARSET));
                }
            } catch (FileNotFoundException e) {
                System.out.println("io error, file not exist");
            } catch (IOException e) {
                System.out.println("io error");
            } finally {
                if (null != outputStream) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (result) {
                System.out.println("io error, cat't find file:" + file.getName() + ", and file can't be create");
            } else {
                System.out.println("io error, file not exist");
            }
        }
    }

}
