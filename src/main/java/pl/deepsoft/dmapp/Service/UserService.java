package pl.deepsoft.dmapp.Service;
import pl.deepsoft.dmapp.Dto.LoginDTO;
import pl.deepsoft.dmapp.Dto.UserDTO;
import pl.deepsoft.dmapp.Entity.User;
import pl.deepsoft.dmapp.Response.LoginResponse;

import java.util.List;


public interface UserService {

    String addUser(UserDTO userDTO);

    LoginResponse loginUser(LoginDTO loginDTO);

    List<User> getAllUsers();

    boolean activateUser(int userId);

    boolean deactivateUser(int userId);

    boolean deleteUser(int userId);
}
