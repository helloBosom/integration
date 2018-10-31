package fun.peri.dao;

import fun.peri.dao.dbhelp.DBHelp;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOperation {

    public static void main(String[] args) {
        // saveFileContentToDb();
        readFileContentFromDb();
    }

    public static void saveFileContentToDb() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBHelp.getConnection();
            String sql = "INSERT INTO tbl_filecontent(id,filename,filecontent) VALUES (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "store_schema.sql");
            File file = new File("store_schema.sql");
            InputStream in = new FileInputStream(file);
            /*
             * PreparedStatement.setAsciiStream(int parameterIndex, InputStreamx, int
             * length) 将指定参数设置为给定输入流，该输入流将拥有给定字节数。
             */
            ps.setAsciiStream(3, in, (int) file.length());
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(null, ps, connection);
        }
    }

    public static void readFileContentFromDb() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBHelp.getConnection();
            String sql = "SELECT * FROM tbl_filecontent WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            if (rs.next()) {
                File f = new File("src" + File.separator + "store_schema2.sql");
                /*
                 * getAsciiStream(int columnIndex) 以 ASCII 字符流的形式检索此 ResultSet 对象的当前行中指定列的值。
                 * InputStream getAsciiStream(String columnName) 以 ASCII 字符流的形式检索此 ResultSet
                 * 对象的当前行中指定列的值。
                 */
                InputStream in = rs.getAsciiStream(3);
                OutputStream out = new FileOutputStream(f);
                int temp = 0;
                /*
                 * 从输入流读取下一个数据字节。返回 0 到 255 范围内的 int 字节值。 如果因已到达流末尾而没有可用的字节，则返回值-1。
                 * 在输入数据可用、检测到流的末尾或者抛出异常前，此方法一直阻塞。
                 */
                while ((temp = in.read()) != -1) {
                    out.write(temp);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(rs, ps, connection);
        }
    }
}