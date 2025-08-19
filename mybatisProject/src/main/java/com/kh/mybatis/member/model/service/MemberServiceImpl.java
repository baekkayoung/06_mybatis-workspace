package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {
	
private MemberDao mDao = new MemberDao();

	@Override
    public int insertMember(Member m) {
        
        /*
         * Connection conn = JDBCTemplate.getConnection();
         * int result = new MemberDao().insertMember(conn, m);
         * 
         * if(result > 0){
         *    commit(conn); 
         * }else{
         *  rollback(conn);
         * }
         * 
         * return result;
         *      
         */
        
        SqlSession sqlSession = Template.getSqlSession();  //에 이미 커밋 롤백 클로즈 그런거 있음.! 오늘의 핵심 부분 여기를 이해하자
        int result = mDao.insertMember(sqlSession, m); // 전역으로 정의해서 필요할 때마다 만들 필요 없음
        if(result > 0 ) {
        	sqlSession.commit();
        } else {
        	sqlSession.rollback();
        }sqlSession.close(); // 여기서 싹 다 반납
        
        return result;
        }
	

	@Override
	public Member loginMember(Member m) {
		
		 SqlSession sqlSession = Template.getSqlSession();
		 Member loginUser = mDao.loginMember(sqlSession, m); 
		 sqlSession.close();
		 return loginUser;
		
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	// MemberService를 구현
	


}
