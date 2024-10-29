package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .flatMap(currentUser -> {
                    currentUser.setFirstName(user.getFirstName());
                    currentUser.setLastName(user.getLastName());
                    currentUser.setEmail(user.getEmail());
                    return userRepository.save(currentUser);
                }).switchIfEmpty(Mono.error(new RuntimeException("user not foud")));
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.existsById(id)
                .flatMap(exists -> {
                    if (exists) {
                        return userRepository.deleteById(id);
                    } else {
                        return Mono.error(new RuntimeException("User not found"));
                    }
                });
    }
// END
}
