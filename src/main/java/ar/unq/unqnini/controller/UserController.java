package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.LoginData;
import ar.unq.unqnini.model.RecoverPasswordData;
import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login/validate")
    @ResponseBody
    public ResponseEntity<String> validateData(@Validated @RequestBody LoginData loginData) throws JSONException { return userService.validateData(loginData); }

    @PostMapping("/login/recover")
    @ResponseBody
    public ResponseEntity<String> recoverPassword(@Validated @RequestBody RecoverPasswordData recoverPasswordData) throws JSONException { return userService.recoverPassword(recoverPasswordData); }

    @PostMapping("/user/register")
    @ResponseBody
    public ResponseEntity<String> addUser(@Validated @RequestBody UserData user) throws JSONException { return userService.addUser(user); }

    @GetMapping("/user/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) throws JSONException { return userService.getUser(username); }

    @PostMapping("/user/modifiedInformation")
    @ResponseBody
    public ResponseEntity<String> modifiedInformation(@Validated @RequestBody UserData userData) throws JSONException { return userService.modifiedInformation(userData); }
}