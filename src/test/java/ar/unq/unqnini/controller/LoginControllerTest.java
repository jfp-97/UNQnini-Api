package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.RecoverPasswordData;
import ar.unq.unqnini.model.LoginData;
import ar.unq.unqnini.service.LoginService;
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

class LoginControllerTest {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private RecoverPasswordData recoverPasswordData;
    private JSONObject jsonResult;
    private LoginData loginData;
    private HttpStatus httpStatus;
    private List<JSONObject> errors;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginData = LoginData.builder().userName("TEST").password("TEST").build();
        recoverPasswordData = RecoverPasswordData.builder().userName("TEST").build();
        jsonResult = new JSONObject();
        errors = new ArrayList<>();
    }

    @Test
    void validateData() throws JSONException {
        jsonResult = new JSONObject().put("areTheUserDetailsCorrect", true);
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.validateData(loginData)).thenReturn(responseEntity);
        assertEquals(responseEntity, loginController.validateData(loginData));
    }

    @Test
    void validateDataInvalidUserName() throws JSONException {
        errors.add(new JSONObject().put("field", "userName").put("defaultMessage", "no es un usuario registrado en el sistema"));
        jsonResult = new JSONObject().put("error", "Bad Request");
        jsonResult.put("errors", new JSONArray(errors));
        httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.validateData(loginData)).thenReturn(responseEntity);
        assertEquals(responseEntity, loginController.validateData(loginData));
    }

    @Test
    void recoverPassword() throws JSONException {
        jsonResult = new JSONObject().put("password", loginData.getPassword());
        httpStatus = HttpStatus.OK;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResult.toString(), httpStatus);
        when(loginService.recoverPassword(recoverPasswordData)).thenReturn(responseEntity);
        assertEquals(responseEntity, loginController.recoverPassword(recoverPasswordData));
    }
}
