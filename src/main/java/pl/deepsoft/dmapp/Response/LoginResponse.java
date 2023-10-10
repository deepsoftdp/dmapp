package pl.deepsoft.dmapp.Response;

public class LoginResponse {
    String message;
    Boolean status;
    String roles;

    public LoginResponse(String message, Boolean status, String roles) {
        this.message = message;
        this.status = status;
        this.roles = roles;
    }

    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", roles='" + roles + '\'' +
                '}';
    }
}
