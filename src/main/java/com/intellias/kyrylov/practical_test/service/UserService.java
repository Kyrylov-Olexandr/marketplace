package com.intellias.kyrylov.practical_test.service;

import com.intellias.kyrylov.practical_test.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();

    List<UserDTO> findAllByPurchasedProductId(Long productId);

    boolean deleteUserById(Long id);
}
