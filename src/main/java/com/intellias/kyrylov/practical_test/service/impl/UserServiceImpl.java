package com.intellias.kyrylov.practical_test.service.impl;

import com.intellias.kyrylov.practical_test.dto.UserDTO;
import com.intellias.kyrylov.practical_test.model.User;
import com.intellias.kyrylov.practical_test.repository.ProductRepository;
import com.intellias.kyrylov.practical_test.repository.PurchaseRepository;
import com.intellias.kyrylov.practical_test.repository.UserRepository;
import com.intellias.kyrylov.practical_test.service.UserService;
import com.intellias.kyrylov.practical_test.util.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, Mapper<User, UserDTO> {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return convertAllToDTO(userRepository.findAll());
    }

    @Override
    public List<UserDTO> findAllByPurchasedProductId(Long productId) {
        return convertAllToDTO(userRepository.findByPurchasedProductId(productId));
    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow();
            userRepository.delete(user);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public List<UserDTO> convertAllToDTO(Collection<User> users) {
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
    }

    @Override
    public List<User> convertAllToEntity(Collection<UserDTO> dtos) {
        return dtos.stream().map(userDTO -> modelMapper.map(userDTO, User.class)).toList();
    }
}
