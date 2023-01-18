package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.exception.AuthenticationException;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardUpdate.do")
public class BoardUpdateController {
	private final BoardService service;
	
	@GetMapping
	public String updateForm(@RequestParam("what") int boNo, Model model) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardEdit";
	}
	
	@PostMapping
	public String boardUpdate(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
		, Errors errors
		, Model model
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				int rowcnt = service.modifyBoard(board);
				if(rowcnt>0) {
					viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				}else {
					model.addAttribute("message", "서버 오류, 쫌따 다시!");
					viewName = "board/boardEdit";
				}
			}catch (AuthenticationException e) {
				model.addAttribute("message", "비번 오류");
				viewName = "board/boardEdit";
			}
		}else {
			viewName = "board/boardEdit";
		}
		return viewName;
	}
}



















