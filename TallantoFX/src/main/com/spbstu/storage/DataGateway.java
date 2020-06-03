
package main.com.spbstu.storage;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Statement;
import java.util.Scanner;

public class DataGateway {

    private static String JDBC_DRIVER = "org.postgresql.Driver";
    private static DataGateway dataGateway;
    private static PGSimpleDataSource dataSource;
    //костыль
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/talanto";
    static final String USER = "postgres";
    static final String PASS = "29evodem";

    private DataGateway() throws IOException {
        //Структура соединения с базой данных
        /*
        ConfigReader config = ConfigReader.getInstance();
        dataSource = new PGSimpleDataSource();
        dataSource.setURL(config.getDburl());
        dataSource.setUser(config.getDbuser());
        dataSource.setPassword(config.getDbpassword());
*/
        dataSource = new PGSimpleDataSource();
        dataSource.setURL(DB_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        /*
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException se) {
            se.printStackTrace();
        }*/

        //костыль
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }


    public static DataGateway getInstance() throws IOException {
        if(dataGateway == null)
            dataGateway = new DataGateway();
        return dataGateway;
    }

    public PGSimpleDataSource getDataSource() {
        return dataSource;
    }

    public void dropAll() throws SQLException {
        executeSqlScript(DriverManager.getConnection(DB_URL, USER, PASS), new File("C:/Users/Maddy_juliet/Desktop/Zzz/TallantoFX/db/db_create.sql"));
    }

    private void executeSqlScript(Connection conn, File inputFile) {

        // Delimiter
        String delimiter = ";";

        // Create scanner
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return;
        }

        // Loop through the SQL file statements
        Statement currentStatement = null;
        while(scanner.hasNext()) {

            // Get statement
            String rawStatement = scanner.next() + delimiter;
            try {
                // Execute statement
                currentStatement = conn.createStatement();
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
        scanner.close();
    }

}