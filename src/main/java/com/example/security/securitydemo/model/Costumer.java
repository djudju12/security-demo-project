package com.example.security.securitydemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "costumers")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @OneToMany(mappedBy = "costumer", fetch = FetchType.EAGER)
    private List<Authority> role = new ArrayList<>();

    public void addRole(Authority role) {
        this.role.add(role);
    }

}
