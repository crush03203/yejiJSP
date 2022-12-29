package kr.or.ddit.mvc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InternalResourceViewResolver implements ViewResolver {

   
   private String prefix;
   private String suffix;
   
   //기본생성자로 사용자 prefix와 suffix사용X
   //new InternalResourceViewResolver()
   public InternalResourceViewResolver() { 
      this("", "");
   }
   
   //이 생성자 사용시 prefix, suffix사용O
   //new InternalResourceViewResolver("/WEB-INF/views/", ".jsp") 
   public InternalResourceViewResolver(String prefix, String suffix) {
      super();
      this.prefix = prefix;
      this.suffix = suffix;
   }

   @Override
   public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
   
      
      //5번단계  이거 반복 줄이기
      // 규칙! redirect:/ 로 시작되는 viewName은 redirect로 넘기기
      if(viewName.startsWith("redirect:")) { //viewName이 redirect:로 시작 할 경우
         viewName = viewName.substring("redirect:".length()); // redirect:길이 만큼 잘라라 => redirect: 이부분 삭제
         resp.sendRedirect(req.getContextPath() + viewName); //   req.getContextPath() +  /login/loginForm.jsp
      }else { //forward할 경우
         req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
      }
      
      
      
   }

}