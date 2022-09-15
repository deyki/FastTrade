package deyki.FastTrade.service.impl;

import deyki.FastTrade.domain.bindingModels.item.BuyItemBindingModel;
import deyki.FastTrade.domain.bindingModels.item.ItemBindingModel;
import deyki.FastTrade.domain.entity.DigitalWallet;
import deyki.FastTrade.domain.entity.Item;
import deyki.FastTrade.domain.entity.PaymentHistory;
import deyki.FastTrade.domain.entity.User;
import deyki.FastTrade.domain.responseModels.item.ItemResponseModel;
import deyki.FastTrade.repository.DigitalWalletRepository;
import deyki.FastTrade.repository.ItemRepository;
import deyki.FastTrade.repository.PaymentHistoryRepository;
import deyki.FastTrade.repository.UserRepository;
import deyki.FastTrade.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final DigitalWalletRepository digitalWalletRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository, ModelMapper modelMapper, DigitalWalletRepository digitalWalletRepository, PaymentHistoryRepository paymentHistoryRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.digitalWalletRepository = digitalWalletRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    @Override
    public void createNewItem(Long userId, ItemBindingModel itemBindingModel) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        Item item = modelMapper.map(itemBindingModel, Item.class);
        item.setForSale(true);
        item.setUserOwner(user);

        itemRepository.save(item);
    }

    @Override
    public List<ItemResponseModel> getItemsByOwnerUsername(String username) {

        return itemRepository
                .findAll()
                .stream()
                .filter(item -> item.getUserOwner().getUsername().equals(username))
                .map(item -> modelMapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResponseModel> getItemsForSale() {

        return itemRepository
                .findAll()
                .stream()
                .filter(item -> item.getForSale().equals(true))
                .map(item -> modelMapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResponseModel> getSoldItems() {

        return itemRepository
                .findAll()
                .stream()
                .filter(item -> item.getForSale().equals(false))
                .map(item -> modelMapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyItem(BuyItemBindingModel buyItemBindingModel) throws Exception {

        User user = userRepository
                .findById(buyItemBindingModel.getItemId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", buyItemBindingModel.getUserId())));

        Item item = itemRepository
                .findById(buyItemBindingModel.getItemId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Item with id: %d not found!", buyItemBindingModel.getItemId())));

        DigitalWallet digitalWallet = user.getDigitalWallet();

        boolean enoughBalance = digitalWallet.getBalance() > item.getPrice();

        if (enoughBalance) {
            Float newDigitalWalletBalance = digitalWallet.getBalance() - item.getPrice();
            digitalWallet.setBalance(newDigitalWalletBalance);

            item.setForSale(false);

            itemRepository.save(item);

            digitalWalletRepository.save(digitalWallet);

            PaymentHistory paymentHistory = new PaymentHistory();
            paymentHistory.setItemName(item.getName());
            paymentHistory.setItemPrice(item.getPrice());
            paymentHistory.setBuyerUsername(user.getUsername());
            paymentHistory.setOwnerUsername(item.getUserOwner().getUsername());
            paymentHistory.setDate(new Date());

            paymentHistoryRepository.save(paymentHistory);
        } else {
            throw new Exception("Your digital wallet balance is not enough!");
        }
    }

    @Override
    public void changeItemStatusById(Long itemId) {

        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Item with id: %d not found!", itemId)));

        item.setForSale(!item.getForSale().equals(true));

        itemRepository.save(item);
    }
}
