package deyki.FastTrade.domain.responseModels.user;

import lombok.Data;

@Data
public class UserResponseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
}
