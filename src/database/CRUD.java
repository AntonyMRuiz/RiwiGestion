package database;

import java.util.List;

public interface CRUD {
    public boolean create(Object obj);

    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);

    public List<Object> getByField(String field, String search);
}
