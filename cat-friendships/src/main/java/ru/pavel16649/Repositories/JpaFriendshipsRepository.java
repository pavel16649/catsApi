package ru.pavel16649.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.pavel16649.Domain.Friendship;

import java.util.List;
import java.util.Optional;

public interface JpaFriendshipsRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT f.secondCatId FROM Friendship f WHERE f.firstCatId = :catId")
    List<Long> findFriendsByCatId(@Param("catId") Long catId);

    @Query("DELETE FROM Friendship f WHERE f.firstCatId = :catId OR f.secondCatId = :catId")
    void deleteFriendshipsByCatId(@Param("catId") Long catId);

    @Query("SELECT f FROM Friendship f WHERE f.firstCatId = :catId1 AND f.secondCatId = :catId2")
    Optional<Friendship> findFriendshipByCatsIds(@Param("catId1") Long catId1, @Param("catId2") Long catId2);
}
