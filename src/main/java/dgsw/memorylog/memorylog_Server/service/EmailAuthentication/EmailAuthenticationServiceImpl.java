package dgsw.memorylog.memorylog_Server.service.EmailAuthentication;

import dgsw.memorylog.memorylog_Server.domain.entity.EmailAuthentication;
import dgsw.memorylog.memorylog_Server.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import dgsw.memorylog.memorylog_Server.domain.repository.EmailAuthenticationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
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

    /**
     * 인증 메일 발송
     * @param email
     */
    @Override
    public void sendEmailCode(String email) {
        try {
//            EmailAuthentication emailAuthentication = emailAuthenticationRepo.findByEmail(email);
//            Date now = new Date();
//            Calendar expireAt = Calendar.getInstance();
//
//            expireAt.setTime(now);
//            expireAt.add(Calendar.MINUTE, 5);
//
//            if (emailAuthentication == null) {
//                emailAuthentication = new EmailAuthentication();
//            }
//
//            emailAuthentication.setExpireTime(expireAt.getTime());
//
            sendEmail(email, "Memory-log 본인 확인", "<p>테스트코드</p>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(String email, String title, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(content, "text/html");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setFrom(senderEmail);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
