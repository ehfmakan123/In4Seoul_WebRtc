package com.ssafy.api.service;

import com.ssafy.api.dto.ConsultantDto;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.AdminRepository;
import com.ssafy.db.repository.AdminRepositorySupport;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminRepositorySupport adminRepositorySupport;


    @Override
    public Staff getStaffById(String userId) {
        Optional<Staff> admin = adminRepositorySupport.findStaffById(userId);
        if (admin.isPresent()) {
            return admin.get();
        } else
            return null;
    }

    @Override
    public List<ConsultantDto> getConsultantList() {
        return adminRepositorySupport.getConsultantList();
    }

    @Override
    public ConsultantDto getConsultant(int id) {

        return adminRepositorySupport.getConsultant(id);


    }

    @Override
    public int updateConsultant(ConsultantDto dto) {
        return 0;
    }
}
