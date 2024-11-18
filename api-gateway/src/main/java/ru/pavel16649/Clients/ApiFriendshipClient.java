package ru.pavel16649.Clients;

import java.util.List;


public interface ApiFriendshipClient {
    public void addFriendship(Long catId1, Long catId2) throws Exception;

    public void deleteFriendship(Long catId1, Long catId2) throws Exception;

    public List<Long> getCatFriends(Long catId);
}
