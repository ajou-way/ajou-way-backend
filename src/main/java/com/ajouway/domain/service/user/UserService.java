//package com.ajouway.service.user;
//
//import com.ajouway.domain.enums.ProviderType;
//import com.ajouway.domain.enums.UserRole;
//import com.ajouway.domain.persistence.entity.user.User;
//import com.ajouway.domain.persistence.repository.UserRepository;
//import com.ajouway.dto.auth.SignupRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void registerUser(final SignupRequest signupRequest) {
//        userRepository.save(
//                User.from(signupRequest.email(), passwordEncoder.encode(signupRequest.password()), signupRequest.name(),
//                        UserRole.ROLE_USER));
//    }
//}
