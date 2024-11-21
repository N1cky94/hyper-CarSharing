package carsharing;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String dbName = "CarSharing_DB";
        if (args.length == 2 && args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }

        String dbUrl = "jdbc:h2:./src/carsharing/db/%s".formatted(dbName);

        JdbcDataSource source = new JdbcDataSource();
        source.setURL(dbUrl);

        try (Connection conn = source.getConnection()) {

            String sql = "CREATE TABLE COMPANY (" +
                    "ID INT, " +
                    "NAME VARCHAR(255));";

            conn.prepareStatement(sql).execute();
            conn.prepareStatement("insert into COMPANY (ID, NAME) values (1, 'Colruyt Group')").execute();

            Thread.sleep(10_000);

            var result = conn.prepareStatement("select * from COMPANY").executeQuery();
            result.next();
            System.out.println(result.getString("NAME"));
            System.out.println(result.getInt("ID"));

        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
        } catch (InterruptedException ie) {
            System.out.println("InterruptedException: " + ie.getMessage());
        }
    }
}