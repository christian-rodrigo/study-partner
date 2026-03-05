package com.example.backend.service;

import com.example.backend.entity.*;
import com.example.backend.repository.ChatRepository;
import com.example.backend.repository.MatchRepository;
import com.example.backend.repository.SwipeRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SwipeService {

    private final SwipeRepository swipeRepository;
    private final MatchRepository matchRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public SwipeService(SwipeRepository swipeRepository,
                        MatchRepository matchRepository,
                        ChatRepository chatRepository,
                        UserRepository userRepository) {
        this.swipeRepository = swipeRepository;
        this.matchRepository = matchRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Match swipe(Long swiperId, Long swipedId, SwipeType type) {
        if (swiperId.equals(swipedId)) return null;

        User swiper = userRepository.findById(swiperId).orElseThrow();
        User swiped = userRepository.findById(swipedId).orElseThrow();

        // upsert swipe (unique constraint ensures one row per pair)
        Swipe swipe = swipeRepository.findBySwiper_IdAndSwiped_Id(swiperId, swipedId)
                .orElseGet(Swipe::new);

        swipe.setSwiper(swiper);
        swipe.setSwiped(swiped);
        swipe.setType(type);
        swipeRepository.save(swipe);

        // Only LIKE can produce match
        if (type != SwipeType.LIKE) return null;

        boolean reverseLikeExists =
                swipeRepository.existsBySwiper_IdAndSwiped_IdAndType(swipedId, swiperId, SwipeType.LIKE);

        if (!reverseLikeExists) return null;

        // Create match only once (keep ordering stable)
        Long u1 = Math.min(swiperId, swipedId);
        Long u2 = Math.max(swiperId, swipedId);

        Match match = matchRepository.findByUser1_IdAndUser2_Id(u1, u2).orElse(null);
        if (match == null) {
            Match m = new Match();
            m.setUser1(userRepository.findById(u1).orElseThrow());
            m.setUser2(userRepository.findById(u2).orElseThrow());
            match = matchRepository.save(m);

            Chat chat = new Chat();
            chat.setMatch(match);
            chatRepository.save(chat);
        }

        return match;
    }
}