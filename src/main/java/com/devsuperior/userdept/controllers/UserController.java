package com.devsuperior.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users") // Mapeando o endpoint
public class UserController {
    @Autowired // Injeta  dependências em classes gerenciadas pelo Spring
    private UserRepository repository;
    @GetMapping // Indica que é uma requisição HTTP
    public List<User> findAll() { // Retorna uma lista de usuários do banco de dados
        List<User> result = repository.findAll();
        return result;
    }
    @GetMapping(value = "/{id}") // busca pelo id nos parametros da URL
    public User findById(@PathVariable Long id) { 
        User result = repository.findById(id).get(); // O get para pegar o usuário pelo id
        return result;
    }
    
    @PostMapping // Indica que é uma requisição HTTP para inserção
    public User insert(@RequestBody User user) { 
        User result = repository.save(user); // O save para inserir um usuário pelo Body da req
        return result; 
    }
}
