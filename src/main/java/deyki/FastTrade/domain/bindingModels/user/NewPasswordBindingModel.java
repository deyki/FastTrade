package deyki.FastTrade.domain.bindingModels.user;

import lombok.Data;

@Data
public class NewPasswordBindingModel {

    private String oldPassword;
    private String newPassword;
}
