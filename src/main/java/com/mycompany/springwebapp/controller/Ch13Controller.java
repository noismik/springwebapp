package com.mycompany.springwebapp.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Member;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.interceptor.Login;
import com.mycompany.springwebapp.service.Ch13BoardService;
import com.mycompany.springwebapp.service.Ch13MemberService;
import com.mycompany.springwebapp.service.Ch13MemberService.JoinResult;
import com.mycompany.springwebapp.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
   @Resource
   private Ch13BoardService boardService;
   @Autowired
   private Ch13MemberService memberService;
   //dispatcher 쪽에서 13-properties.xml 설정 파일이 있어야됨
   //<context:property-placeholder location="classpath:properties/*.properties"/>가 WebApplicationContext 단위로 사용되기 때문
   @Value("${file.upload.dir}")
   private String fileUploadDir;
  
   
   @RequestMapping("/content")
   public String content() {
      
      return "ch13/content";
   }
   @GetMapping("/writeBoard")
   @Login
   public String writeBoardForm() {
	   return "ch13/writeBoardForm";
   }
   
   
   @PostMapping("/writeBoard")
   @Login
   public String writeBoard(Ch13Board board, HttpSession session) throws Exception {
	   	  Ch13Member member = (Ch13Member)session.getAttribute("ch13Login");
	   	  board.setMid(member.getMid());
	   
	   	  MultipartFile mf = board.getBattach();
	   	  
	   	  if(!mf.isEmpty()) {
	   		  //브라우저에서 선택한 파일 이름으로 설정
	   		  board.setBattachoname(mf.getOriginalFilename());
	   		  
	   		  //파일의 형식(MIME 타입)을 설정(image/jpeg, image/png, ...)
	   		  board.setBattachtype(mf.getContentType());
	   		  //방법1(첨부 파일을 서버 파일 시스템에 저장)
	   		  String saveFileName = new Date().getTime() + "-" + mf.getOriginalFilename();
	   		  board.setBattachsname(saveFileName);
	   		  
	   		  File file = new File(fileUploadDir + "/" + saveFileName);
	   		  mf.transferTo(file);
	   		  
	   		  //방법2(첨부 파일을 DB에 직접 저장)
	   		  //board.setBattachdata(mf.getBytes());
	   		  
	   		  
	   		  
	   	  }
	   	  
 	      boardService.write(board);
	      log.info("저장된 bno: " + board.getBno());
      
      return "redirect:/ch13/getBoardList";
   }
   
   @GetMapping("/getBoardList") 
   public String getBoardList(@RequestParam(defaultValue="1") int pageNo,
		   Model model) {
	   int totalBoardNum = boardService.getTotalBoardNum();
	   Ch13Pager pager = new Ch13Pager(10, 5, totalBoardNum, pageNo);
	   
	   
	   List<Ch13Board> list = boardService.getList(pager);
	   
	   model.addAttribute("pager", pager);
	   model.addAttribute("boards",list);
	   
	   
	   return "ch13/boardList";
	   
   }
   @GetMapping("/detailBoard")
   public String detailBoard(int bno, Model model) {
	   Ch13Board board = boardService.getBoard(bno);
	   model.addAttribute("board", board);
	   
	   if(board.getBattachdata() !=null) {
		   //0과 1로 구성된 바이너리 데이터를 base64 문자열로 변환
		   String base64Img = Base64.getEncoder().encodeToString(board.getBattachdata());
		   model.addAttribute("base64Img", base64Img);
	   }
	   return "ch13/detailBoard";
	   
	   
   }
   
   @GetMapping("/filedownload1")
   public void filedownload1(int bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
	    Ch13Board board = boardService.getBoard(bno);
	   
	    String fileOriginalName= board.getBattachoname();
        String fileSaveName = board.getBattachsname();
        String filePath = fileUploadDir + "/" + fileSaveName;
        
        //응답 헤드에 Content-Type 추가
        String mimeType = board.getBattachtype();
        response.setContentType(mimeType);
        
        //응답 헤드에 한글 이름의 파일명을 ISO-8859-1 문자셋으로 인코딩해서 추가
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.contains("Trident")|| userAgent.contains("MSIE")) {
           //IE
        	fileOriginalName = URLEncoder.encode(fileOriginalName,"UTF-8");
        }else {
          //Chrome, Edge, FireFox, Safari 
        	fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"),"ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileOriginalName + "\"" );//response.setHeader가 없으면 브라우저에 바로 보여줄 수 있으면 보여줌
                                                                             // 바로 보여줄 수 없으면 파일이 다운로드됨
        
        //응답 본문에 파일데이터 싣기
		OutputStream os = response.getOutputStream();
		Path path = Paths.get(filePath);
		Files.copy(path, os);
		os.flush();
		os.close();
   }
   @GetMapping("/filedownload2")
   public void filedownload2(int bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
	   Ch13Board board = boardService.getBoard(bno);
	   
	   String fileOriginalName= board.getBattachoname();
	   
	   //응답 헤드에 Content-Type 추가
	   String mimeType = board.getBattachtype();
	   response.setContentType(mimeType);
	   
	   //응답 헤드에 한글 이름의 파일명을 ISO-8859-1 문자셋으로 인코딩해서 추가
	   String userAgent = request.getHeader("User-Agent");
	   if(userAgent.contains("Trident")|| userAgent.contains("MSIE")) {
		   //IE
		   fileOriginalName = URLEncoder.encode(fileOriginalName,"UTF-8");
	   }else {
		   //Chrome, Edge, FireFox, Safari 
		   fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"),"ISO-8859-1");
	   }
	   response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileOriginalName + "\"" );//response.setHeader가 없으면 브라우저에 바로 보여줄 수 있으면 보여줌
	   // 바로 보여줄 수 없으면 파일이 다운로드됨
	   
	   //응답 본문에 파일데이터 싣기
	   OutputStream os = response.getOutputStream();
	   os.write(board.getBattachdata());
	   os.flush();
	   os.close();
   }
   
   
   @GetMapping("/updateBoard") 
   public String updateBoard() {
	   Ch13Board board = boardService.getBoard(2);
	   board.setBtitle("오늘은 금요일");
	   board.setBcontent("힘들다, 집가고 싶다.");
	   boardService.modify(board);
	   return "redirect:/ch13/content";
	   
   }
   
   @GetMapping("/deleteBoard") 
   public String deleteBoard() {
	   int bno = 1;
	   boardService.remove(bno);
	   return "redirect:/ch13/content";
	   
   }
   
   @GetMapping("/join")
   public String joinForm() {
	   return "ch13/joinForm";
	   
   }
   
   @PostMapping("/join")
   public String join(Ch13Member member, Model model) {
	   JoinResult result = memberService.join(member);
	   if(result == JoinResult.FAIL_DUPLICATED_MID) {
		   String error = "중복된 MID가 존재합니다.";
		   model.addAttribute("error", error);
		   return "ch13/joinForm";
	   } else {
	   
		   memberService.join(member);
		   return "redirect:/ch13/content";
	   }
	   
   }
   
   @GetMapping("/login")
   public String loginForm() {
	   return "ch13/loginForm";
   }
   
   @PostMapping("/login")
   public String login(Ch13Member member, Model model, HttpSession session) {
	   LoginResult result = memberService.login(member);
	   String error = "";
	   if(result == LoginResult.FAIL_MID) {
		  error = "MID가 없습니다.";
	   } else if(result == LoginResult.FAIL_ENABLED) {
		  error = "MID가 비활성화 되어 있습니다.";
	   } else if(result == LoginResult.FAIL_MPASSWORD) {
		  error = "MPASSWORD가 틀립니다.";
	   } else {
		   Ch13Member dbMember = memberService.getMember(member.getMid());
		   session.setAttribute("ch13Login", dbMember);
		   return "redirect:/ch13/content";
		   
	   }
	   
	   model.addAttribute("error", error);
	   return "ch13/loginForm";
   }
   
   @GetMapping("/logout")
   public String logout(HttpSession session) {
	   session.removeAttribute("ch13Login");
	   return "redirect:/ch13/content";
   }
}
