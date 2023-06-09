package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_seq")
    @SequenceGenerator(sequenceName = "department_seq",allocationSize = 1,name = "department_seq")
    @Column(name = "department_id")
    private Long id;
    private String name;
    //OneToMany and ManyToMany we use Lazy
    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

}