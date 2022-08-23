package deyki.FastTrade.domain.bindingModels;

import deyki.FastTrade.domain.entity.User;
import lombok.Data;

@Data
public class UserProfileDetailsBindingModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
}
