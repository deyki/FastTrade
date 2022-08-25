package deyki.FastTrade.repository;

import deyki.FastTrade.domain.entity.DigitalWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DigitalWalletRepository extends JpaRepository<DigitalWallet, Long> {

    @Query("SELECT d FROM DigitalWallet d WHERE d.user.username = ?1")
    Optional<DigitalWallet> findByUsername(@Param("username") String username);
}
