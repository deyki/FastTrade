package deyki.FastTrade.service;

import deyki.FastTrade.domain.responseModels.digitalWallet.DigitalWalletResponseModel;

public interface DigitalWalletService {

    void createDigitalWallet(Long userId);

    DigitalWalletResponseModel getDigitalWalletById(Long digitalWalletId);

    DigitalWalletResponseModel getDigitalWalletByOwnerUsername(String username);
}
