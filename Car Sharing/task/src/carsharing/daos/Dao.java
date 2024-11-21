package carsharing.daos;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    void save(T entity);
    void update(int id,T updatedEntity);
    void delete(T entity);
}
