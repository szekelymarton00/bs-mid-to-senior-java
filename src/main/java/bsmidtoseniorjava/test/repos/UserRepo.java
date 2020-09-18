package bsmidtoseniorjava.test.repos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bsmidtoseniorjava.test.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public interface UserRepo extends CrudRepository<User, UUID> {

    @Query("SELECT * FROM bsuser")
    List<User> listAllUsers();

    @Query("SELECT * FROM bsuser WHERE user_id = :id")
    Optional<User> findById(@Param("id") UUID id);
}