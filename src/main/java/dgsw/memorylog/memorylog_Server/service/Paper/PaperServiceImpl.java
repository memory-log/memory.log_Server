package dgsw.memorylog.memorylog_Server.service.Paper;

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
    public Integer createPaper(Integer member_idx, PaperCreatePaperVo paperCreatePaperVo) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Paper mappedPaper = modelMapper.map(paperCreatePaperVo, Paper.class);
            Paper createdPaper = paperRepo.save(mappedPaper);
            return createdPaper.getIdx();
        } catch (Exception e) {
            throw e;
        }
    }
}
