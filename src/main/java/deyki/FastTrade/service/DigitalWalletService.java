package deyki.FastTrade.service;

import deyki.FastTrade.domain.bindingModels.bankAccount.DepositBindingModel;
import deyki.FastTrade.domain.responseModels.digitalWallet.DigitalWalletResponseModel;

public interface DigitalWalletService {

    void createDigitalWallet(Long userId);

    DigitalWalletResponseModel getDigitalWalletById(Long digitalWalletId);

    DigitalWalletResponseModel getDigitalWalletByOwnerUsername(String username);

    void depositMoneyFromBankAccount(DepositBindingModel depositBindingModel) throws Exception;

    Float checkDigitalWalletBalanceByUserId(Long userId);

    Float checkDigitalWalletBalanceByUserUsername(String username);
}
