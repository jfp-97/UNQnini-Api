package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login/validate")
    @ResponseBody
    public ResponseEntity<String> validateData(@Validated @RequestBody UserData userData) throws JSONException {
        return loginService.validateData(userData);
    }
}
