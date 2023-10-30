package com.emsi.javafx.dao.implem;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public DB() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException var2) {
                System.err.println("Erreur d'ouverture de connexion");
            }
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException var1) {
                System.err.println("Erreur de fermeture de connexion");
            }
        }

    }

    private static Properties loadProperties() {
        try {
            Throwable var0 = null;
            Object var1 = null;

            try {
                FileInputStream fs = new FileInputStream("src/main/resources/db.properties");

                Properties var10000;
                try {
                    Properties props = new Properties();
                    props.load(fs);
                    var10000 = props;
                } finally {
                    if (fs != null) {
                        fs.close();
                    }

                }

                return var10000;
            } catch (Throwable var11) {
                if (var0 == null) {
                    var0 = var11;
                } else if (var0 != var11) {
                    var0.addSuppressed(var11);
                }

                throw var0;
            }
        } catch (Throwable var12) {
            System.err.println("Erreur de chargement de proriétés");
            return null;
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException var2) {
                System.err.println("Erreur de fermeture de Statement");
            }
        }

    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException var2) {
                System.err.println("Erreur de fermeture de ResultSet");
            }
        }

    }
}
