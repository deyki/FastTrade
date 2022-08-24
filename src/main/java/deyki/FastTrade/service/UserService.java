package deyki.FastTrade.service;

import deyki.FastTrade.domain.bindingModels.user.*;
import deyki.FastTrade.domain.responseModels.user.SignInResponseModel;
import deyki.FastTrade.domain.responseModels.user.UserResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void signUp(UserBindingModel userBindingModel);

    SignInResponseModel signIn(UserBindingModel userBindingModel);

    void changeUsernameById(Long userId, NewUsernameBindingModel newUsernameBindingModel);

    void createUserProfileDetails(Long userId, UserProfileDetailsBindingModel userProfileDetailsBindingModel);

    UserResponseModel getUserInfoById(Long userId);

    void updateUserPhoneNumber(Long userId, NewPhoneNumberBindingModel newPhoneNumberBindingModel);

    void updateUserEmail(Long userId, NewEmailBindingModel newEmailBindingModel);

    void updateUserNames(Long userId, NewUserNamesBindingModel newUserNamesBindingModel);
}
