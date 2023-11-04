package pl.deepsoft.dmapp.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.deepsoft.dmapp.Entity.User;
import pl.deepsoft.dmapp.Exception.AppException;
import pl.deepsoft.dmapp.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/activateUser/{userId}")
    public ResponseEntity<String> activateUser(@PathVariable int userId) {
        boolean success = userService.activateUser(userId);
        if (success) {
            return ResponseEntity.ok("Użytkownik został aktywowany.");
        } throw new AppException("Nie można aktywować użytkownika.");
        }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deactivateUser/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable int userId) {
        boolean success = userService.deactivateUser(userId);
        if (success) {
            return ResponseEntity.ok("Użytkownik został dezaktywowany.");
        } else {
            throw new AppException("Nie można dezaktywować użytkownika.");
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        boolean success = userService.deleteUser(userId);
        if (success) {
            return ResponseEntity.ok("Użytkownik został usunięty.");
        } else {
            throw new AppException("Nie można usunąć użytkownika.");
        }
    }

}

