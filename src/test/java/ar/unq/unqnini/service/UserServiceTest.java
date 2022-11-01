package ar.unq.unqnini.service;
import ar.unq.unqnini.model.RecoverPasswordData;
import ar.unq.unqnini.model.LoginData;
import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.repository.LoginRepository;
import ar.unq.unqnini.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService loginService;

    private UserData userData;
    private JSONObject jsonResult;
    private LoginData loginData;
    private RecoverPasswordData recoverPasswordData;
    private HttpStatus httpStatus;
    private List<JSONObject> errors;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userData = UserData.builder().username("TEST").fullname("SRTESTER").password("TESTER").cuit("1234").businessName("REST").businessAddress("API").build();
        loginData = LoginData.builder().userName("TEST").password("TEST").build();
        recoverPasswordData = RecoverPasswordData.builder().userName("TEST").build();
        jsonResult = new JSONObject();
        errors = new ArrayList<>();
    }

    @Test
    void validateData() throws JSONException {
        when(loginRepository.findById("TEST")).thenReturn(Optional.ofNullable(loginData));
        jsonResult = new JSONObject().put("areTheUserDetailsCorrect", true);
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.validateData(loginData));
    }

    @Test
    void validateDataInvalidUser() throws JSONException {
        when(loginRepository.findById("TEST")).thenReturn(Optional.empty());
        errors.add(new JSONObject().put("field", "userName").put("defaultMessage", "no es un usuario registrado en el sistema"));
        jsonResult = new JSONObject().put("error", "Bad Request");
        jsonResult.put("errors", new JSONArray(errors));
        httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.validateData(loginData));
    }

    @Test
    void validateDataInvalidPassword() throws JSONException {
        when(loginRepository.findById("TEST")).thenReturn(Optional.ofNullable(LoginData.builder().userName("TEST").password("TESTER").build()));
        errors.add(new JSONObject().put("field", "password").put("defaultMessage", "no es correcto"));
        jsonResult = new JSONObject().put("error", "Bad Request");
        jsonResult.put("errors", new JSONArray(errors));
        httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.validateData(loginData));
    }

    @Test
    void recoverPassword() throws JSONException {
        when(loginRepository.findById("TEST")).thenReturn(Optional.ofNullable(loginData));
        jsonResult = new JSONObject().put("password", loginData.getPassword());
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.recoverPassword(recoverPasswordData));
    }

    @Test
    void recoverPasswordInvalidUser() throws JSONException {
        when(loginRepository.findById("TEST")).thenReturn(Optional.empty());
        jsonResult = new JSONObject().put("error", "Bad Request");
        httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.recoverPassword(recoverPasswordData));
    }

    @Test
    void getUser() throws JSONException {
        when(userRepository.findById("TEST")).thenReturn(Optional.ofNullable(userData));
        jsonResult = new JSONObject().put("username", userData.getUsername());
        jsonResult.put("password", userData.getPassword());
        jsonResult.put("fullname", userData.getFullname());
        jsonResult.put("cuit", userData.getCuit().toString());
        jsonResult.put("businessName", userData.getBusinessName());
        jsonResult.put("businessAddress", userData.getBusinessAddress());
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.getUser("TEST"));
    }

    @Test
    void getUserNonExistentUser() throws JSONException {
        when(userRepository.findById("TEST")).thenReturn(Optional.empty());
        jsonResult = new JSONObject().put("error", "Bad Request");
        httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        assertEquals(responseEntity, loginService.getUser("TEST"));
    }

    @Test
    void modifiedInformation() {
        when(userRepository.save(userData)).thenReturn(userData);
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(new JSONObject().toString(), httpStatus);
        assertEquals(responseEntity, loginService.modifiedInformation(userData));
    }
}
