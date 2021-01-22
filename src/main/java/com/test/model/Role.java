package com.test.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/*@Entity
@Table(name = "roles")
@Data
public class Role {
    private static final long serialVersionUID = -5525359165179861924L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;
}*/
