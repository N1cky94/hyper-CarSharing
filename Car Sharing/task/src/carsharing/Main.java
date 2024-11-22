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
            createTables(conn);


            while (true) {
                MainMenu.show();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createTables(Connector connector) {
        try {
            connector.state("CREATE TABLE COMPANY(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255) UNIQUE NOT NULL)");
        } catch (SqlRuntimeException sre) {
            System.out.println("Table COMPANY already exists!");
        }

        try {
            connector.state("""
                    CREATE TABLE CAR(
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(255) UNIQUE NOT NULL,
                        COMPANY_ID INT NOT NULL,
                        FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)
                    );""");
        } catch (SqlRuntimeException sre) {
            System.out.println("Table CAR already exists!");
        }

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