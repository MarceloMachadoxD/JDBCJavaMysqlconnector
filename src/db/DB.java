package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn==null){
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                conn = DriverManager.getConnection(url, properties);
            }catch (SQLException e){

                throw new DbException(e.getMessage());
            }

        }

        return conn;
    }

    public static void closeConnection(){
        try {
            if (conn != null){
                conn.close();
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }


    private static Properties loadProperties(){

        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);

            return props;
        } catch (IOException e ){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if (st == null) {

        } else {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs == null) {

        } else {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
