package ru.pavel16649.Services;

import java.util.List;

public interface FriendshipService {
    void addFriendship(Long catId1, Long catId2);
    void removeFriendship(Long catId1, Long catId2);
    List<Long> findFriendsOfCat(Long catId);
    void deleteFriendshipsByCatId(Long catId);
}