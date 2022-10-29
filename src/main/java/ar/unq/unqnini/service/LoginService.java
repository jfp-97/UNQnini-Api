package ar.unq.unqnini.service;
import ar.unq.unqnini.model.RecoverPasswordData;
import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public ResponseEntity<String> validateData(UserData userData) throws JSONException {
        Optional<UserData> loginData = loginRepository.findById(userData.getUserName());
        JSONObject jsonResult = new JSONObject().put("error", "Bad Request");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<JSONObject> errors = new ArrayList<>();

        if(loginData.isEmpty()) {
            errors.add(createErrorBody("userName", "no es un usuario registrado en el sistema"));
            jsonResult.put("errors", new JSONArray(errors));
        } else if(!Objects.equals(userData.getPassword(), loginData.get().getPassword())){
            errors.add(createErrorBody("password", "no es correcto"));
            jsonResult.put("errors", new JSONArray(errors));
        } else {
            jsonResult = new JSONObject().put("areTheUserDetailsCorrect", true);
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(jsonResult.toString(), httpStatus);
    }

    public ResponseEntity<String> recoverPassword(RecoverPasswordData recoverPasswordData) throws JSONException {
        Optional<UserData> loginData = loginRepository.findById(recoverPasswordData.getUserName());
        JSONObject jsonResult;
        HttpStatus httpStatus;

        if(loginData.isEmpty()) {
            jsonResult = new JSONObject().put("error", "Bad Request");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            jsonResult = new JSONObject().put("password", loginData.get().getPassword());
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(jsonResult.toString(), httpStatus);
    }

    private JSONObject createErrorBody(String field, String Message) throws JSONException {
        return new JSONObject().put("field", field).put("defaultMessage", Message);
    }
}
