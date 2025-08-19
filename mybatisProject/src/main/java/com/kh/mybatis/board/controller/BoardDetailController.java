package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		 
		// 이게 정확한 표현법. 다형성으로 부모타입을 적어야함 => 근데 service카운트로 이동.. ㅜ 
		// BoardService bService = new BoardServiceImpl(); // 필요할 때마다 bService를 호출
		
		BoardServiceImpl bService = new BoardServiceImpl(); // 필요할때마다 간단하게 꺼내쓸 수 있음.
		
		// 1. 조회수 증가
		int result = bService.increaseCount(boardNo); 
		
		if(result>0) { // 조회수 증가가 되면, 
			 // 2. 해당 게시글 상세 조회 
			Board b = bService.selectBoard(boardNo);
			// 3. 해당 게시글에 딸린 댓글 조회
			ArrayList<Reply> list = bService.selectReplyList(boardNo);
			
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "상세조회 실패!");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
