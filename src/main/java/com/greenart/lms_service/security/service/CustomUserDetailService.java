package com.greenart.lms_service.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.entity.member.StaffEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.repository.member.ProfessorRepository;
import com.greenart.lms_service.repository.member.StaffRepository;
import com.greenart.lms_service.repository.member.StudentRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentEntity student = studentRepository.findByMbId(username);
        ProfessorEntity professor = professorRepository.findByMbId(username);
        StaffEntity staff = staffRepository.findByMbId(username);

        if (student!=null) {
            return createUserDetails(student);
        }
        else if (professor!=null) {
            return createUserDetails(professor);
        }
        else if (staff!=null) {
            return createUserDetails(staff);
        }
        else {
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }
    }
    public UserDetails createUserDetails(StudentEntity student) {
        return User.builder().username(student.getMbId())
                .password(passwordEncoder.encode(student.getMbPwd()))
                .roles("S")
                .build();
    }
    public UserDetails createUserDetails(ProfessorEntity professor) {
        return User.builder().username(professor.getMbId())
                .password(passwordEncoder.encode(professor.getMbPwd()))
                .roles("P")
                .build();
    }
    public UserDetails createUserDetails(StaffEntity staff) {
        return User.builder().username(staff.getMbId())
                .password(passwordEncoder.encode(staff.getMbPwd()))
                .roles("U")
                .build();
    }
}