package carsharing;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String dbName = "CarSharing_DB";
        if (args.length == 2 && args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }

        try (Connector conn = new Connector(dbName)) {

            conn.state("create table COMPANY (ID INT, NAME VARCHAR(255))");
            conn.state("insert into COMPANY (ID, NAME) values (1, 'Colruyt Group')");

            Thread.sleep(5_000);

            var result = conn.query("select * from COMPANY");
            result.next();
            System.out.println(result.getString("NAME"));
            System.out.println(result.getInt("ID"));

        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
        } catch (Exception ie) {
            System.out.println("InterruptedException: " + ie.getMessage());
        }
    }
}