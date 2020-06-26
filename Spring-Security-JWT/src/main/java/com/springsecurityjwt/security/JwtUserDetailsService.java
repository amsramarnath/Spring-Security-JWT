package com.springsecurityjwt.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurityjwt.domain.entity.UserMstEntity;
import com.springsecurityjwt.domain.repository.JwtUserRepository;
import com.springsecurityjwt.schemaobjects.UserRegisterSO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private JwtUserRepository jwtUserRepository;

	// The below method is in-memory implementation

	/*
	 * @Override
	 * 
	 * public UserDetails loadUserByUsername(String userName) throws
	 * UsernameNotFoundException
	 * 
	 * { return new User("amar","amar",new ArrayList<>()); }
	 */

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserMstEntity entity = jwtUserRepository.findByUserName(userName);
		if (entity == null) {
			throw new UsernameNotFoundException(userName);
		}
		UserDetails user = User.withUsername(entity.getUserName()).password(entity.getPassword())
				.authorities(new ArrayList<>()).build();
		return user;
	}

	public void registerUser(UserRegisterSO userRegisterSO) {

		System.out.println(" *** Inside registerUser *** ");

		UserMstEntity userMstEntity = new UserMstEntity();
		userMstEntity.setUserName(userRegisterSO.getUserName());
		userMstEntity.setPassword(new BCryptPasswordEncoder().encode(userRegisterSO.getPassword()));
		jwtUserRepository.save(userMstEntity);
	}

}
