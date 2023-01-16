package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/member/memberUpdate.do")
@RequiredArgsConstructor
@Controller
public class MemberUpdateController {
	@Inject
	private final MemberService service ;
   
   @GetMapping
   public String updateForm(
         @SessionAttribute("authMember") MemberVO authMember
         ,Model model
      ) {
      
      
      MemberVO member = service.retrieveMember(authMember.getMemId());
      
      model.addAttribute("member", member);
      
      return "member/memberForm";
      
   }
   
   @PostMapping
   public String updateProcess(
         @Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
         , BindingResult errors
         ,@Valid ProdVO prod
         , Model model
         ,HttpSession session 
      )throws IOException {
      
      String viewName = null;
      
      
      if(!errors.hasErrors()) {
         ServiceResult result = service.modifyMember(member);
         switch (result) {
         case INVAILDPASSWORD:
        	 model.addAttribute("message", "비밀번호 오류");
            viewName = "member/memberForm";
            break;
         case FAIL:
        	 model.addAttribute("message", "서버 오류, 쫌따 다시.");
            viewName = "member/memberForm";
            break;
            
         default:
            MemberVO modifiedMember = service.retrieveMember(member.getMemId());
            session.setAttribute("authMember", modifiedMember);
            viewName = "redirect:/mypage.do";
            break;
         }
      }else {
         viewName = "member/memberForm";
      }
      
      return viewName;
   }
}






















