package deyki.FastTrade.service.impl;

import deyki.FastTrade.domain.entity.DigitalWallet;
import deyki.FastTrade.domain.entity.User;
import deyki.FastTrade.domain.responseModels.digitalWallet.DigitalWalletResponseModel;
import deyki.FastTrade.repository.DigitalWalletRepository;
import deyki.FastTrade.repository.UserRepository;
import deyki.FastTrade.service.DigitalWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DigitalWalletServiceImpl implements DigitalWalletService {

    private final DigitalWalletRepository digitalWalletRepository;
    private final UserRepository userRepository;

    @Autowired
    public DigitalWalletServiceImpl(DigitalWalletRepository digitalWalletRepository, UserRepository userRepository) {
        this.digitalWalletRepository = digitalWalletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createDigitalWallet(Long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        DigitalWallet digitalWallet = new DigitalWallet();
        digitalWallet.setBalance(0.00F);
        digitalWallet.setUser(user);

        user.setDigitalWallet(digitalWallet);

        userRepository.save(user);
        digitalWalletRepository.save(digitalWallet);
    }

    @Override
    public DigitalWalletResponseModel getDigitalWalletById(Long digitalWalletId) {

        DigitalWallet digitalWallet = digitalWalletRepository
                .findById(digitalWalletId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Digital wallet with id: %d not found!", digitalWalletId)));

        DigitalWalletResponseModel digitalWalletResponseModel = new DigitalWalletResponseModel();
        digitalWalletResponseModel.setBalance(digitalWallet.getBalance());
        digitalWalletResponseModel.setUserOwner(digitalWallet.getUser().getUsername());

        return digitalWalletResponseModel;
    }

    @Override
    public DigitalWalletResponseModel getDigitalWalletByOwnerUsername(String username) {

        DigitalWallet digitalWallet = digitalWalletRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Digital wallet with owner username: %s not found!", username)));

        DigitalWalletResponseModel digitalWalletResponseModel = new DigitalWalletResponseModel();
        digitalWalletResponseModel.setBalance(digitalWallet.getBalance());
        digitalWalletResponseModel.setUserOwner(digitalWallet.getUser().getUsername());

        return digitalWalletResponseModel;
    }
}
