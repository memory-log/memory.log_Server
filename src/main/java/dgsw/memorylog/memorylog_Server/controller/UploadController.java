package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.domain.vo.http.ResponseData;
import dgsw.memorylog.memorylog_Server.service.Upload.UploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Api(value = "업로드 Api")
public class UploadController {

    @Autowired
    private UploadServiceImpl uploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "이미지 업로드")
    public ResponseData uploadFile(@Valid @RequestParam("file") MultipartFile file) {
        try {
            String fileName = uploadService.storeFile(file);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("fileName", fileName);

            return new ResponseData(HttpStatus.OK, "업로드 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @GetMapping("/images/{fileName:.+}")
    @ApiOperation(value = "이미지 다운로드 ( 필요에 한에서 )")
    public ResponseEntity downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = uploadService.loadFileAsResource(fileName);

        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch(IOException ex) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"서버 오류.");
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"")
                .body(resource);
    }
}
