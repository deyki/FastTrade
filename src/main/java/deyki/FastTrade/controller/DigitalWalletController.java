package deyki.FastTrade.controller;

import deyki.FastTrade.domain.bindingModels.bankAccount.DepositBindingModel;
import deyki.FastTrade.domain.responseModels.digitalWallet.DigitalWalletResponseModel;
import deyki.FastTrade.service.impl.DigitalWalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/digitalWallet")
public class DigitalWalletController {

    private final DigitalWalletServiceImpl digitalWalletService;

    @Autowired
    public DigitalWalletController(DigitalWalletServiceImpl digitalWalletService) {
        this.digitalWalletService = digitalWalletService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createDigitalWallet(@PathVariable Long userId) {

        digitalWalletService.createDigitalWallet(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Digital wallet created!");
    }

    @GetMapping("/getById/{digitalWalletId}")
    public ResponseEntity<DigitalWalletResponseModel> getById(@PathVariable Long digitalWalletId) {

        return ResponseEntity.status(HttpStatus.OK).body(digitalWalletService.getDigitalWalletById(digitalWalletId));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<DigitalWalletResponseModel> getByUsername(@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK).body(digitalWalletService.getDigitalWalletByOwnerUsername(username));
    }

    @PostMapping("/depositMoney")
    public ResponseEntity<String> depositMoney(@RequestBody DepositBindingModel depositBindingModel) throws Exception {

        digitalWalletService.depositMoneyFromBankAccount(depositBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Deposit is done!");
    }
}
