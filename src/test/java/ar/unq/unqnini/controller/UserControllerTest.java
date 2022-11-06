package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Username;
import ar.unq.unqnini.model.LoginData;
import ar.unq.unqnini.model.UserData;
import ar.unq.unqnini.service.UserService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    private UserService loginService;

    @InjectMocks
    private UserController userController;

    private Username recoverPasswordData;
    private JSONObject jsonResult;
    private LoginData loginData;
    private HttpStatus httpStatus;
    private List<JSONObject> errors;
    private UserData userData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginData = LoginData.builder().userName("TEST").password("TEST").build();
        userData = UserData.builder().username("TEST").fullname("SRTESTER").password("TESTER").cuit("1234").businessName("REST").businessAddress("API").build();
        recoverPasswordData = Username.builder().username("TEST").build();
        jsonResult = new JSONObject();
        errors = new ArrayList<>();
    }

    @Test
    void validateData() throws JSONException {
        jsonResult = new JSONObject().put("areTheUserDetailsCorrect", true);
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.validateData(loginData)).thenReturn(responseEntity);
        assertEquals(responseEntity, userController.validateData(loginData));
    }

    @Test
    void validateDataInvalidUserName() throws JSONException {
        errors.add(new JSONObject().put("field", "userName").put("defaultMessage", "no es un usuario registrado en el sistema"));
        jsonResult = new JSONObject().put("error", "Bad Request");
        jsonResult.put("errors", new JSONArray(errors));
        httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.validateData(loginData)).thenReturn(responseEntity);
        assertEquals(responseEntity, userController.validateData(loginData));
    }

    @Test
    void recoverPassword() throws JSONException {
        jsonResult = new JSONObject().put("password", loginData.getPassword());
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.recoverPassword(recoverPasswordData)).thenReturn(responseEntity);
        assertEquals(responseEntity, userController.recoverPassword(recoverPasswordData));
    }

    @Test
    void getUser() throws JSONException {
        jsonResult = new JSONObject().put("username", userData.getUsername());
        jsonResult.put("password", userData.getPassword());
        jsonResult.put("fullname", userData.getFullname());
        jsonResult.put("cuit", userData.getCuit().toString());
        jsonResult.put("businessName", userData.getBusinessName());
        jsonResult.put("businessAddress", userData.getBusinessAddress());
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.getUser("TEST")).thenReturn(responseEntity);
        assertEquals(responseEntity, userController.getUser("TEST"));
    }

    @Test
    void modifiedInformation() throws JSONException {
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(new JSONObject().toString(), httpStatus);
        when(loginService.modifiedInformation(userData)).thenReturn(responseEntity);
        assertEquals(responseEntity, userController.modifiedInformation(userData));
    }
}
