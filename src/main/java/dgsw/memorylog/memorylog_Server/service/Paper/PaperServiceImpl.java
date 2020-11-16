package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.entity.PaperComment;
import dgsw.memorylog.memorylog_Server.domain.entity.PaperLike;
import dgsw.memorylog.memorylog_Server.domain.repository.PaperLikeRepository;
import dgsw.memorylog.memorylog_Server.domain.repository.PaperRepository;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperHitPaperVo;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperModifyPaperVo;
import dgsw.memorylog.memorylog_Server.enums.PaperScope;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaperServiceImpl implements PaperService{
    @Autowired
    private PaperRepository paperRepo;

    @Autowired
    private PaperLikeRepository paperLikeRepo;

    /**
     * 롤링페이퍼 생성
     * @return 롤링페이퍼 인덱스
     */
    @Override
    public void createPaper(PaperCreatePaperVo paperCreatePaperVo) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Paper mappedPaper = modelMapper.map(paperCreatePaperVo, Paper.class);
            paperRepo.save(mappedPaper);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 조회
     * @return 내 롤링페이퍼 글
     */
    @Override
    public List<Paper> getMyPaper(String name) {
        try {
            return paperRepo.findAllByMember_Name(name);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 조회
     * @return 하나의 롤링페이퍼 글
     */
    @Override
    public Paper showOnePaper(Integer paper_idx) {
        try {
            return paperRepo.findByIdx(paper_idx);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 조회
     * @return 공개 범위가 ONLYCODE인 롤링페이퍼 글
     */
    @Override
    public Paper showOnlyCodePaper(Integer paper_idx, String code) {
        try {
            return paperRepo.findByIdxAndCode(paper_idx, code);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 조회
     * @return 공개 범위가 PUBLIC인 롤링페이퍼 글
     */
    @Override
    public List<Paper> showPublicPaper() {
        try {
            return paperRepo.findAllByScope(PaperScope.PUBLIC);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<PaperHitPaperVo> showHitPaper() {
        try {
            List<Paper> papers = paperRepo.findAllByScope(PaperScope.PUBLIC);
            List<PaperHitPaperVo> filteredPaper = new ArrayList<>();
            for (Paper paper: papers) {
                Integer likeCount = paperLikeRepo.countByPaperIdx(paper.getIdx());
                ModelMapper modelMapper = new ModelMapper();
                PaperHitPaperVo mappedPaper = modelMapper.map(paper, PaperHitPaperVo.class);
                mappedPaper.setLikeCount(likeCount);
                filteredPaper.add(mappedPaper);
            }

            filteredPaper.sort((o1, o2) -> {
                if (o1.getLikeCount().equals(o2.getLikeCount()))
                    return 0;
                return o1.getLikeCount() > o2.getLikeCount() ? -1 : 1;
            });
            return filteredPaper;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 롤링페이퍼 검색
     * @return 롤링페이퍼 정보
     */
    @Override
    public List<Paper> searchPaperByTitle(String title) {
        try {
            return paperRepo.findAllByTitleContaining(title);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Paper> searchPaperByMemberName(String name) {
        try {
            return paperRepo.findAllByMember_Name(name);
        } catch (Exception e) {
            throw e;
        }
    }

    // @TODO
    @Override
    public void modifyPaper(Member member, Integer paperIdx, PaperModifyPaperVo paperModifyPaperVo) {
//        try {
//            Paper paper = paperRepo.findByIdx(paperIdx);
//            if (paper == null) {
//                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "ㄹㅗㄹ 없음.");
//            }
//
//            if (paper.getMember() != member) {
//                throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "권한 없음.");
//            }
//
//            if (paperModifyPaperVo == null) {
//                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "검증 오류.");
//            }
//
//            paper.setCreatedAt();
//            paperComment.setComment(Optional.ofNullable(paperCommentModifyVo.getComment()).orElse(paperComment.getComment()));
//            paperComment.setLocationX(Optional.ofNullable(paperCommentModifyVo.getLocationX()).orElse(paperComment.getLocationX()));
//            paperComment.setLocationY(Optional.ofNullable(paperCommentModifyVo.getLocationY()).orElse(paperComment.getLocationY()));
//            paperComment.setFontFamily(Optional.ofNullable(paperCommentModifyVo.getFontFamily()).orElse(paperComment.getFontFamily()));
//            paperComment.setImage(Optional.ofNullable(paperCommentModifyVo.getImage()).orElse(paperComment.getImage()));
//            paperComment.setUpdatedAt(new Date());
//            paperCommentRepo.save(paperComment);
//        } catch (Exception e) {
//            throw e;
//        }
    }
}
