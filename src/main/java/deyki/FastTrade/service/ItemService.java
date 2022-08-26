package deyki.FastTrade.service;

import deyki.FastTrade.domain.bindingModels.item.BuyItemBindingModel;
import deyki.FastTrade.domain.bindingModels.item.ItemBindingModel;
import deyki.FastTrade.domain.responseModels.item.ItemResponseModel;

import java.util.List;

public interface ItemService {

    void createNewItem(Long userId, ItemBindingModel itemBindingModel);

    List<ItemResponseModel> getItemsByOwnerUsername(String username);

    List<ItemResponseModel> getItemsForSale();

    List<ItemResponseModel> getSoldItems();

    void buyItem(BuyItemBindingModel buyItemBindingModel) throws Exception;
}
