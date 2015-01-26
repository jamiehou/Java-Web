package com.ioryz.login.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static Properties p = null;

    static {
        InputStream is = null;
        try {
            is = PropertyUtil.class.getClassLoader().getResourceAsStream("app.properties");
            p = new Properties();
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getPropertyByName(String name) {
        return p.getProperty(name);
    }

    public static void main(String[] args) {
        System.out.println(PropertyUtil.getPropertyByName("jdbc.driver"));
    }
}
