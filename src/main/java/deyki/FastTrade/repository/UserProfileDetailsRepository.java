package deyki.FastTrade.repository;

import deyki.FastTrade.domain.entity.UserProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDetailsRepository extends JpaRepository<UserProfileDetails, Long> {
}
