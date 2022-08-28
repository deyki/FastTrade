package deyki.FastTrade.repository;

import deyki.FastTrade.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.userProfileDetails.email = ?1")
    Optional<User> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.userProfileDetails.phoneNumber = ?1")
    Optional<User> findByPhoneNumber(@Param("phoneNumber") Integer phoneNumber);
}
