package dgsw.memorylog.memorylog_Server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(value = "롤링페이퍼 API")
@RestController
@RequestMapping("/paper")
public class PaperController {

//    @PostMapping("/createpaper")
//    @ApiOperation(value = "롤링페이퍼 생성")
//    public ResponseData createpaper(HttpServletRequest request) {
//        try {
//
//        } catch (HttpClientErrorException e){
//            throw e;
//        } catch (Exception e) {
//            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
//        }
//    }
}
