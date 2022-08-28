package deyki.FastTrade.controller;

import deyki.FastTrade.domain.bindingModels.user.*;
import deyki.FastTrade.domain.responseModels.user.UserResponseModel;
import deyki.FastTrade.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PutMapping("/changeUsername/{userId}")
    public ResponseEntity<String> changeUsername(@PathVariable Long userId, @RequestBody NewUsernameBindingModel newUsernameBindingModel) {

        userService.changeUsernameById(userId, newUsernameBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Username changed successfully!");
    }

    @PostMapping("/createProfileDetails/{userId}")
    public ResponseEntity<String> createProfileDetails(@PathVariable Long userId, @RequestBody UserProfileDetailsBindingModel userProfileDetailsBindingModel) {

        userService.createUserProfileDetails(userId, userProfileDetailsBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Profile details successfully created!");
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserResponseModel> getUserInfo(@PathVariable Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfoById(userId));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserResponseModel> getUserByEmail(@PathVariable String email) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfoByEmail(email));
    }

    @GetMapping("/getUserByPhoneNumber/{phoneNumber}")
    public ResponseEntity<UserResponseModel> getUserByPhoneNumber(@PathVariable Integer phoneNumber) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfoByPhoneNumber(phoneNumber));
    }

    @PutMapping("/updatePhoneNumber/{userId}")
    public ResponseEntity<String> updateUserPhoneNumber(@PathVariable Long userId, @RequestBody NewPhoneNumberBindingModel newPhoneNumberBindingModel) {

        userService.updateUserPhoneNumber(userId, newPhoneNumberBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Phone number updated successfully!");
    }

    @PutMapping("/updateEmail/{userId}")
    public ResponseEntity<String> updateUserEmail(@PathVariable Long userId, @RequestBody NewEmailBindingModel newEmailBindingModel) {

        userService.updateUserEmail(userId, newEmailBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Email updated successfully!");
    }

    @PutMapping("/updateNames/{userId}")
    public ResponseEntity<String> updateUserNames(@PathVariable Long userId, @RequestBody NewUserNamesBindingModel newUserNamesBindingModel) {

        userService.updateUserNames(userId, newUserNamesBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("User names updated successfully!");
    }
}
