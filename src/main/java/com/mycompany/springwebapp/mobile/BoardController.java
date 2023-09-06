package com.mycompany.springwebapp.mobile;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mobile/board")
public class BoardController {
	@Autowired
	private Ch13BoardService boardService;
	
	@GetMapping("/prepare")
	public String prepare(HttpServletRequest request) throws Exception {
		log.info("실행");
		
		for(int i=1; i<=1000; i++) {
			String fileName ="photo" + (i%17 + 1) + ".jpg";
			String filePath = "/resources/image/photo/" + fileName;
			filePath = request.getServletContext().getRealPath(filePath);
			byte[] fileData = Files.readAllBytes(Paths.get(filePath));
			log.info("fileSize: " + fileData.length);
			Ch13Board board = new Ch13Board();
			board.setBtitle("유럽 사진" + i);
			board.setBcontent("유럽 여행" + i + "패키지 상품 안내입니다");
			board.setMid("user");
			board.setBattachoname(fileName);
			board.setBattachtype("image/jpeg");
			board.setBattachdata(fileData);
			boardService.write(board);
			
		}
		
		return "redirect:/";
	}

}
