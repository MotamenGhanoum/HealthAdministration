package com.sigma.it.health.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;


    public UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

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




    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

