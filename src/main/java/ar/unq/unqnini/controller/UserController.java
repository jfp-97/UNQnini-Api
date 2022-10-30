package ar.unq.unqnini.controller;

import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.service.UserService;
import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{username}")
    public UserData getUser(@PathVariable String username){
        try {
            return userService.getUser(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product does not exist"));
        } catch (MongoException exc){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available");
        }
    }
}
