package deyki.FastTrade.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @SequenceGenerator(name = "payment_history_sequence", sequenceName = "payment_history_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_history_sequence")
    private Long paymentHistoryId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private Float itemPrice;

    @Column(name = "date")
    private Date date;
}
