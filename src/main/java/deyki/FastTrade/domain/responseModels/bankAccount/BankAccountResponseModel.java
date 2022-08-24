package deyki.FastTrade.domain.responseModels.bankAccount;

import lombok.Data;

@Data
public class BankAccountResponseModel {

    private String iban;
    private Float balance;
    private String username;
}
