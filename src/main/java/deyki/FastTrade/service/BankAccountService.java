package deyki.FastTrade.service;

import deyki.FastTrade.domain.bindingModels.bankAccount.BankAccountBindingModel;
import deyki.FastTrade.domain.bindingModels.bankAccount.DepositBindingModel;
import deyki.FastTrade.domain.responseModels.bankAccount.BankAccountResponseModel;

public interface BankAccountService {

    void createBankAccount(Long userId, BankAccountBindingModel bankAccountBindingModel);

    BankAccountResponseModel getBankAccountByUsername(String username);

    void deposit(DepositBindingModel depositBindingModel);

    Float checkBalanceByIban(BankAccountBindingModel bankAccountBindingModel);

    Float checkBalanceByUsername(String username);

    Float checkBalanceById(Long bankAccountId);

    String getIbanByUserUsername(String username);
}
