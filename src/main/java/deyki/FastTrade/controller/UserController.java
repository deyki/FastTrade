package deyki.FastTrade.controller;

import deyki.FastTrade.domain.bindingModels.NewUsernameBindingModel;
import deyki.FastTrade.domain.bindingModels.UserProfileDetailsBindingModel;
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
}
