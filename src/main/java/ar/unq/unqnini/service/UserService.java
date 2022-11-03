package ar.unq.unqnini.service;
import ar.unq.unqnini.model.DataOfOrder;
import ar.unq.unqnini.model.RecoverPasswordData;
import ar.unq.unqnini.model.LoginData;
import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.repository.LoginRepository;
import ar.unq.unqnini.repository.UserRepository;
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
public class UserService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UserRepository userRepository;

    JSONObject jsonResult;
    HttpStatus httpStatus;

    public ResponseEntity<String> validateData(LoginData userData) throws JSONException {
        Optional<LoginData> loginData = loginRepository.findById(userData.getUserName());
        jsonResult = new JSONObject().put("error", "Bad Request");
        httpStatus = HttpStatus.BAD_REQUEST;
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
        Optional<LoginData> loginData = loginRepository.findById(recoverPasswordData.getUserName());

        if(loginData.isEmpty()) {
            jsonResult = new JSONObject().put("error", "Bad Request");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            jsonResult = new JSONObject().put("password", loginData.get().getPassword());
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(jsonResult.toString(), httpStatus);
    }

    public ResponseEntity<String> addUser(UserData userData) throws JSONException{
        List<JSONObject> errors = new ArrayList<>();


        if(userRepository.findById(userData.getUsername()).isPresent()) {
            jsonResult = new JSONObject().put("error", "Bad Request");
            errors.add(createErrorBody("username", "ya existe en el sistema"));
            jsonResult.put("errors", new JSONArray(errors));
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            jsonResult = new JSONObject().put("userRegitered", "true");
            httpStatus = HttpStatus.OK;
            userRepository.save(userData);
        }

        return new ResponseEntity<>(jsonResult.toString(), httpStatus);
    }

    public ResponseEntity<String> getUser(String userName) throws JSONException {
        Optional<UserData> userData = userRepository.findById(userName);

        if(userData.isEmpty()) {
            jsonResult = new JSONObject().put("error", "Bad Request");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            jsonResult = new JSONObject().put("username", userData.get().getUsername());
            jsonResult.put("password", userData.get().getPassword());
            jsonResult.put("fullname", userData.get().getFullname());
            jsonResult.put("cuit", userData.get().getCuit().toString());
            jsonResult.put("businessName", userData.get().getBusinessName());
            jsonResult.put("businessAddress", userData.get().getBusinessAddress());
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(jsonResult.toString(), httpStatus);
    }

    public ResponseEntity<String> modifiedInformation(UserData userData) {
        httpStatus = HttpStatus.OK;
        userRepository.save(userData);
        return new ResponseEntity<>(new JSONObject().toString(), httpStatus);
    }

    private JSONObject createErrorBody(String field, String Message) throws JSONException {
        return new JSONObject().put("field", field).put("defaultMessage", Message);
    }
}
