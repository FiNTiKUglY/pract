package repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import entities.*;

public interface BaseRepository {
    List<IEntity> getAll() throws SQLException;
    IEntity getById(UUID id) throws SQLException;
    void add(IEntity entity) throws SQLException;
    void remove(UUID id) throws SQLException;
    void update(IEntity entity) throws SQLException;
}