package com.devsuperior.userdept.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devsuperior.userdept.DTO.UserDTO;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAtivo(user.getAtivo());
        userDTO.setDepartment(user.getDepartment());
        userDTO.setId(user.getId());

        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll(); // Busca todos os usuários
        List<UserDTO> usersDTOList = new ArrayList<>(); // Instancia uma lista de acordo com o DTO
        for (User user : users) { // Percorre todo array para adicionar os usuários ao array

            UserDTO userDTO = new UserDTO();

            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setAtivo(user.getAtivo());
            userDTO.setDepartment(user.getDepartment());
            userDTO.setId(user.getId());

            usersDTOList.add(userDTO);
        }

        return usersDTOList;
    }

    public UserDTO insertUser(User user) {
        UserDTO userDTO = new UserDTO();

        userRepository.save(user);

        return userDTO;
    }

    public ResponseEntity<UserDTO> updateUserService(Long id, User user) {
        UserDTO usuarioExistenteDTO = this.getUserById(id);
        User usuarioExistente = new User();        
    

        if (usuarioExistenteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (user.getName() != null) {
            usuarioExistenteDTO.setName(user.getName());
        }

        if (user.getEmail() != null) {
            usuarioExistenteDTO.setEmail(user.getEmail());
        }

        if (user.getDepartment() != null) {
            usuarioExistenteDTO.setDepartment(user.getDepartment());
        }

        usuarioExistente.setId(usuarioExistenteDTO.getId());
        usuarioExistente.setName(usuarioExistenteDTO.getName());
        usuarioExistente.setAtivo(usuarioExistenteDTO.getAtivo());
        usuarioExistente.setDepartment(usuarioExistenteDTO.getDepartment());
        usuarioExistente.setEmail(usuarioExistenteDTO.getEmail());

        userRepository.save(usuarioExistente);
        
        return new ResponseEntity<>(usuarioExistenteDTO, HttpStatus.OK);

    }


    public ResponseEntity<String> deleteService(Long id) {
        User userToDelete = userRepository.findById(id).get();
        userRepository.delete(userToDelete);
        
        return ResponseEntity.ok("Usuario excluido com sucesso");
    }
}
