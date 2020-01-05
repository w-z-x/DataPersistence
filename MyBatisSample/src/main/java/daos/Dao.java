package daos;

import entities.Entity;

public interface Dao {
    int create(Entity entity);

    Entity retrieve(int id);

    int update(Entity entity);

    int delete(int id);
}
