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

    public void state(String sql) {
        try {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            throw new SqlRuntimeException("State failed: " + sql, e);
        }
    }

    public ResultSet query(String sql) {
        try {
            return connection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            throw new SqlRuntimeException("Query failed: " + sql, e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
