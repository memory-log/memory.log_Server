package dgsw.memorylog.memorylog_Server.service.EmailAuthentication;

public interface EmailAuthenticationService {
    public void sendEmailCode(String email);
    public boolean emailConfirm(String code);
}
