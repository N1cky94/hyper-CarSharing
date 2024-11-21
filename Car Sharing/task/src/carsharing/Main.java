package carsharing;

import java.sql.SQLException;

public class Main {
    public static Connector connector;

    public static void main(String[] args) throws SQLException {
        String dbName = "CarSharing_DB";
        if (args.length == 2 && args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }

        try (Connector conn = new Connector(dbName)) {
            connector = conn;

        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void createCompanyTable() throws SQLException {
        connector.state("CREATE TABLE COMPANY(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255) UNIQUE NOT NULL)");
    }

    public static void insertCompany(int id, String name) throws SQLException {
        connector.state("INSERT INTO COMPANY(ID, NAME) VALUES(%d, '%s')".formatted(id, name));
    }

    public static void selectTestCompany() throws SQLException {
        var result = connector.query("select * from COMPANY");
        result.next();
        System.out.println(result.getString("NAME"));
        System.out.println(result.getInt("ID"));
    }
}