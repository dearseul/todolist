package todolist.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;


}
