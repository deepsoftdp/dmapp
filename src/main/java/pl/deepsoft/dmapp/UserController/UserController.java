package pl.deepsoft.dmapp.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.deepsoft.dmapp.Dto.LoginDTO;
import pl.deepsoft.dmapp.Dto.UserDTO;
import pl.deepsoft.dmapp.Exception.AppException;
import pl.deepsoft.dmapp.Exception.NotAcceptableException;
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
        } else throw new NotAcceptableException("Użytkownik o takim mailu: "+ userDTO.getMail()+" istneije");
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
           throw new AppException("Coś poszło nie tak");
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


