package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.repository.PaperRepository;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService{
    @Autowired
    private PaperRepository paperRepo;

    /**
     * 롤링페이퍼 생성
     * @return 롤링페이퍼 인덱스
     */
    @Override
    public void createPaper(Member member, PaperCreatePaperVo paperCreatePaperVo) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Paper mappedPaper = modelMapper.map(paperCreatePaperVo, Paper.class);
            paperRepo.save(mappedPaper);
        } catch (Exception e) {
            throw e;
        }
    }
}
