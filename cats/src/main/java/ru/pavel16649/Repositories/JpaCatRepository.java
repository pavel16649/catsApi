package ru.pavel16649.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.pavel16649.Domain.Cat;


import java.time.LocalDate;
import java.util.List;

public interface JpaCatRepository extends JpaRepository<Cat, Long> {
    @Query("SELECT c FROM Cat c WHERE LOWER(c.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Cat> findCatsByName(@Param("name") String name);

    @Query("SELECT c FROM Cat c WHERE LOWER(c.breed) = LOWER(:breed)")
    List<Cat> findCatsByBreed(@Param("breed") String breed);

    @Query("SELECT c FROM Cat c WHERE LOWER(c.color) = LOWER(:color)")
    List<Cat> findCatsByColor(@Param("color") String color);

    List<Cat> findByOwnerId(Long owner_id);

    @Query("SELECT c FROM Cat c WHERE c.birthDate = :birth_date")
    List<Cat> findCatsByBirthDate(@Param("birth_date") LocalDate birth_date);

    void deleteByOwnerId(Long ownerId);
}
