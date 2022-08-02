package com.wipro.bookAppBackend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String pswd;

    @Column
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Set<Role> roles;

    @Column
    private String token;
}
