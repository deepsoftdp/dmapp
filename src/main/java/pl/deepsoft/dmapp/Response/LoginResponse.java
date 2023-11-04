package pl.deepsoft.dmapp.Response;

import lombok.*;

@ToString
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    String message;
    Boolean status;
    String roles;



    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
