package it.vitalegi.budget.user;

import it.vitalegi.budget.auth.AuthenticationService;
import it.vitalegi.budget.metrics.Performance;
import it.vitalegi.budget.metrics.Type;
import it.vitalegi.budget.user.dto.User;
import it.vitalegi.budget.user.entity.UserEntity;
import it.vitalegi.budget.user.mapper.UserMapper;
import it.vitalegi.budget.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Performance(Type.SERVICE)
@Log4j2
@Service
public class UserService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper mapper;

    @Transactional
    public void importCurrentUser() {
        String id = authenticationService.getUid();
        if (userRepository.findByUid(id) != null) {
            return;
        }
        log.info("User {} is unknown, import.", id);
        UserEntity user = new UserEntity();
        user.setUid(id);
        user.setUsername(authenticationService.getName());
        userRepository.save(user);
    }

    public UserEntity getCurrentUserEntity() {
        String uid = authenticationService.getUid();
        return userRepository.findByUid(uid);
    }
    public User getCurrentUser() {
        return mapper.map(getCurrentUserEntity());
    }
}