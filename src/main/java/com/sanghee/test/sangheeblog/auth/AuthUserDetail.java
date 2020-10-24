package com.sanghee.test.sangheeblog.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class AuthUserDetail implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 946745782922017096L;
	private UserSession user;

	public AuthUserDetail(UserSession user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		return user.getPasswd();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return user.getStatus().contains("Y");
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(()->{return "ROLE_"+user.getRole();});
		return collection;
	}
	
}
