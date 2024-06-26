package com.systemedebons.bonification.Service;

import com.systemedebons.bonification.Entity.Point;
import com.systemedebons.bonification.Entity.Reward;
import com.systemedebons.bonification.Entity.User;
import com.systemedebons.bonification.Repository.PointRepository;
import com.systemedebons.bonification.Repository.RewardRepository;
import com.systemedebons.bonification.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Optional<Reward> getRewardById(String  id) {
        return rewardRepository.findById(id);
    }

    public Reward saveReward(Reward reward) {
        return rewardRepository.save(reward);

    }

    public void deleteReward(String id) {
        rewardRepository.deleteById(id);
    }

    public boolean exchangePoints(String userId, String rewardId) {
        Optional<Reward> rewardOptional = rewardRepository.findById(rewardId);
        if (rewardOptional.isPresent()) {
            Reward reward = rewardOptional.get();
            int solde = pointRepository.findByUserId(userId)
                    .stream().mapToInt(Point::getNombre).sum();

            if (solde >= reward.getPoints()) {
                Optional<User> userOptional = userRepository.findById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    Point point = new Point();
                    point.setUser(user);
                    point.setNombre(-reward.getPoints());
                    point.setDate(LocalDate.now());
                    pointRepository.save(point);
                    return true;
                }
            }
        }
        return false;
    }





}
