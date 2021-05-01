package com.board.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
public class BoardController {
	
	private final BoardService  boardService;
	
	private BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
//	@GetMapping(value = "/board/write.do")
//	public String openBoardWrite(Model model) {
//		
//		String title = "제목";
//		String content = "내용";
//		String writer = "홍길동";
//		
//		model.addAttribute("t", title);
//		model.addAttribute("c", content);
//		model.addAttribute("w", writer);
//		
//		return "board/write";
//	}
	
	@GetMapping(value = "/board/write.do")
	public String openboard(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		
		if(idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if(board == null) {
				return "redirect:/board/list.do";
			}
		}
		
		return "board/write";
	}
	
	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/board/list.do";
	}
}
