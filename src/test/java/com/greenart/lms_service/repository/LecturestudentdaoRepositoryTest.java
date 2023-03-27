package com.greenart.lms_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.greenart.lms_service.repository.viewRepo.LecturestudentdaoRepository;

@DataJpaTest // jpa의 쿼리만 검증됨 - gradle 설정해주야함
public class LecturestudentdaoRepositoryTest {
    @Autowired 
    private LecturestudentdaoRepository lecturestudentdaoRepository;

    @Test
    void ts() {
        // given - 값은 생성하고        

        // when -값이 들어왔는지 확인해야 하는 메소드
        lecturestudentdaoRepository.findAll();

        // then- 검증 assertions - equals해서 같으면 오키 틀리면 쿼리문 노
    }
}
