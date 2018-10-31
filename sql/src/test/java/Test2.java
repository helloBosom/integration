import fun.peri.dao.dbhelp.DBHelp;

import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBHelp.getConnection();
            conn.setAutoCommit(false);
            System.out.println(conn.toString());
            String sql = "select filename,FILECONTENT from tbl_filecontent where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            if (rs.next()) {
                StringBuffer sb = new StringBuffer();
                InputStream input = rs.getAsciiStream(2);
                Scanner scan = new Scanner(input);
                /*
                 * delimiter() 返回此 Scanner 当前正在用于匹配分隔符的 Pattern。
                 * useDelimiter(Pattern pattern) 将此扫描器的分隔模式设置为指定模式。
                 * Scanner类useDelimiter(String pattern) 将此扫描器的分隔模式设置为从指定 String
                 * 构造的模式。
                 * public final class Patternextends ObjectimplementsSerializable正则表达式的编译表示形式。
                 * 指定为字符串的正则表达式必须首先被编译为此类的实例。然后，可将得到的模式用于创建 Matcher
                 * 对象，依照正则表达式，该对象可以与任意字符序列匹配。执行匹配所涉及的所有状态都驻留在匹配器中，
                 * 所以多个匹配器可以共享同一模式。 因此，典型的调用顺序是
                 *  Pattern p = Pattern.compile("a*b");
                 *  Matcher m = p.matcher("aaaaab");
                 * boolean b = m.matches();
                 */
                scan.useDelimiter("\r\n");
                while (scan.hasNext()) {
                    sb.append(scan.next()).append("\n");
                }
                System.out.println(sb);
                input.close();
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
    }

    public static void closeAll(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
