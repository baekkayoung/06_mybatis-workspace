package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	public int insertMember(SqlSession sqlSession, Member m) {
		/*
         * int result = 0;
         * PreparedStatement pstmt = null;
         * String sql = prop.getProperty("insertMember");
         * 
         * try {
         *     pstmt = conn.prepareStatement(sql);
         *  pstmt.setString(1, m.getUserId());
         *  pstmt.setString(2, m.getUserPwd());
         *  ....
         *  
         *  result = pstmt.executeUpdate();
         * 
         * } catch(xxx){
         * 
         * } finally{
         *      close(pstmt);
         * }
         * 
         * return result
         * 
         */
		
		/*
		 * sqlSession에서 제공하는 메소드를 통해 sql문을 찾아서 실행하고 결과 바로 받음
		 * 
		 * sqlSesison.sql문종류에맞는메소드("매퍼의별칭.쿼리아이디", [그sql문을완성시킬객체]);
		 * 
		 */
		
		return sqlSession.insert("memberMapper.insertMember", m);
		
	}
	
	
	

}
