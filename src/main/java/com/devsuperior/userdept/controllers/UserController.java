package com.devsuperior.userdept.controllers;

import com.devsuperior.userdept.entities.Department;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users") // Mapeando o endpoint

public class UserController {
    // Injeta  dependências em classes gerenciadas pelo Spring
    private final UserRepository repository;
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

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
//    @PutMapping("/update/{id}")
//    public Optional<User> editUser(@PathVariable Long id){
//
//        return repository.findById(id).map(user -> {
//            user.setEmail(insert(user).getEmail());
//            user.setName(insert(user).getName());
//            user.setAtivo(insert(user).getAtivo());
//            user.setDepartment(insert(user).getDepartment());
//            return repository.save(user);
//        }).orElseGet(()->{
//            User insert = null;
//            insert.setId(id);
//        })
//    }
    @DeleteMapping(path="/delete/{id}")
    @CrossOrigin(origins="*", maxAge = 3600)
    public ResponseEntity<String> delete(@PathVariable Long id){
        User userToDelete = repository.findById(id).orElse(null);

        if (userToDelete!=null){
            repository.delete(userToDelete);
            return ResponseEntity.ok("Usuario excluido com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
    }

}
