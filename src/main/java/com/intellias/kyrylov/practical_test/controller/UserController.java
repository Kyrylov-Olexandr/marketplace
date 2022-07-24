package com.intellias.kyrylov.practical_test.controller;

import com.intellias.kyrylov.practical_test.dto.ProductDTO;
import com.intellias.kyrylov.practical_test.dto.UserDTO;
import com.intellias.kyrylov.practical_test.model.User;
import com.intellias.kyrylov.practical_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO savedUserDTO = userService.saveUser(userDTO);
            return ResponseEntity.ok(savedUserDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) Long purchasedProductId) {
        List<UserDTO> userDTOList;
        if (purchasedProductId == null) {
            userDTOList = userService.findAllUsers();
        } else {
            userDTOList = userService.findAllByPurchasedProductId(purchasedProductId);
        }
        return ResponseEntity.ok(userDTOList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
