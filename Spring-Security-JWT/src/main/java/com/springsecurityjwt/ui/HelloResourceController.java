package com.springsecurityjwt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityjwt.schemaobjects.AuthenticationRequestSO;
import com.springsecurityjwt.schemaobjects.AuthenticationResponseSO;
import com.springsecurityjwt.schemaobjects.InfoSO;
import com.springsecurityjwt.schemaobjects.UserRegisterSO;
import com.springsecurityjwt.security.JwtUserDetailsService;
import com.springsecurityjwt.util.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloResourceController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/getInfo", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getInfo() {

		System.out.println(" *** getInfo called *** ");

		InfoSO infoSO = new InfoSO();
		infoSO.setData("Information is coming from the server.");
		return ResponseEntity.ok(infoSO);

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestSO authenticationRequestSO)
			throws Exception {

		System.out.println(" *** authenticate called *** ");
		if (authenticationRequestSO != null) {
			System.out.println(" *** UserName =" + authenticationRequestSO.getUserName());
			System.out.println(" *** Password =" + authenticationRequestSO.getPassword());
		}

		// the below method is to authenticate the user
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequestSO.getUserName(), authenticationRequestSO.getPassword()));

		} catch (BadCredentialsException e) {
			System.out.println(" *** BadCredentialsException Error =" + e.getMessage());
			return ResponseEntity.ok(new AuthenticationResponseSO());
		} catch (Exception e) {
			System.out.println(" *** Exception Error =" + e.getMessage());
		}

		// If the user is valid. Generate the token
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequestSO.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponseSO(jwt));

	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@RequestBody UserRegisterSO userRegisterSO) throws Exception {

		System.out.println(" *** registerUser called *** ");

		if (userRegisterSO != null && !StringUtils.isEmpty(userRegisterSO.getUserName())
				&& !StringUtils.isEmpty(userRegisterSO.getPassword())) {

			jwtUserDetailsService.registerUser(userRegisterSO);
			return ResponseEntity.ok("Created");
		}
		return ResponseEntity.ok(new UserRegisterSO());
	}
}
