package com.sanghee.test.sangheeblog.repository;


import com.sanghee.test.sangheeblog.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	public List<Board>findAllByStatusOrderByBoardSeqDesc(String status);
}
