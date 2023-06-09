package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRoleKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @MapsId("role_id")
    @JoinColumn(name="role_id")
    private Role role;


}
