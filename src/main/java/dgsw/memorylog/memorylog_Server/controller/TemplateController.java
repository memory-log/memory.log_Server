package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.service.EmailAuthentication.EmailAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Controller
@Api(value = "템플릿 Api")
public class TemplateController {
    @Autowired
    private EmailAuthenticationService emailAuthenticationService;

    @GetMapping("/confirm")
    @ApiOperation(value = "인증 메일 검증")
    public String emailConfirm(@RequestParam("code") String code, Model model) {
        try {
            boolean isExist = emailAuthenticationService.emailConfirm(code);

            model.addAttribute("isConfirm", isExist);
            return "confirm";
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
