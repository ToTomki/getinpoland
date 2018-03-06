package pl.getinpoland.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.getinpoland.model.user.User;
import pl.getinpoland.model.user.enums.UserRole;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUserId (Long userId);
    User findByUsername (String username);
    List<User> findByUserRoleIn(List<UserRole> userRoles);
}
