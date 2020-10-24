package com.sanghee.test.sangheeblog.repository;

import com.sanghee.test.sangheeblog.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Optional<Member> findByEmail(String email);
}
