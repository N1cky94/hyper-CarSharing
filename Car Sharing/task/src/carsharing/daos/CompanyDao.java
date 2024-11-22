package carsharing.daos;

import carsharing.db.SqlRuntimeException;
import carsharing.models.Company;
import carsharing.db.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyDao implements Dao<Company> {
    private static final String FIND_ALL = "select * from COMPANY";
    private static final String SAVE_NEW = "insert into COMPANY (name) values ('%s')";
    private final Connector connector;

    public CompanyDao(Connector connector) {
        this.connector = connector;
    }

    @Override
    public List<Company> findAll() {
        ResultSet set = connector.query(FIND_ALL);
        List<Company> companies = new ArrayList<>();
        try {
            while (set.next()) {
                Company company = new Company(
                        set.getInt("ID"),
                        set.getString("NAME")
                );
                companies.add(company);
            }
        } catch (SQLException sqle) {
            throw new SqlRuntimeException(sqle);
        }

        return companies;
    }

    @Override
    public Optional<Company> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Company entity) {
        connector.state(SAVE_NEW.formatted(entity.getName()));
    }

    @Override
    public void update(int id, Company updatedEntity) {

    }

    @Override
    public void delete(Company entity) {

    }
}
