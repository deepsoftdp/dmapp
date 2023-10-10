package pl.deepsoft.dmapp.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.deepsoft.dmapp.Dto.LoginDTO;
import pl.deepsoft.dmapp.Dto.UserDTO;
import pl.deepsoft.dmapp.Response.LoginResponse;
import pl.deepsoft.dmapp.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path ="/save")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO)
    {
        String result = userService.addUser(userDTO);

        if (result.equals("Zarejestrowano pomyślnie")) {
            return ResponseEntity.ok("{\"success\": true}");
        } else if (result.equals("Użytkownik o podanym adresie e-mail już istnieje.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"success\": false, \"message\": \"" + result + "\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"Wystąpił błąd podczas rejestracji.\"}");
        }
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUser(loginDTO);

        String redirectUrl;

        if (loginResponse.getRoles().equals("ROLE_USER")) {
            redirectUrl = "/user";
        } else if (loginResponse.getRoles().equals("ROLE_ADMIN")) {
            redirectUrl = "/admin";
        } else {
            // Domyślny przypadek, na przykład, jeśli nie ma przypisanej roli
            redirectUrl = "/";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", redirectUrl);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }





    @GetMapping(path = "/logout")
    public ResponseEntity<?> logoutUser() {

        SecurityContextHolder.clearContext();


        return ResponseEntity.ok("Wylogowano pomyślnie.");
    }

    @GetMapping("/user")
    public ResponseEntity<String> userPage() {
        return ResponseEntity.ok("To jest strona użytkownika.");
    }
}


