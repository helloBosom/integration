package fun.peri.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Picture {

    public static void main(String[] args) {
        // savePictureToDB();
        readPicture();
    }

    public static void savePictureToDB() {
        Connection connection = DBHelp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO tbl_picture(id,picturename,picturecontent) VALUES(?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "Water");
            File file = new File("src/Water.jpg");
            InputStream inpuStream = new FileInputStream(file);
            /*
             * setBinaryStream(int parameterIndex, InputStream x, int length)
             * 将指定参数设置为给定输入流，该输入流将拥有给定字节数。
             */
            ps.setBinaryStream(3, inpuStream, file.length());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(null, ps, connection);
        }
    }

    public static void readPicture() {
        Connection connection = DBHelp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,picturename,picturecontent FROM tbl_picture WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);

            rs = ps.executeQuery();
            if (rs.next()) {
                File f = new File("src" + File.separator + "water2.jpg");
                /*
                 * getBinaryStream(int columnIndex) 以未解释字节的二进制流的形式检索此 ResultSet对象的当前行中指定列的值。
                 * InputStream getBinaryStream(StringcolumnName) 以未解释的 byte 流的形式检索此 ResultSet
                 * 对象的当前行中指定列的值。
                 */
                InputStream in = rs.getBinaryStream(3);
                OutputStream out = new FileOutputStream(f);
                int temp = 0;
                while ((temp = in.read()) != -1) {
                    out.write(temp);
                }
                in.close();
                out.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(rs, ps, connection);
        }
    }
}