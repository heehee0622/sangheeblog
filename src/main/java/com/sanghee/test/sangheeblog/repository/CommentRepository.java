package com.sanghee.test.sangheeblog.repository;

import com.sanghee.test.sangheeblog.model.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
	public boolean existsByCommentSeqAndWriter(int commentSeq, int writer); 
	public List<Comments> findByContentSeqAndStatus(int contentSeq, String status);
}	

