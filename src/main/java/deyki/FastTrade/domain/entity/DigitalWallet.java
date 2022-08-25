package deyki.FastTrade.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "digital_wallet")
public class DigitalWallet {

    @Id
    @SequenceGenerator(name = "digital_wallet_sequence", sequenceName = "digital_wallet_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "digital_wallet_sequence")
    private Long walletId;

    @Column(name = "balance")
    private Float balance;

    @OneToOne(mappedBy = "digitalWallet")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_history_id", referencedColumnName = "paymentHistoryId")
    private List<PaymentHistory> paymentHistory;
}
