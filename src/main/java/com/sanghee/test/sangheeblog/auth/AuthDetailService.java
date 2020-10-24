package com.sanghee.test.sangheeblog.auth;


import com.sanghee.test.sangheeblog.model.entity.Member;
import com.sanghee.test.sangheeblog.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthDetailService implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> memberOptional = memberRepository.findByEmail(username);
		Member member = memberOptional.orElseThrow(() -> new UsernameNotFoundException("사용자가 존재 하지 않습니다."));
		UserSession userSession = modelMapper.map(member, UserSession.class);
		return new AuthUserDetail(userSession);
	}

}
