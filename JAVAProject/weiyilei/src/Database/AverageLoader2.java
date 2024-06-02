package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.sql.*;
import java.net.URL;

public class AverageLoader2 {
    private static URL        propertyURL = AverageLoader2.class
            .getResource("/loader.cnf");

    private static Connection         con = null;
    private static PreparedStatement  stmt = null;
    private static boolean            verbose = false;

    private static void openDB(String host, String dbname,
                               String user, String pwd) {
        try {
            //
            Class.forName("org.postgresql.Driver");
        } catch(Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:postgresql://" + host + "/" + dbname;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        try {
            con = DriverManager.getConnection(url, props);
            if (verbose) {
                System.out.println("Successfully connected to the database "
                        + dbname + " as " + user);
            }
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            stmt = con.prepareStatement("insert into studying(student_id,course_id)" + " values(?,?)");
        } catch (SQLException e) {
            System.err.println("Insert statement failed");
            System.err.println(e.getMessage());
            closeDB();
            System.exit(1);
        }
    }

    private static void closeDB() {
        if (con != null) {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                con.close();
                con = null;
            } catch (Exception e) {
                // Forget about it
            }
        }
    }

    private static void loadData(String student_id, String course_id)
            throws SQLException {
        if (con != null) {
            stmt.setString(1, student_id);
            stmt.setString(2, course_id);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        String  fileName = null;
        boolean verbose = false;

        switch (args.length) {
            case 1:
                fileName = args[0];
                break;
            case 2:
                switch (args[0]) {
                    case "-v":
                        verbose = true;
                        break;
                    default:
                        System.err.println("Usage: java [-v] AverageLoader filename");
                        System.exit(1);
                }
                fileName = args[1];
                break;
            default:
                System.err.println("Usage: java [-v] AverageLoader filename");
                System.exit(1);
        }

        /*if (propertyURL == null) {
           System.err.println("No configuration file (loader.cnf) found");
           System.exit(1);
        }*/
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "checker");
        defprop.put("password", "123456");
        defprop.put("database", "postgres");
        Properties prop = new Properties(defprop);
        /*try (BufferedReader conf
                = new BufferedReader(new FileReader(propertyURL.getPath()))) {
          prop.load(conf);
        } catch (IOException e) {
           // Ignore
           System.err.println("No configuration file (loader.cnf) found");
        }*/
        try (BufferedReader infile
                     = new BufferedReader(new FileReader(fileName))) {
            long     start;
            long     end;
            String   line;
            String[] parts;
            String   student_id;
            String   course_id;
            long      cnt = 0;
            int n = 0;
            Map<String,Integer> map = new HashMap<>();
            // Empty target table
            openDB(prop.getProperty("host"), prop.getProperty("database"),
                    prop.getProperty("user"), prop.getProperty("password"));
            Statement stmt0;
            if (con != null) {
                stmt0 = con.createStatement();
                //stmt0.execute("truncate table student");
                stmt0.close();
            }
            closeDB();
            //
            start = System.currentTimeMillis();
            openDB(prop.getProperty("host"), prop.getProperty("database"),
                    prop.getProperty("user"), prop.getProperty("password"));
            while ((line = infile.readLine()) != null) {
                n++;
                System.out.println(n);
                parts = line.split(",");
                if (parts.length == 4) {
                    student_id = parts[3];
                    loadData(student_id, "null");
                    cnt++;
                }
                else if(parts.length > 4){
                    student_id = parts[3];
                    map.clear();
                    int number = parts.length - 4;
                    int now = 4;
                    int i = 1;
                    while(number > 0){
                        number--;
                        course_id = parts[now];
                        if(map.get(course_id) == null){
                            loadData(student_id,course_id);
                            cnt++;
                            map.put(course_id,i);
                            now++;
                            i++;
                        }
                        else{
                            now++;
                        }
                    }
                }
            }
            con.commit();
            closeDB();
            end = System.currentTimeMillis();
            System.out.println(cnt + " records successfully loaded");
            System.out.println("Loading speed : "
                    + (cnt * 1000)/(end - start)
                    + " records/s");
        } catch (SQLException se) {
            System.err.println("SQL error: " + se.getMessage());
            try {
                con.rollback();
            } catch (Exception e2) {
            }
            closeDB();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Fatal error: " + e.getMessage());
            try {
                con.rollback();
            } catch (Exception e2) {
            }
            closeDB();
            System.exit(1);
        }
        closeDB();
    }
}

