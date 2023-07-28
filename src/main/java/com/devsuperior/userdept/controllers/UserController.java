package com.devsuperior.userdept.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.userdept.DTO.UserDTO;
import com.devsuperior.userdept.Services.UserService;
import com.devsuperior.userdept.entities.User;

@RestController
@RequestMapping(value = "/users") // Mapeando o endpoint
public class UserController {
    // Injeta dependências em classes gerenciadas pelo Spring
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // Indica que é uma requisição HTTP
    public List<UserDTO> findAll() { // Retorna uma lista de usuários do banco de dados
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}") // busca pelo id nos parametros da URL
    public UserDTO findById(@PathVariable Long id) {
        // O get para pegar o usuário pelo id
        return userService.getUserById(id);
    }

    @PostMapping // Indica que é uma requisição HTTP para inserção
    public UserDTO insert(@RequestBody User user) {

        return userService.insertUser(user);
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserService(id, user);
    }

    @DeleteMapping(path = "/delete/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.deleteService(id);
    }

}
