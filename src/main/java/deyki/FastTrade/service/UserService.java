package deyki.FastTrade.service;

import deyki.FastTrade.domain.bindingModels.NewUsernameBindingModel;
import deyki.FastTrade.domain.bindingModels.UserBindingModel;
import deyki.FastTrade.domain.responseModels.SignInResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void signUp(UserBindingModel userBindingModel);

    SignInResponseModel signIn(UserBindingModel userBindingModel);

    void changeUsernameById(Long userId, NewUsernameBindingModel newUsernameBindingModel);
}
