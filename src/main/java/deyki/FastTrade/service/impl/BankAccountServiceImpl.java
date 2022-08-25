package deyki.FastTrade.service.impl;

import deyki.FastTrade.domain.bindingModels.bankAccount.BankAccountBindingModel;
import deyki.FastTrade.domain.bindingModels.bankAccount.DepositBindingModel;
import deyki.FastTrade.domain.entity.BankAccount;
import deyki.FastTrade.domain.entity.User;
import deyki.FastTrade.domain.responseModels.bankAccount.BankAccountResponseModel;
import deyki.FastTrade.repository.BankAccountRepository;
import deyki.FastTrade.repository.UserRepository;
import deyki.FastTrade.service.BankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createBankAccount(Long userId, BankAccountBindingModel bankAccountBindingModel) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        bankAccountRepository
                .findByIban(bankAccountBindingModel.getIban())
                .ifPresent(bankAccount -> {
                    throw new EntityExistsException(String.format("Bank account with iban: %s already exist!", bankAccountBindingModel.getIban()));
                });

        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban(bankAccountBindingModel.getIban());
        bankAccount.setBalance(0.0F);
        bankAccount.setUser(user);
        bankAccountRepository.save(bankAccount);

        user.setBankAccount(bankAccount);
        userRepository.save(user);
    }

    @Override
    public BankAccountResponseModel getBankAccountByUsername(String username) {

        BankAccount bankAccount = bankAccountRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Bank account where username is: %s not found!", username)));

        BankAccountResponseModel bankAccountResponseModel = new BankAccountResponseModel();
        bankAccountResponseModel.setIban(bankAccount.getIban());
        bankAccountResponseModel.setBalance(bankAccount.getBalance());
        bankAccountResponseModel.setUsername(bankAccount.getUser().getUsername());

        return bankAccountResponseModel;
    }

    @Override
    public void deposit(DepositBindingModel depositBindingModel) {

        BankAccount bankAccount = bankAccountRepository
                .findByIban(depositBindingModel.getIban())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Bank account with iban: %s not found!", depositBindingModel.getIban())));

        Float newBankAccountBalance = bankAccount.getBalance() + depositBindingModel.getAmount();

        bankAccount.setBalance(newBankAccountBalance);

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public Float checkBalanceByUsername(String username) {

        BankAccount bankAccount = bankAccountRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Bank account with username: %s not found!", username)));

        return bankAccount.getBalance();
    }

    @Override
    public Float checkBalanceById(Long bankAccountId) {

        BankAccount bankAccount = bankAccountRepository
                .findById(bankAccountId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Bank account with id: %d not found!", bankAccountId)));

        return bankAccount.getBalance();
    }
}
