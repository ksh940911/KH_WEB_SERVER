package com.kh.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet
 * Web Service를 위한 java class
 * 일반 자바클래스인데 웹서비스 용도로 쓰는 것
 * 그러려면 반드시 HttpServlet을 상속해야 함
 * 
 * Servlet 생명주기
 * - was구동내내 딱 하나의 객체만 만들어져서 처리된다. (singletone방식)
 * 1. Servlet객체생성(기본 생성자 호출) 		- 최초 client호출시 1회
 * 2. init메소드 호출  					- 최초 client호출시 1회
 * 3. HttpServlet의 service메소드 호출 	- client매요청마다
 * 4. 전송방식에 따라 doGet | doPost 호출 	- client매요청마다
 * 5. destroy호출(tomcat종료시 객체 반환.)	- 마지막 1회
 * 
 * GET과 POST방식을 무분별하게 섞어쓰면 안된다. 어떤 요청을 보낼지에 따라서 GET방식을 쓸지 POST방식을 쓸지 정해야 한다.
 * - 멱등 : 서비스 전후로 database의 상태가 바뀌지 않는 경우
 * - select -> GET
 * - 멱등X : 서비스 전후로 database의 상태가 바뀌는 경우
 * - insert update delete -> POST 
 * - login (멱등이지만 id/password url노출을 막기위해) -> POST
 * 
 */
public class TestPerson1Servlet extends HttpServlet{
	
	/**
	 * 기본생성자
	 */
	public TestPerson1Servlet() {
		super();
		System.out.println("기본생성자 TestPerson1Servlet() 호출!");
	}
	
	@Override
	public void init(ServletConfig config) {
		System.out.println("init(ServletConfig) 호출!");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() 호출!");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		//매 요청시 사용되는 servlet객체는 동일하다. singletone 패턴
		System.out.println(this.hashCode());
		
		// 1. 사용자 입력값 가져오기
		String name = request.getParameter("name");
		// "name" - 브라우저의 name=홍길동
		// 왜 name? html에서 name값이었으므로
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		// 복수개의 경우 
		String[] foodArr = request.getParameterValues("food");
		// String 배열을 리턴함
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("foodarr = " + Arrays.toString(foodArr)); // import java.util.Arrays;
		 
		//2. 응답메세지 작성 : html
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개취 검사 결과</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>개인 취향 검사 결과 GET</h1>");
		out.println("<p>" + name + "님의 개인취향 검사 결과는 </p>");
		out.println("<p>" + color + "색을 좋아합니다. </p>");
		out.println("<p>좋아하는 동물은 " + animal + "입니다.</p>");
		out.println("<p>좋아하는 음식은 " + Arrays.toString(foodArr)+ "입니다. </p>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		//0. 인코딩 선언
		//http message body부분 인코딩이 유효하도록 한다.
		request.setCharacterEncoding("utf-8");

		//1. 사용자입력값 가져오기
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		String[] foodArr = request.getParameterValues("food");
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("foodArr = " + Arrays.toString(foodArr));
		
		//2. 응답메세지 작성 : html
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개취 검사 결과</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>개인 취향 검사 결과 POST</h1>");
		out.println("<p>" + name + "님의 개인취향 검사 결과는 </p>");
		out.println("<p>" + color + "색을 좋아합니다. </p>");
		out.println("<p>좋아하는 동물은 " + animal + "입니다.</p>");
		out.println("<p>좋아하는 음식은 " + Arrays.toString(foodArr)+ "입니다. </p>");
		out.println("</body>");
		out.println("</html>");
	}	
}	