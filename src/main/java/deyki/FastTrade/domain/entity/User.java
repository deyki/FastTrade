package deyki.FastTrade.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id", referencedColumnName = "userDetailsId")
    private UserProfileDetails userProfileDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "bankAccountId")
    private BankAccount bankAccount;
}
