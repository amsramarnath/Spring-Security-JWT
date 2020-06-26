package com.springsecurityjwt.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USR_MST_JWT")
public class UserMstEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USRMSTJWT_ID")
	private Long usrMstJwtId;

	@Column(name = "USR_NAME")
	private String userName;

	@Column(name = "USR_PWD")
	private String password;

	public Long getUsrMstJwtId() {
		return usrMstJwtId;
	}

	public void setUsrMstJwtId(Long usrMstJwtId) {
		this.usrMstJwtId = usrMstJwtId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
