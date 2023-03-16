//package com.greenart.lms_service.finalscore.service;
//
//import com.greenart.lms_service.entity.ClassRegisterEntity;
//import com.greenart.lms_service.entity.LectureInfoEntity;
//import com.greenart.lms_service.entity.member.MemberBasicEntity;
//import com.greenart.lms_service.entity.member.StudentEntity;
//import com.greenart.lms_service.entity.score.ScoreCateEntity;
//import com.greenart.lms_service.entity.score.ScoreStandardEntity;
//import com.greenart.lms_service.finalscore.VO.FinalScoreVO;
//import com.greenart.lms_service.finalscore.VO.ScoreInfoVO;
//import com.greenart.lms_service.repository.ClassRegisterRepository;
//import com.greenart.lms_service.repository.LectureInfoRepository;
//import com.greenart.lms_service.repository.member.MemberBasicRepository;
//import com.greenart.lms_service.repository.member.StudentRepository;
//import com.greenart.lms_service.repository.score.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FinalScoreService {
//    private final FinalGradeRepository finalGradeRepository;
//    private final ScoreCateRepository scoreCateRepository;
//    private final ScoreMasterRepository scoreMasterRepository;
//    private final ScoreStandardRepository scoreStandardRepository;
//    private final ScoreStudentRepository scoreStudentRepository;
//    private final MemberBasicRepository memberBasicRepository;
//    private final LectureInfoRepository lectureInfoRepository;
//    private final ClassRegisterRepository classRegisterRepository;
//
//    public List<FinalScoreVO> getFinalScore(String code) {
//        LectureInfoEntity lecture = lectureInfoRepository.findByLiCode(code);
//        List<ClassRegisterEntity> studentInClass = classRegisterRepository.findByCrLiSeq(lecture.getLiSeq());
//        List<FinalScoreVO> finalScore = new ArrayList<>();
//        // 이름 score_cate, sc_name
//        // 이름의 만점 score_master
//        // 설명 score_master s_mas_name
//        // 성적평가만점 score_standard, ss_score_max
//        // 성적 score_student s.stu_score
//        for(int i=0; i<studentInClass.size(); i++) {
//            MemberBasicEntity student = memberBasicRepository.findByMbSeq(studentInClass.get(i).getCrStuSeq());
//            ScoreStandardEntity scoreStandard = scoreStandardRepository.find
//            ScoreInfoVO scoreInfoVO = ScoreInfoVO.builder()
//                    .name()
//                    .build();
//            FinalScoreVO response = FinalScoreVO.builder()
//                    .studentName(student.getMbName())
//                    .studentCode(student.getMbId())
//                    .scoreList()
//                    .build();
//        }
//        return null;
//    }
//
//}
