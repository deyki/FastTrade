package deyki.FastTrade.service;

import deyki.FastTrade.domain.responseModels.paymentHistory.PaymentHistoryResponseModel;

import java.util.List;

public interface PaymentHistoryService {

    List<PaymentHistoryResponseModel> getMyPurchases(String username);

    List<PaymentHistoryResponseModel> getMySales(String username);
}
