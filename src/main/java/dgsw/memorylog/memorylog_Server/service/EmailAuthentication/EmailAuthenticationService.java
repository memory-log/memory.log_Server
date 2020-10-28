package dgsw.memorylog.memorylog_Server.service.EmailAuthentication;

public interface EmailAuthenticationService {
    public void sendEmailCode(String email);
    public void sendEmail(String email, String title, String content);
}
