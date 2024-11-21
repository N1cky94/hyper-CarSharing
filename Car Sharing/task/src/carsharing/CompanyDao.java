package carsharing;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyDao implements Dao<Company> {
    private static final String FIND_ALL = "select * from COMPANIES";
    private final Connector connector;

    public CompanyDao(Connector connector) {
        this.connector = connector;
    }

    @Override
    public List<Company> findAll() {
        ResultSet set = connector.query(FIND_ALL);
        return new ArrayList<>();
    }

    @Override
    public Optional<Company> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Company entity) {

    }

    @Override
    public void update(int id, Company updatedEntity) {

    }

    @Override
    public void delete(Company entity) {

    }
}
