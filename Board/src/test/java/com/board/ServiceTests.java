package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@SpringBootTest
public class ServiceTests {
	
	private final BoardMapper boardMapper;
	
	@Autowired
	private ServiceTests(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Test
	public void testOfRegisterBoard() {
		
		BoardDTO params = new BoardDTO();
		params.setIdx(1L);
		params.setTitle("testOfRegisterBoard 타이틀");
		params.setContent("setContent 테스트");
		params.setWriter("테스터");
		
		int queryResult = 0;
		
		if(params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		System.out.println(queryResult);
	}
	
}
