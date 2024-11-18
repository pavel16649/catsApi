package ru.pavel16649.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pavel16649.Domain.Owner;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaOwnerRepository extends JpaRepository<Owner, Long> {
    @Query("SELECT o FROM Owner o WHERE LOWER(o.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Owner> findByName(@Param("name") String name);

    List<Owner> findByBirthDate(LocalDate birthDate);
}