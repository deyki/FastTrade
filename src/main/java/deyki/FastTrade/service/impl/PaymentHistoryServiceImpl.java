package deyki.FastTrade.service.impl;

import deyki.FastTrade.domain.responseModels.paymentHistory.PaymentHistoryResponseModel;
import deyki.FastTrade.repository.PaymentHistoryRepository;
import deyki.FastTrade.service.PaymentHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentHistoryServiceImpl(PaymentHistoryRepository paymentHistoryRepository, ModelMapper modelMapper) {
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PaymentHistoryResponseModel> getMyPurchases(String username) {

        return paymentHistoryRepository
                .findAll()
                .stream()
                .filter(paymentHistory -> paymentHistory.getBuyerUsername().equals(username))
                .map(paymentHistory -> modelMapper.map(paymentHistory, PaymentHistoryResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentHistoryResponseModel> getMySales(String username) {

        return paymentHistoryRepository
                .findAll()
                .stream()
                .filter(paymentHistory -> paymentHistory.getOwnerUsername().equals(username))
                .map(paymentHistory -> modelMapper.map(paymentHistory, PaymentHistoryResponseModel.class))
                .collect(Collectors.toList());
    }
}
