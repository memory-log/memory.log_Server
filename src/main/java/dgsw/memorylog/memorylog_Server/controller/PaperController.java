package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.vo.http.ResponseData;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;
import dgsw.memorylog.memorylog_Server.service.Paper.PaperServiceImpl;
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
@Api(value = "롤링페이퍼 API")
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping("/createpaper")
    @ApiOperation(value = "롤링페이퍼 생성")
    public ResponseData createpaper(@RequestBody @Valid PaperCreatePaperVo paperCreatePaperVo, HttpServletRequest request) {
        try {
            Member member = (Member)request.getAttribute("member");
            Integer paperIdx = paperService.createPaper(member.getIdx(), paperCreatePaperVo);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("paperIdx", paperIdx);
            return new ResponseData(HttpStatus.OK, "롤링페이퍼 생성 성공.", data);
        } catch (HttpClientErrorException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
