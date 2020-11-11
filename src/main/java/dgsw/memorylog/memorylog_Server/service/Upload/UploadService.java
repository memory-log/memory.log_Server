package dgsw.memorylog.memorylog_Server.service.Upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public String storeFile(MultipartFile file);
    public Resource loadFileAsResource(String fileName);
}
