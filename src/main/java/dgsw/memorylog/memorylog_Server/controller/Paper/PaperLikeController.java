package dgsw.memorylog.memorylog_Server.controller.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.repository.PaperRepository;
import dgsw.memorylog.memorylog_Server.domain.vo.http.Response;
import dgsw.memorylog.memorylog_Server.domain.vo.http.ResponseData;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperUpdateLikeVo;
import dgsw.memorylog.memorylog_Server.enums.UserLike;
import dgsw.memorylog.memorylog_Server.service.Paper.PaperLikeService;
import dgsw.memorylog.memorylog_Server.service.Paper.PaperLikeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Api(value = "롤링페이퍼 좋아요 API")
@RestController
@RequestMapping("/paperLike")
public class PaperLikeController {
    @Autowired
    private PaperRepository paperRepo;

    @Autowired
    private PaperLikeServiceImpl paperLikeService;

    @GetMapping("/getLikeCount")
    @ApiOperation(value = "좋아요 수 조회")
    public Response getLikeCount(@RequestParam @Valid Integer paper_idx) {
        try {
            Integer count = paperLikeService.getLikeCount(paper_idx);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("likeCount", count);
            return new ResponseData(HttpStatus.OK, "좋아요 수 조회 성공", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @PostMapping("/updateLike")
    @ApiOperation(value = "좋아요 / 좋아요 취소")
    public Response updateLike(@RequestParam @Valid Integer paper_idx, HttpServletRequest request) {
        try {
            Member member = (Member)request.getAttribute("member");
            Paper paper = paperRepo.findByIdx(paper_idx);
            PaperUpdateLikeVo paperUpdateLikeVo = new PaperUpdateLikeVo();
            paperUpdateLikeVo.setPaper(paper);
            paperUpdateLikeVo.setMember(member);
            Enum<UserLike> userLike = paperLikeService.updateLike(paperUpdateLikeVo);
            if (userLike == UserLike.YES)
                return new Response(HttpStatus.OK, "좋아요 성공");
            else
                return new Response(HttpStatus.OK, "좋아요 취소 성공");
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
