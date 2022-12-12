<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="sun.security.util.Length"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourcesIdentify.jsp</title>
</head>
<body>


하이시스템자원
<h4>자원의 종류와 식별방법 : 접근하는 방법에 따라 달라짐</h4>
<pre>
	: 자원의 위치와 경로 표기 방법에 따라 분류 
	
	1. File system resource(물리주소를 가짐) : d:/contents/images/cat1.jpg 
	<%
		String realPath = "d:/contents/images/cat1.jpg"; //물리주소 
		File fileSystemResource = new File(realPath);
	%> 
	파일의 크기 : <%= fileSystemResource.length() %>
	
	--------------------------------------------------------------------------------------------------------------------
	
	2. Class path resource : /kr/or/ddit/images/cat2.png <!-- 논리주소 - 클래스패스 리소스  : 클래스패스 안에 들어 있는 소스 -->
	=> 퀄리파이드 네임(= 클래스패스 )을 통해서 접근

	<%
	
		//String qualifiedName = "/kr/or/ddit/images/cat2.png";
		String qualifiedName = "../images/cat2.png"; //DescriptionServlet상위 폴더 ddit로 이동한 뒤 images폴더 내부 
		
		//이미지 존재를 찾아내기 위한 코드 필요  : 클래스 로더 (클래스 패스의 관리자)
		//File classPathResource = new File(qualifiedName); //이렇게 쓸수 없음 바로 퀄리파이드경로 넣을 수 없음 
		realPath = DescriptionServlet.class.getResource(qualifiedName).getFile(); //DescriptionServlet.class => DescriptionServlet.class 실제 톰캣 내부속 파일로 접근 가능 
		realPath = DescriptionServlet.class.getClassLoader().getResource("kr/or/ddit/images/cat2.png").getFile(); //getClassLoader => 상대경로 사용 안함 (클래스패스로 고정되어있기 때문에 시작할 때 / 로 시작하지 않음) 
		File classPathResource = new File(realPath);

	%>	
	
	실제경로(톰캣 내부에 있는 실제 경로) : <%= realPath %>
	파일크기 : <%= classPathResource.length() %>
	
	--------------------------------------------------------------------------------------------------------------------
	
	3. web resource : https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif   <!-- 논리주소 가짜주소  -->
	
	<!-- /webStudy01/src/main/webapp/resources/js/jquery-3.6.1.min.js -->
	http://localhost/webStudy01/resources/js/jquery-3.6.1.min.js
	<!-- 웹상에 존재하는 자원의 내 로컬 자원으로 가져와야됨   -->
	<%
		//new File() => 로컬에 가지고있는 자원에만 쓸수 있음 다른방식 필요
		String resourceURL = "https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif"; //논리주소
//		String resourceURL = "http://localhost/webStudy01/resources/js/jquery-3.6.1.min.js"; //논리주소
		URL url = new URL(resourceURL);
		
		URLConnection conn = url.openConnection(); //구글서버를 대상으로 요청 넘어감
		String resourcePath = url.getPath(); //파일 이름 따오기 
		int lastIdx = resourcePath.lastIndexOf('/');
		String fileName = resourcePath.substring(lastIdx + 1);
		String folderPath = "d:/contents/images/"; //저장위치
		File downloadFile = new File(folderPath, fileName); //folderPath에 fildName이라는 이름으로 저장하기 
		InputStream is = conn.getInputStream();
		Files.copy(is, Paths.get(downloadFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
		
		//downloadFile.getPath() : 파일은 패스타입으로 변경
		//REPLACE_EXISTING : 저장하려는 위치에 똑같은게 있으면 replace해라 
			
	%>
	
	resourcePath :  <%=resourcePath  %>



	*** 웹자원에 대한 식별성 : URI
	URI(Uniform Resource Identifier, 범용자원식별자)
	URL(Uniform Resource Locator, 범용자원위치자)
	URN(Uniform Resource Name, 범용자원의 이름) - 같은 이름의 자원이 있으면 구분이 불가능 , 파악하는 과정이 필요
	URC(Uniform Resource Content, 범용자원의 조건)
	
	URL 구조 
	protocol(scheme)://IP(DN):port/context/depth1...depN/resourceName
	
	DomainName : 
	3 level www.naver.com    > GlobalTopLevelDomain: GTLD
	4 level www.naver.co.kr  > NationalTopLevelDomain : LTLD


	URL 표기 방식
	절대경로(**) : 최상위 루트부터 전체 경로 표현  - 생략가능한 요소가 존재
		client side방식     :  /webStudy01/resources/images/cat1.jpg
						   => context path부터 시작됨
		servier side 방식 : /resources/images/cat1.jpg(서버는 이미 컨텍스트위치를 저장하고있기때문에 컨텍스트패스부터 시작하지 않아도 됨 )
						   => context path 이후의 경로 표기 
		
	상대경로 : 기준점(브라우저의 현재주소)을 중심으로 한 경로 표현 (/ 없으면 상대경로)




</pre>

<%
//	InputStream is2 = application.getResourceAsStream(request.getContextPath() + "/resources/images/cat1.jpg");
	String realPath1 = application.getRealPath("/resources/images/cat1.jpg");
	String realPath2 = application.getRealPath(request.getContextPath() +  "/resources/images/cat1.jpg");
	
	request.getRequestDispatcher("/WEB-INF/views/depth1/test.jsp").forward(request, response); //server side 방식
	response.sendRedirect(request.getContextPath() + "/member/memberForm.do"); // client side 방식 
%>
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg" /> <!-- /webStudy01/resources/images/cat1.jpg  -->
<img src="../resources/images/cat1.jpg" />
<img src="cat1.jpg" />
<%-- 서버사이드방식으로 접근한 파일의 크기 : <%= is2.available() %> --%>


realPath1 : <%= realPath1 %> <br />
realPath2 : <%= realPath2 %>


</body>
</html>

