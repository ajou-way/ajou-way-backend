//package com.ajouway.domain.persistence.repository;
//
//import com.ajouway.common.exception.CustomException;
//import com.ajouway.common.exception.CustomExceptionInfo;
//import com.ajouway.domain.persistence.entity.user.User;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByLoginId(String loginId);
//
//    default User findByLoginIdOrThrow(final String loginId){
//        return findByLoginId(loginId)
//                .orElseThrow(() -> new CustomException(CustomExceptionInfo.NOT_FOUND_USER));
//    }
//
//    default User findByIdOrThrow(final Long id){
//        return findById(id)
//                .orElseThrow(() -> new CustomException(CustomExceptionInfo.NOT_FOUND_USER));
//    }
//}
