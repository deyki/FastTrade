package deyki.FastTrade.service.impl;

import deyki.FastTrade.domain.bindingModels.user.*;
import deyki.FastTrade.domain.entity.User;
import deyki.FastTrade.domain.entity.UserProfileDetails;
import deyki.FastTrade.domain.responseModels.SignInResponseModel;
import deyki.FastTrade.domain.responseModels.UserResponseModel;
import deyki.FastTrade.repository.UserProfileDetailsRepository;
import deyki.FastTrade.repository.UserRepository;
import deyki.FastTrade.security.JWTUtil;
import deyki.FastTrade.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileDetailsRepository userProfileDetailsRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userRes = userRepository.findByUsername(username);

        if (userRes.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with username: %s not found!", username));
        }

        User user = userRes.get();

        return new org.springframework
                .security
                .core
                .userdetails
                .User(username, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public void signUp(UserBindingModel userBindingModel) {

        userRepository.findByUsername(userBindingModel.getUsername()).ifPresent(user1 -> {
            throw new EntityExistsException(String.format("User with username: %s already exist!", userBindingModel.getUsername()));
        });

        User user = modelMapper.map(userBindingModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userBindingModel.getPassword()));

        userRepository.save(user);
    }

    @Override
    public SignInResponseModel signIn(UserBindingModel userBindingModel) {

        User user = userRepository
                .findByUsername(userBindingModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find User with username: %s", userBindingModel.getUsername())));

        final String token = jwtUtil.generateToken(user.getUsername());

        SignInResponseModel signInResponseModel = new SignInResponseModel();
        signInResponseModel.setJWToken(token);

        return signInResponseModel;
    }

    @Override
    public void changeUsernameById(Long userId, NewUsernameBindingModel newUsernameBindingModel) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        user.setUsername(newUsernameBindingModel.getUsername());

        userRepository.save(user);
    }

    @Override
    public void createUserProfileDetails(Long userId, UserProfileDetailsBindingModel userProfileDetailsBindingModel) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        UserProfileDetails userProfileDetails = modelMapper.map(userProfileDetailsBindingModel, UserProfileDetails.class);
        userProfileDetails.setUser(user);

        user.setUserProfileDetails(userProfileDetails);

        userProfileDetailsRepository.save(userProfileDetails);
        userRepository.save(user);
    }

    @Override
    public UserResponseModel getUserInfoById(Long userId) {

        return userRepository
                .findById(userId)
                .map(user -> modelMapper.map(user.getUserProfileDetails(), UserResponseModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));
    }

    @Override
    public void updateUserPhoneNumber(Long userId, NewPhoneNumberBindingModel newPhoneNumberBindingModel) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        Integer newPhoneNumber = newPhoneNumberBindingModel.getPhoneNumber();

        user.getUserProfileDetails().setPhoneNumber(newPhoneNumber);

        userRepository.save(user);
    }

    @Override
    public void updateUserEmail(Long userId, NewEmailBindingModel newEmailBindingModel) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %d not found!", userId)));

        String newEmail = newEmailBindingModel.getEmail();

        user.getUserProfileDetails().setEmail(newEmail);

        userRepository.save(user);
    }
}
