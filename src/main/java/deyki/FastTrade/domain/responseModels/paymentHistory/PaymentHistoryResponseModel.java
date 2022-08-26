package deyki.FastTrade.domain.responseModels.paymentHistory;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentHistoryResponseModel {

    private String itemName;
    private Float itemPrice;
    private String ownerUsername;
    private String buyerUsername;
    private Date date;
}
