package com.sanghee.test.sangheeblog.repository;

import com.sanghee.test.sangheeblog.model.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Integer> {

	public int countByWriterAndCreateDateBetween(int writer, Date start, Date end);
	public boolean existsByContentSeqAndWriter(int seq, int writer); 
	public Page<Content> findAllByBoardSeqAndStatus(int boardSeq, String status, Pageable pageable);
	@Query(value = "SELECT content From Content content"
			+ " left join fetch content.commentList "
			+ " where content.contentSeq = :contentSeq")
	public Optional<Content> findByContentSeq(int contentSeq); 
}

