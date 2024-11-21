package carsharing;

import java.util.List;

public class CompanyService {
    private static CompanyService instance;
    private final Dao dao;

    private CompanyService(Connector connector) {
        this.dao = new CompanyDao(connector);
    }

    public static void registerInstanceWith(Connector conn) {
        if (instance == null) {
            instance = new CompanyService(conn);
        }
    }

    public static CompanyService getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CompanyService not initialized");
        }
        return instance;
    }

    public List<Company> getCompanies() {
        return dao.findAll();
    }
}
