package ru.pavel16649.Services.Jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pavel16649.Domain.Friendship;
import ru.pavel16649.Repositories.JpaFriendshipsRepository;
import ru.pavel16649.Services.FriendshipService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaFriendshipService implements FriendshipService {
    private final JpaFriendshipsRepository friendshipsRepository;

    public void addFriendship(Long catId1, Long catId2) {
        Friendship friendship1 = new Friendship();
        friendship1.setFirstCatId(catId1);
        friendship1.setSecondCatId(catId2);

        Friendship friendship2 = new Friendship();
        friendship2.setFirstCatId(catId2);
        friendship2.setSecondCatId(catId1);

        friendshipsRepository.save(friendship1);
        friendshipsRepository.save(friendship2);
    }

    public void removeFriendship(Long catId1, Long catId2) {
        Optional<Friendship> friendship1 = friendshipsRepository.findFriendshipByCatsIds(catId1, catId2);
        if (friendship1.isEmpty()) {
            return;
        }
        friendshipsRepository.delete(friendship1.get());
        Friendship friendship2 = friendshipsRepository.findFriendshipByCatsIds(catId1, catId2).get();
        friendshipsRepository.delete(friendship2);
    }

    public List<Long> findFriendsOfCat(Long catId) {
        return friendshipsRepository.findFriendsByCatId(catId);
    }

    @Override
    public void deleteFriendshipsByCatId(Long catId) {
        friendshipsRepository.deleteFriendshipsByCatId(catId);
    }
}
