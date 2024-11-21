package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector implements AutoCloseable {
    private static final String MAIN_URI = "jdbc:h2:./src/carsharing/db/";
    private final String dbName;
    private final Connection connection;

    public Connector(String dbName) throws SQLException {
        this.dbName = dbName;

        connection = DriverManager.getConnection(MAIN_URI + this.dbName);
    }

    public Connector() throws SQLException {
        this("CarSharing_DB");
    }

    public void state(String sql) throws SQLException {
        connection.prepareStatement(sql).execute();
    }

    public ResultSet query(String sql) throws SQLException {
        return connection.prepareStatement(sql).executeQuery();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
