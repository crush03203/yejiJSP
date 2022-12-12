package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 
 * alt + shift + j
 * 서블릿 : 웹상에서 발생하는 요청을 처리하고, 그에 따른 동적 응답을 생성하기 위한 객체의 필요요건(명세, specification)
 * 
 * 개발단계
 * 1. HttpServlet 상속
 * 2. callback메소드 재정의  (doGet)
 * 3. 컴파일  : /WEB-INF/classes (context의 최우선 classpath) 에 배포 
 * 4. WAS(Servlet Container)에 등록 
 * 	  1) web.xml -> servlet -> servlet-name, servlet-class  (서블릿스펙 2.X에서 많이 사용되는 방법)
 *    2) annotation @WebServlet 사용 (서블릿스펙 3.X에서 많이 사용되는 방법)
 * 5. 서블릿 매핑 설정 
 * 	  1) web.xaml -> servlet-mapping -> servlet-name, url-pattern (서블릿스펙 2.X에서 많이 사용되는 방법)
 *    2) @WebServlet(속성들) (3.X)
 * 6. 컨테이너 재구동 
 * 
 * 
 * 
 *  ** Servlet container? 
 *  	: 컨테이너에 의해 관리되는 서블릿 객체의 생명주기 제어. 
 *  서블릿 객체의 싱글턴 인스턴스 생성 : 구체적인 설정(, loadOnStartup=1)이 없는 한, 매칭된 조건의 요청이 발생했을 때 생성 
 *  
 *  
 *  ** 서블릿에서 재정의할 수 있는 콜백 메소드 종류  **
 *  생명주기 이벤트
 *  	생성 : init()으로 제어
 *  	소멸  : destroy()으로 제어
 *  요청 관련 이벤트  : service, doXXX(doGet...)으로 제어할 수 있음 
 *  callback: 관련 이벤트가 발생했을 때, 시스템 내부적으로 자동 호출되는 코드 형태 
 *   => 개발자가 호출하는게 아니라, 이벤트를 실행했을때 시스템 내부적으로 자동호출되는 것(제이쿼리가 자동적으로 자체적으로 실행코드를 실행시켜줌 ) 
 *  $("button).on("click", function(){
 *  	//실행코드 
 *  });
 *  
 *
 */


//loadOnStartup=1 요청이 발생하지 않더라도 서버가 돌아가면 첫번째로 발생시켜줘 
//멀티 value 어노테이션  : 두개이상의 어노테이션 속성을 사용하고 싶을 경우 앞에 value=라고 적어줘야됨 
//@WebServlet(value="/desc.do", loadOnStartup=1)
@WebServlet(value="/desc.do", loadOnStartup=1)
public class DescriptionServlet extends HttpServlet {
	
	
	
	
	
	public DescriptionServlet() {
		super();
		System.out.printf("%s 생성\n", this.getClass().getName()); 
		//this => DescriptionServlet의 객체 
		//this.getClass().getName() => 퀄리파이드네임 가져오기 
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 서블릿 인스턴스 초기화\n", this.getClass().getSimpleName());
	}
	
	@Override
	public void destroy() {
		System.out.printf("%s 서블릿 인스턴스 소멸\n", this.getClass().getSimpleName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("============ service 시작 ===========");
		super.service(req, resp); //do계열의 메소드 호출하는 역할 이게 없으면 화면 안띄워짐 
		System.out.println("============ service 종료 ===========");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(String.format("description-%s", Thread.currentThread().getName()));
		//미리 10개의 스레드를 만들어놓고 돌렸는거 => polling (for 자원의 관리 효율적)
		System.out.println("doGet 실행 ");
	}
	
	
}
