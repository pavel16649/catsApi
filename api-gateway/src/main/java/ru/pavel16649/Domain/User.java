package ru.pavel16649.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String roles;

    public User() {

    }
}

