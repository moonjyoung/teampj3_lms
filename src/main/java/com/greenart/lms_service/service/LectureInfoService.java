package com.greenart.lms_service.service;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.vo.AllLectureResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureInfoService {
    private final LectureInfoRepository liRepo;

    public AllLectureResponse showLecList() {
        
        
        
        return null;
    }
}
