package com.devsuperior.userdept.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;

//Mapeamento da classe com anotations
@Entity // anotation informando que é uma entidade de um banco de dados
@Table(name = "tb_user") // Informando que é uma tabela no bd
public class User {
 public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    private boolean ativo;
    private String email;

    @ManyToOne
    @JoinColumn(name="department_id") // Informando que o department id vem com join e é uma foreign key
    private Department department; // Relacionando um atributo de outra class

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User() {
        
    }
}
