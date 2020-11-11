package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.vo.http.Response;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;
import dgsw.memorylog.memorylog_Server.service.PaperComment.PaperCommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Api(value = "페이지 코멘트 API")
@RestController
@RequestMapping("/comment")
public class PaperCommentController {

    @Autowired
    private PaperCommentServiceImpl paperCommentService;

    @PostMapping("/create")
    @ApiOperation(value = "코멘트 생성")
    public Response createpaper(HttpServletRequest request) {
        try {
            Member member = (Member)request.getAttribute("member");
//            paperCommentService.create(member, paperCreatePaperVo);
            Map<String, Object> data = new HashMap<String, Object>();
            return new Response(HttpStatus.OK, "코멘트 생성 성공.");
        } catch (HttpClientErrorException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
