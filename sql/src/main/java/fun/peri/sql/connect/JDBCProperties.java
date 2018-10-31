package fun.peri.sql.connect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JDBCProperties {
    public static Properties getProperties(String pathname) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(pathname)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
