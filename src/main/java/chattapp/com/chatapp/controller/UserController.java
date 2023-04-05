package chattapp.com.chatapp.controller;


import chattapp.com.chatapp.dao.model.User;
import chattapp.com.chatapp.dto.reesponse.UpdateUserDetailsResponse;
import chattapp.com.chatapp.dto.reesponse.UserRegisterResponse;
import chattapp.com.chatapp.dto.request.UpdateUserdetailsRequest;
import chattapp.com.chatapp.exception.UserCannotBeFoundException;
import chattapp.com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody User registerUser) {
        UserRegisterResponse savedUser = userService.registerUser(registerUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> viewAllUser() {
        List<User> allUsers = userService.viewAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.CREATED);
    }


    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserdetailsRequest updateUserdetailsRequest, @PathVariable Long userId) throws UserCannotBeFoundException {
        UpdateUserDetailsResponse updatedUser = userService.updateUserProfile(updateUserdetailsRequest, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @PutMapping("disable/{userId}")
    public ResponseEntity<?> disableUserAccount(@PathVariable Long userId) throws UserCannotBeFoundException {
        User userAccount = userService.disableUserAccount(userId);
        return new ResponseEntity<>(userAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) throws UserCannotBeFoundException {
        String userAccount = userService.deleteAccountById(userId);
        return new ResponseEntity<>(userAccount, HttpStatus.CREATED);
    }



}
