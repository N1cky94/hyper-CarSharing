package carsharing;

import carsharing.db.Connector;
import carsharing.db.SqlRuntimeException;
import carsharing.menus.MainMenu;
import carsharing.services.CompanyService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String dbName = "CarSharing_DB";
        if (args.length == 2 && args[0].equals("-databaseFileName")) {
            dbName = args[1];
        }

        try (Connector conn = new Connector(dbName)) {
            CompanyService.registerInstanceWith(conn);
            try {
                createCompanyTable(conn);
            } catch (SQLException | SqlRuntimeException sre) {
                System.out.println("Table already exists!");
            }

            while (true) {
                MainMenu.show();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createCompanyTable(Connector connector) throws SQLException {
        connector.state("CREATE TABLE COMPANY(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255) UNIQUE NOT NULL)");
    }

    public static void insertCompany(int id, String name, Connector connector) throws SQLException {
        connector.state("INSERT INTO COMPANY(ID, NAME) VALUES(%d, '%s')".formatted(id, name));
    }

    public static void selectTestCompany(Connector connector) throws SQLException {
        var result = connector.query("select * from COMPANY");
        result.next();
        System.out.println(result.getString("NAME"));
        System.out.println(result.getInt("ID"));
    }
}