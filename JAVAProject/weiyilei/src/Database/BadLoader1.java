package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BadLoader1 {
    private static URL        propertyURL = BadLoader1.class
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
      } catch (SQLException e) {
        System.err.println("Database connection failed");
        System.err.println(e.getMessage());
        System.exit(1);
      }
      try {
        stmt = con.prepareStatement("insert into student(student_id,first_name,surname,gender,academy)" + " values(?,?,?,?,?)");
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

    private static void loadData(String student_id, String first_name,String surname,String gender,String academy)
                  throws SQLException {
        if (con != null) {
            stmt.setString(1, student_id);
            stmt.setString(2, first_name);
            stmt.setString(3,surname);
            stmt.setString(4,gender);
            stmt.setString(5,academy);
          stmt.executeUpdate();
        }
    }

    public static boolean iscontainchinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
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
                       System.err.println("Usage: java [-v] BadLoader filename");
                       System.exit(1);
                }
                fileName = args[1];
                break;
           default:
                System.err.println("Usage: java [-v] BadLoader filename");
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
            String   first_name = "";
            String   surname = "";
            String   gender;
            String   academy;
            String   name;
            long      cnt = 0;
           // Empty target table
           openDB(prop.getProperty("host"), prop.getProperty("database"),
                  prop.getProperty("user"), prop.getProperty("password"));
           Statement stmt0;
           if (con != null) {
             stmt0 = con.createStatement();
             stmt0.execute("truncate table student");
             stmt0.close();
           }
           closeDB();
           //
           start = System.currentTimeMillis();
           openDB(prop.getProperty("host"), prop.getProperty("database"),
                  prop.getProperty("user"), prop.getProperty("password"));
           while ((line = infile.readLine()) != null) {
             parts = line.split(",");
             if (parts.length > 1) {
                 name = parts[0];
                 if( !iscontainchinese(name) )  {
                     if(name.contains(" ")){
                         first_name = name.substring(0,name.indexOf(" "));
                         surname = name.substring(name.indexOf(" ") + 1);
                     }
                     else{
                         first_name = "";
                         surname = name;
                     }
                 }
                 else{
                     if(name.length() == 1) {
                         surname = "";
                         first_name = name;
                     }
                     else if(name.length() == 2 || name.length() == 3){
                         surname = name.substring(0,1);
                         first_name = name.substring(1);
                     }
                     else if(name.length() == 4){
                         surname = name.substring(0,2);
                         first_name = name.substring(2);
                     }
                 }
                 gender = parts[1];
                 academy = parts[2];
                 student_id = parts[3];

                 loadData(student_id, first_name,surname,gender,academy);
                cnt++;
             }
           }
           closeDB();
           end = System.currentTimeMillis();
           System.out.println(cnt + " records successfully loaded");
           System.out.println("Loading speed : " 
                               + (cnt * 1000)/(end - start)
                               + " records/s");
        } catch (SQLException se) {
           System.err.println("SQL error: " + se.getMessage());
           closeDB();
           System.exit(1);
        } catch (IOException e) {
           System.err.println("Fatal error: " + e.getMessage());
           closeDB();
           System.exit(1);
        }
        closeDB();
    }
}

