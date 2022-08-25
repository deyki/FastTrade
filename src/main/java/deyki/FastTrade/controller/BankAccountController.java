package deyki.FastTrade.controller;

import deyki.FastTrade.domain.bindingModels.bankAccount.BankAccountBindingModel;
import deyki.FastTrade.domain.bindingModels.bankAccount.DepositBindingModel;
import deyki.FastTrade.domain.responseModels.bankAccount.BankAccountResponseModel;
import deyki.FastTrade.service.impl.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {

    private final BankAccountServiceImpl bankAccountService;

    @Autowired
    public BankAccountController(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createBankAccount(@PathVariable Long userId, @RequestBody BankAccountBindingModel bankAccountBindingModel) {

        bankAccountService.createBankAccount(userId, bankAccountBindingModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Bank account created!");
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<BankAccountResponseModel> getBankAccountByUsername(@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getBankAccountByUsername(username));
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositBindingModel depositBindingModel) {

        bankAccountService.deposit(depositBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Deposit successfully done!");
    }

    @GetMapping("/balance/{username}")
    public ResponseEntity<String> checkBankAccountBalance(@PathVariable String username) {

        String message = String.format("Your bank account balance is %f", bankAccountService.checkBalanceByUsername(username));

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/balance/{bankAccountId}")
    public ResponseEntity<String> checkBankAccountBalanceById(@PathVariable Long bankAccountId) {

        String message = String.format("Balance of bank account with id %d is %f", bankAccountId, bankAccountService.checkBalanceById(bankAccountId));

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
