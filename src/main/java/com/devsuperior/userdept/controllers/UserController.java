package com.devsuperior.userdept.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;

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

   @PutMapping("/update/{id}")
   @CrossOrigin(origins="*", maxAge = 3600)
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        
        User usuarioExistente = repository.findById(id).get();
         if (usuarioExistente == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

        if (updatedUser.getName() != null) {
            usuarioExistente.setName(updatedUser.getName());
        }

        if (updatedUser.getEmail() != null) {
            usuarioExistente.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getDepartment() != null) {
            usuarioExistente.setDepartment(updatedUser.getDepartment());
        }

       repository.save(usuarioExistente);

       return new ResponseEntity<>(usuarioExistente, HttpStatus.OK);
    }
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
