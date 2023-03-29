package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.user.domain.User;
import com.hongdaestudy.recipebackend.user.repository.ChefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChefService {

    private final ChefRepository chefRepository;

    @Transactional
    public List<User> selectListChef() {
        return chefRepository.findAll();
    }

    @Transactional
    public List<User> selectListChef(Long userId) {
        return chefRepository.findAllById(userId);
    }
}
