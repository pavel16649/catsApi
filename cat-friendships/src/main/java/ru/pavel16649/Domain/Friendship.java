package ru.pavel16649.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_cat_id", nullable = false)
    private Long firstCatId;

    @Column(name = "second_cat_id", nullable = false)
    private Long secondCatId;

    public Friendship() {

    }
}
