package pl.getinpoland.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.getinpoland.model.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUserId (Long userId);
    User findByUsername (String username);
}
