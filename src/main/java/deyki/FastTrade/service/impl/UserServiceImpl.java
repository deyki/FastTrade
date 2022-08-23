package deyki.FastTrade.service.impl;

import deyki.FastTrade.domain.bindingModels.UserBindingModel;
import deyki.FastTrade.domain.responseModels.SignInResponseModel;
import deyki.FastTrade.repository.UserRepository;
import deyki.FastTrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void signUp(UserBindingModel userBindingModel) {

    }

    @Override
    public SignInResponseModel signIn(UserBindingModel userBindingModel) {
        return null;
    }
}
