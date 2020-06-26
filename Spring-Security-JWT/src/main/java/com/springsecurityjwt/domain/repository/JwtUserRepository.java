package com.springsecurityjwt.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurityjwt.domain.entity.UserMstEntity;

@Repository
public interface JwtUserRepository extends JpaRepository<UserMstEntity, Long> {

	UserMstEntity findByUserName(String userName);

}
