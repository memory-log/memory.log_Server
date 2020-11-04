package dgsw.memorylog.memorylog_Server.service.EmailAuthentication;

import dgsw.memorylog.memorylog_Server.domain.repository.MemberRepository;
import dgsw.memorylog.memorylog_Server.lib.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import dgsw.memorylog.memorylog_Server.domain.repository.EmailAuthenticationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

@Service
public class EmailAuthenticationServiceImpl implements EmailAuthenticationService {
    @Autowired
    private EmailAuthenticationRepository emailAuthenticationRepo;

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${java.encrypt.key.sha256}")
    private String secretKey;

    @Value("${server.url}")
    private String serverUrl;

    /**
     * 인증 메일 발송
     * @param email
     */
    @Override
    public void sendEmailCode(String email) {
        try {
            EmailAuthentication emailAuthentication = emailAuthenticationRepo.findByEmail(email);
            Date now = new Date();
            Calendar expireAt = Calendar.getInstance();

            expireAt.setTime(now);
            expireAt.add(Calendar.MINUTE, 5);

            if (emailAuthentication == null) {
                emailAuthentication = new EmailAuthentication();
            }

            String code = Encrypt.sha256(email + expireAt.getTime() + secretKey);
            String href = serverUrl + "/member/email/confirm?code=" + code;

            emailAuthentication.setExpireTime(expireAt.getTime());
            emailAuthentication.setEmail(email);
            emailAuthentication.setCode(code);
            emailAuthentication.setIsCertified(false);

            emailAuthenticationRepo.save(emailAuthentication);

            sendEmail(email, "Memory-log 본인 확인", new StringBuffer().append("<a href='").append(href)
                    .append("' target='blank'>")
                    .append("<p>링크를 눌러 인증하세요.</p></a>").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(String email, String title, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setFrom(senderEmail);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean emailConfirm(String code) {
        if (code == null) {
            return false;
        }

        EmailAuthentication emailAuthentication = emailAuthenticationRepo.findByCode(code);

        if (emailAuthentication == null || emailAuthentication.getExpireTime().getTime() < new Date().getTime()) {
            return false;
        }

        emailAuthentication.setCode(null);
        emailAuthentication.setIsCertified(true);
        emailAuthenticationRepo.save(emailAuthentication);

        return true;
    }
}
