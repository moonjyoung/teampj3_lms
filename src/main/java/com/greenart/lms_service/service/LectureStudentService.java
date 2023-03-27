package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.entity.score.ScoreStudentEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.AttendInfoStudentRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.score.ScoreMasterRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.repository.score.ScoreStudentRepository;
import com.greenart.lms_service.repository.viewRepo.LectureStudentAttendVoVIEWRepository;
import com.greenart.lms_service.repository.viewRepo.LectureStudentCateListScoreVoVIEWRepository;
import com.greenart.lms_service.repository.viewRepo.LecturestudentdaoRepository;
import com.greenart.lms_service.vo.finalGrade.vo.MessageVO;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDAO;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDaoVO;
import com.greenart.lms_service.vo.lectureStudent.UpdateStudentLectureScoreCateVO;
import com.greenart.lms_service.vo.student.LectureStudentAttendVO;
import com.greenart.lms_service.vo.student.LectureStudentAttendVoVIEW;
import com.greenart.lms_service.vo.student.LectureStudentCateListScoreVO;
import com.greenart.lms_service.vo.student.LectureStudentCateListScoreVoVIEW;
import com.greenart.lms_service.vo.student.LectureStudentTotalInfoVO;
import com.greenart.lms_service.vo.student.LsTotalFinalVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureStudentService {
    private final LecturestudentdaoRepository lecDaoRepo;
    private final LectureInfoRepository lectureInfoRepository;
    private final StudentRepository stuRepo;
    private final ScoreMasterRepository scoreMasStuRepo;
    private final ScoreStudentRepository scoreStuRepo;
    private final ClassRegisterRepository classRegiRepo;
    private final AttendInfoStudentRepository attStuRepo;
    private final LectureStudentAttendVoVIEWRepository lsavViewRepo;
    private final LectureStudentCateListScoreVoVIEWRepository lsClViewRepo;


    // 교수번호(P타입-repo)- (수강신청)강의entity-학생mb_entity(S타입)-stu_entity(전공명) 실패
    // view로 만들어서_view를 담는 vo를 만들어서 필요한 정보만 api명세서 대로 만듬
    // seq, 학번 , 이름, 학년, 전공명
    // 내강의 수강생 조회
    public List<LectureStudentDaoVO> getLectureStudentList(Long liSeq) {
        LectureInfoEntity entity = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        Long proSeq = entity.getProfessor().getMbSeq();
        List<LectureStudentDAO> dao = lecDaoRepo.findByProSeqAndLiSeq(proSeq, liSeq);
        List<LectureStudentDaoVO> daoVO = new ArrayList<>();

        for(int i=0; i<dao.size(); i++) {
            LectureStudentDaoVO vo = LectureStudentDaoVO.builder()
                // .proName(dao.get(i).getProName())
                // .liName(dao.get(i).getLiName())
                // .liCode(dao.get(i).getLiCode())
                .seq(dao.get(i).getMbSeq())
                .stuName(dao.get(i).getStuName())
                .stuId(dao.get(i).getMbId())
                .stuSubject(dao.get(i).getStuSubject())
                .stuGrade(dao.get(i).getStuGrade())
                .build();
            // vo.setStatus(true);
            // vo.setMessage("성공");
            daoVO.add(vo);
        }
        return daoVO;
    }

    // 내강의 수강생 검색
    public List<LectureStudentDaoVO> searchLectureStudent(Long liSeq, String stuName) {
        List<LectureStudentDAO> dao = lecDaoRepo.findByLiSeqAndStuNameContains(liSeq,stuName);
        List<LectureStudentDaoVO> daoVO = new ArrayList<>();

        for(int i=0; i<dao.size(); i++) {
            LectureStudentDaoVO vo = LectureStudentDaoVO.builder()
                .seq(dao.get(i).getMbSeq())
                .stuName(dao.get(i).getStuName())
                .stuId(dao.get(i).getMbId())
                .stuSubject(dao.get(i).getStuSubject())
                .stuGrade(dao.get(i).getStuGrade())
                .build();
            daoVO.add(vo);
        }

        return daoVO;
    }
    
    // 내 강의 수강생 성적 및 출결조회(개인)
    public LsTotalFinalVO getlectureStudentTotalList(Long liSeq, Long mbSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findByLiSeq(liSeq);
        StudentEntity student = stuRepo.findByMbSeq(mbSeq);

        // List<StudentEntity> stuList = new ArrayList<>();        
        // for(ClassRegisterEntity data : classRegiRepo.findByLectureInfo(lecture)) {
        //     stuList.add(data.getStudent());
        // }

        // 이거를 담는 vo를 필요함
        LsTotalFinalVO result = new LsTotalFinalVO();
        LectureStudentTotalInfoVO data = new LectureStudentTotalInfoVO();
            // for(StudentEntity stuData : stuList) {
                // LectureStudentTotalInfoVO lsAtt = new LectureStudentTotalInfoVO();
                List<LectureStudentAttendVO> attStuList = new ArrayList<>();
                //출석/전체출석
                for(LectureStudentAttendVoVIEW entity : lsavViewRepo.findByLiSeqAndMbSeq(lecture.getLiSeq(), student.getMbSeq())) {
                    LectureStudentAttendVO attStu = new LectureStudentAttendVO();
                    attStu.setAttendCount(entity.getAttendCount());
                    attStu.setAttendCountTotal(entity.getAttendCountTotal());
                    attStuList.add(attStu);
                }
                //  - 상위카테고리번호 카테고리이름(중간), 점수, 최대점수 없으면 미입력
                List<LectureStudentCateListScoreVO> lecCateList = new ArrayList<>();
                for(LectureStudentCateListScoreVoVIEW smData :lsClViewRepo.findByLiSeqAndMbSeq(lecture.getLiSeq(), student.getMbSeq()) ) {
                    LectureStudentCateListScoreVO lscList = new LectureStudentCateListScoreVO();
                    lscList.setScoreCateSeq(smData.getSmasSeq());
                    lscList.setScoreCateName(smData.getSmasName());
                    lscList.setScore(smData.getSstuScore());
                    lscList.setMaxScore(smData.getSmasScore());
                    // lscList.get이 아니라 set 왜냐하면 vo에 entity의 값을 담기위해서
                    lecCateList.add(lscList);
                }
                // lsAtt.setStuId(stuData.getMbId());
                // lsAtt.setStuName(stuData.getMbName());
                // lsAtt.setStuGrade(stuData.getStuGrade());
                // lsAtt.setStuSubject(stuData.getStuSubject());
                data.setStuId(student.getMbId());
                data.setStuName(student.getMbName());
                data.setStuGrade(student.getStuGrade());
                data.setStuSubject(student.getStuSubject());
                data.setLectureStudentCateListScoreVO(lecCateList);
                data.setLectureStudentAttendVO(attStuList);
                // 최종점수는 어디서? 없으면 미입력입니다.
                ClassRegisterEntity crEntity = classRegiRepo.findByfinalName(lecture.getLiSeq(), student.getMbSeq());
                data.setFinalScore(crEntity.getFinalGrade().getFgName()); 
                // }
                result.setStatus(true);
                result.setMessage("조회 성공");
                result.setData(data);

        return result;
    }

    // 내 강의 수강생 성적 수정 
    public MessageVO updateStudentLectureScore(Long liSeq, Long mbSeq, UpdateStudentLectureScoreCateVO data) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        StudentEntity student = stuRepo.findById(mbSeq).orElseThrow(() -> new CustomException("존재하지 않는 학생입니다."));

        ScoreMasterEntity scoreMas = scoreMasStuRepo.findById(data.getScoreCateSeq()).orElseThrow(() -> new CustomException("존재하지 않는 평가입니다."));
        ScoreStudentEntity scoreStu = scoreStuRepo.findByScoreMasterAndStudent(scoreMas, student);

        if (scoreStu==null) throw new CustomException("강의-학생 정보가 맞지 않습니다.");

        if(scoreMas.getSmasScore() < data.getCateStuScore() || 0 > data.getCateStuScore()) {
            return MessageVO.builder()
                .status(false)
                .message("기준최대 점수를 넘을 수 없습니다. 0 보다 낮은 점수를 입력할수 없습니다.")
                .code(HttpStatus.BAD_REQUEST)
                .build();
        }
        else {
            scoreStu.ChangeScore(data.getCateStuScore());
            scoreStuRepo.save(scoreStu);
    
            return MessageVO.builder()
                    .status(true)
                    .message("변경되었습니다.")
                    .code(HttpStatus.ACCEPTED)
                    .build();
        }
    }

}
