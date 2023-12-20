package com.example.aviacase.controller;

import com.example.aviacase.AviacaseApplication;
import com.example.aviacase.dto.AuthDto;
import com.example.aviacase.dto.MakePurchaseDto;
import com.example.aviacase.dto.PurchaseDto;
import com.example.aviacase.dto.UserDto;
import com.example.aviacase.mapper.PurchaseMapper;
import com.example.aviacase.mapper.UserMapper;
import com.example.aviacase.model.User;
import com.example.aviacase.service.PurchaseService;
import com.example.aviacase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @PostMapping("/register")
    public void register(@RequestParam String name, @RequestParam String surname,
                         @RequestParam String password, @RequestParam String login){
        userService.registration(login, password, name, surname);
    }

    @PostMapping("/auth")
    public void authorization(@RequestBody AuthDto authDto){
        User user = userMapper.fromAuthDto(authDto);
        if (userService.authorization(user)){
            User userInfo = userService.findUserByLogin(user.getLogin());
            AviacaseApplication.authenticateUser = userInfo;
        }
    }

    @GetMapping("/me")
    public UserDto me() {
        return userMapper.toUserDto(AviacaseApplication.authenticateUser);
    }

    @PutMapping("/edit-profile")
    public UserDto editProfile(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               @RequestParam(required = false) String password,
                               @RequestParam(required = false) String login){
        User updatedUser = userService.updateUser(AviacaseApplication.authenticateUser, login, password, name, surname);
        AviacaseApplication.authenticateUser = updatedUser;
        return userMapper.toUserDto(updatedUser);
    }

    @GetMapping("/users")
    public List<UserDto> findAllUsers(){
        return userService.findAllUsers().stream()
                .map(user -> userMapper.toUserDto(user))
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/user-purchases")
    public List<PurchaseDto> viewUserPurchases(){
        return purchaseService.getUserPurchases(AviacaseApplication.authenticateUser)
                .stream()
                .map(purchase -> purchaseMapper.toPurchaseDto(purchase))
                .toList();
    }

    @GetMapping("/exit")
    public void exit(){
        AviacaseApplication.authenticateUser = null;
    }

    @GetMapping("/find-by-login")
    public UserDto findUserByLogin(@RequestParam String login){
        return userMapper.toUserDto(userService.findUserByLogin(login));
    }
}
