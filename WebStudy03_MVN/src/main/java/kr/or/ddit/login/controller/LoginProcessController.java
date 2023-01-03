package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;


/**
 * 
 * 1. 검증에 통과하지 못했을 경우 => 다시 로그인 폼으로 이동함 
 * 2. 인증에 통과하지 못했을 경우 => 다시 로그인 폼으로 이동함
 *    - 비밀번호 오류라는 상태를 가정하고 메시지 전달 -> alert 함수로 메시지 출력
 *    - 이전에 입력받은 아이디의 상태는 유지함 (아이디는 오류가 아니라고 가정하고, 아이디는 초기화하지 않고 그냥 유지될수있게끔)  
 * 3. 인증완료시에 웰컴페이지로 이동함      
 *
 */


@Controller
public class LoginProcessController  {

   
   //검증 => AuthenticateService.java파일로 옮겨갔음 
/*   private boolean authenticate(MemberVO member) {  //MemberVO내용 가져와서 해당 내용들 검증 
      return member.getMemId().equals(member.getMemPass()); //MemberVO에 memId값이 memPass 값과 같으면 true 리턴!!!!
   }
*/
   private AuthenticateService service  = new AuthenticateServiceImpl(); //service와 의존성 맺기 

   
   //필드 나가면 걍 대부분 post로 해야됨. 보안때문에.. 
   @RequestMapping(value="/login/loginProcess.do", method=RequestMethod.POST)
   public String process(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException {
   
      //session 정보 담을수있는 session객체 만들기 
      if(session.isNew()) { //이 요청이 최초요청일 경우 (최초요청이라는 것은 없으므로 잘못된 요청일 가능성이 큼. 이게 최초요청일수 없음이라고 알려줘야됨) 
         resp.sendError(400, "로그인 폼이 없는데 어떻게 로그인을 하지???");
         return null;
      }
      
      //1단계 파라미터 값 가져와서 해당 파라미터 값 상태 괜찮은지 검증하기 
      String memId = req.getParameter("memId"); //앞단에서  받아온 아이디
      String memPass =  req.getParameter("memPass"); //앞단에서  받아온 비밀번호 
      String saveId = req.getParameter("saveId"); //넘어온 값이 있거나 없거나 아이디 저장 
      MemberVO member = new MemberVO(); //memberVO객체 만들어서 memberVO속에 있는 내용물을 이 페이지에서 사용할 수 있도록 세팅해두기 
      member.setMemId(memId); //memberVO속에 파라미터로 받아온 memId 넣어주기! 
      member.setMemPass(memPass); //memberVO속에 파라미터로 받아온 memPass 넣어주기! 
      // => 이로써 vo에는 넘어온 파라미터 값이 담겼음 
      // ** (reflection은 다음에 한번 한댔음) reflection : 파라미터 값과 vo가 서로 이름이 동일하면 beanUtils사용해서 간단하게 끝낼수있음 
      
      //검증작업
      boolean valid = validate(member); //MemberVO 내용물 가지고 validate메소드 실행 
      String viewName = null; //일단 경로는 null값으로 잡아두고 
      
      if(valid) { //1차검증(vo에 저장되어있는 아이디와 비밀번호 값이 비어있지 않을 경우 일단 1차 통과함 ( valid = true )  => 이제 그 저장된 값이랑  같이 2차 검증 들어가야됨 
         
         try {
            ServiceResult result =  service.authenticate(member);
            
            
            //2번단계 : 검증 
            //4번단계 : view결정 
            if(ServiceResult.OK.equals(result)) { //(authenticate여기로 member다시 넘기기 => memId와 memPass가 서로 같다? 그럼 2차도 통과완료!) => 인증성공시
               
               //"아이디저장" 체크했을 경우
               Cookie saveIdCookie = new Cookie("saveId", member.getMemId()); //쿠키하나 만들기 
   //            ex)www[blog]naver.com
               saveIdCookie.setDomain("localhost");//도메인만들고
               saveIdCookie.setPath(req.getContextPath()); //path만들고
               int maxAge = 0;
               if(StringUtils.isNoneBlank(saveId)) { //성공시=> 쿠키를 만들어서 5일동안 유지해야됨
                  maxAge = 60*60*24*5;  // 쿠키 5일동안 유지
               }
               saveIdCookie.setMaxAge(maxAge); // 쿠키의 만료 시한을 결정
               resp.addCookie(saveIdCookie);  
               
               
               session.setAttribute("authMember", member); //session에다가 memberVO 내용들을 "authMember"이라는 이름으로 저장해두기 => index.jsp에서 사용되고있음
               viewName = "redirect:/";
            
            }else {//인증실패시 
               session.setAttribute("validId", memId); //session에 validId라는 이름으로 memId 저장하기 
                                             // 만약 아이디/비번값이 틀릴 경우 브라우저에 그대로 남아있어야 하는 조건때문에 jsp(앞단)에서 value="${validId }" 사용함
               session.setAttribute("message", "비밀번호 오류 "); // session에   message라는 이름으로  "비밀번호 오류 "라고 저장하기 => 이렇게 되면  jsp에서 message로 받아서 데이터를 사용할 수 ㅇㅆ음 
               viewName = "redirect:/login/loginForm.jsp";
            }
         }catch(UserNotFoundException e) {
            session.setAttribute("message", "존재하지 않는 회원입니다."); 
            viewName = "redirect:/login/loginForm.jsp";
         }
            
         
      }else { //통과 못했을 경우 ((vo에 저장되어있는 아이디와 비밀번호 값이 비어있다??) 아래 실행 
         session.setAttribute("message", "아이디나 비밀번호 누락"); //session에 message라는 이름으로 "아이디나 비밀번호 누락"값 저장해두기 
         viewName = "redirect:/login/loginForm.jsp";
      }
      
      
      
      //5번단계 
      return viewName;
      /*// 규칙! redirect:/ 로 시작되는 viewName은 redirect로 넘기기
      if(viewName.startsWith("redirect:")) { //viewName이 redirect:로 시작 할 경우  
         viewName = viewName.substring("redirect:".length()); // redirect:길이 만큼 잘라라 => redirect: 이부분 삭제
         resp.sendRedirect(req.getContextPath() + viewName); //   req.getContextPath() +  /login/loginForm.jsp
      }else { //forward할 경우
         req.getRequestDispatcher(viewName).forward(req, resp);
      }*/
   }
   


   
   private boolean validate(MemberVO member) { //  받아오는 member의 형태 => MemberVO 형태임

      boolean valid = true;
      
      //객체 상태를 검증하는 조건
      /*if(member.getMemId()==null || member.getMemId().isEmpty()) { //member가 가지는 getMemId가 null이면  (= 로그인이 실패하면)
         valid = false;
      }*/
      
      //위 방식보다 Apache Commons Lang 라이브러리를 써서 간단하게 처리하는 방식도 있음!!!!  
      if(StringUtils.isBlank(member.getMemId())) {// (넘어온파라미터값memId이 이상한거면 vo로저장안되기 때문에 안넘어가서 공백됨 ) => member.getMemId()에 내용이 비어있는지(isBlank) 여부를 확인 => vo에 저장되어있는 memid가 만약 비어있다?  
         valid = false; // 그럼  false리턴!! 로그인실패! 
      }
      
      if(StringUtils.isBlank(member.getMemPass())) {//member.getMemPass()에 내용이 비어있는지(isBlank) 여부를 확인  => vo에 저장되어있는 memPass가 만약 비어있다?  
         valid = false; // 그럼  false리턴!!  로그인실패! 
      }
      
      return valid;
   }
   
   
}