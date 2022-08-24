package deyki.FastTrade.domain.bindingModels.bankAccount;

import lombok.Data;

@Data
public class DepositBindingModel {

    private String iban;
    private Float amount;
}
