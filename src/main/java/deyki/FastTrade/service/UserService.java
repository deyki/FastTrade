package deyki.FastTrade.service;

import deyki.FastTrade.domain.bindingModels.NewUsernameBindingModel;
import deyki.FastTrade.domain.bindingModels.UserBindingModel;
import deyki.FastTrade.domain.bindingModels.UserProfileDetailsBindingModel;
import deyki.FastTrade.domain.responseModels.SignInResponseModel;
import deyki.FastTrade.domain.responseModels.UserResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void signUp(UserBindingModel userBindingModel);

    SignInResponseModel signIn(UserBindingModel userBindingModel);

    void changeUsernameById(Long userId, NewUsernameBindingModel newUsernameBindingModel);

    void createUserProfileDetails(Long userId, UserProfileDetailsBindingModel userProfileDetailsBindingModel);

    UserResponseModel getUserInfoById(Long userId);
}
