package ar.unq.unqnini.service;

import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserData> getUser(String username) { return userRepository.findByUsername(username); }
}
