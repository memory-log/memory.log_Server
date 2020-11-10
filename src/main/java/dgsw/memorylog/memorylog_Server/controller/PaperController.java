package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.vo.http.Response;
import dgsw.memorylog.memorylog_Server.domain.vo.http.ResponseData;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;
import dgsw.memorylog.memorylog_Server.service.Paper.PaperService;
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
import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(value = "롤링페이퍼 API")
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping("/createPaper")
    @ApiOperation(value = "롤링페이퍼 생성")
    public Response createPaper(@RequestBody @Valid PaperCreatePaperVo paperCreatePaperVo, HttpServletRequest request) {
        try {
            Member member = (Member)request.getAttribute("member");
            paperCreatePaperVo.setMember(member);
            paperService.createPaper(paperCreatePaperVo);
            Map<String, Object> data = new HashMap<String, Object>();
            return new Response(HttpStatus.OK, "롤링페이퍼 생성 성공.");
        } catch (HttpClientErrorException e){
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @GetMapping("/getMyPaper")
    @ApiOperation(value = "내 롤링페이퍼 글 조회")
    public Response getMyPaper(HttpServletRequest request) {
        try {
            Member member = (Member)request.getAttribute("member");
            List<Paper> papers = paperService.getMyPaper(member.getName());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Papers", papers);
            return new ResponseData(HttpStatus.OK, "롤링페이퍼 조회 성공", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @GetMapping("/showPaper")
    @ApiOperation(value = "롤링페이퍼 조회")
    public Response showPaper() {
        try {
            List<Paper> papers = paperService.showPaper();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Papers", papers);
            return new ResponseData(HttpStatus.OK, "롤링페이퍼 조회 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @GetMapping("/searchPaper")
    @ApiOperation(value = "롤링페이퍼 검색")
    public Response searchPaper(@RequestParam @Valid String target) {
        try {
            List<Paper> searchedByTitle = paperService.searchPaperByTitle(target);
            List<Paper> searchedByName = paperService.searchPaperByMemberName(target);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("SearchedByTitle", searchedByTitle);
            data.put("SearchedByName", searchedByName);
            return new ResponseData(HttpStatus.OK, "롤링페이퍼 검색 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
