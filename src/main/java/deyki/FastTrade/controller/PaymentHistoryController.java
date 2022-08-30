package deyki.FastTrade.controller;

import deyki.FastTrade.domain.responseModels.paymentHistory.PaymentHistoryResponseModel;
import deyki.FastTrade.service.impl.PaymentHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/paymentHistory")
public class PaymentHistoryController {

    private final PaymentHistoryServiceImpl paymentHistoryService;

    @Autowired
    public PaymentHistoryController(PaymentHistoryServiceImpl paymentHistoryService) {
        this.paymentHistoryService = paymentHistoryService;
    }

    @GetMapping("/getPurchases/{username}")
    public ResponseEntity<List<PaymentHistoryResponseModel>> getPurchases(@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK).body(paymentHistoryService.getMyPurchases(username));
    }

    @GetMapping("/getSales/{username}")
    public ResponseEntity<List<PaymentHistoryResponseModel>> getSales(@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK).body(paymentHistoryService.getMySales(username));
    }
}
