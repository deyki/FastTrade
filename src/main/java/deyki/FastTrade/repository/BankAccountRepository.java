package deyki.FastTrade.repository;

import deyki.FastTrade.domain.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByIban(String iban);

    @Query("SELECT b FROM BankAccount b WHERE b.user.username = ?1")
    Optional<BankAccount> findByUsername(@Param("username") String username);
}
