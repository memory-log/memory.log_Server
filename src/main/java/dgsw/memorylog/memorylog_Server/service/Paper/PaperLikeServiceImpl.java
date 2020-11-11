package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.entity.PaperLike;
import dgsw.memorylog.memorylog_Server.domain.repository.PaperLikeRepository;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperUpdateLikeVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperLikeServiceImpl implements PaperLikeService{
    @Autowired
    private PaperLikeRepository paperLikeRepo;


    /**
     * 롤링페이퍼 좋아요 여부에 따른 좋아요 / 좋아요 취소 메서드 실행
     * @return 없음
     */
    @Override
    public void updateLike(PaperUpdateLikeVo paperUpdateLikeVo) {
        try {
            Integer isLike = paperLikeRepo.countByPaperIdxAndMemberIdx(
                    paperUpdateLikeVo.getPaper().getIdx(),
                    paperUpdateLikeVo.getMember().getIdx());
            if (isLike == 1)
                deleteLike(paperUpdateLikeVo);
            else
                addLike(paperUpdateLikeVo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 좋아요
     * @return 없음
     */
    @Override
    public void addLike(PaperUpdateLikeVo paperUpdateLikeVo) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            PaperLike mappedPaperLike = modelMapper.map(paperUpdateLikeVo, PaperLike.class);
            paperLikeRepo.save(mappedPaperLike);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 좋아요 취소
     * @return 없음
     */
    @Override
    public void deleteLike(PaperUpdateLikeVo paperUpdateLikeVo) {
        try {
            paperLikeRepo.deleteByPaperIdxAndMemberIdx(
                        paperUpdateLikeVo.getPaper().getIdx(),
                        paperUpdateLikeVo.getMember().getIdx());
        } catch (Exception e) {
            throw e;
        }
    }
}
