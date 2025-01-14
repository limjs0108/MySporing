package com.sporing.app.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sporing.app.mybatis.config.MyBatisConfig;
import com.sporing.app.user.vo.UserVO;

public class UserDAO {
	SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;
	
	public UserDAO() {
		sqlSession = sqlSessionFactory.openSession(true);
	}
	
	// 중복검사
	public boolean checkId(String id) {
		return (Integer)(sqlSession.selectOne("User.checkId",id)) == 1;
	}
	
	// 회원가입
	public void join(UserVO member) {
		sqlSession.insert("User.join", member);
	}
	
	// 로그인
	public boolean login(String id, String pw) {
		HashMap<String, String> loginMap = new HashMap<>();
		loginMap.put("id", id);
		loginMap.put("pw", pw);
		return (Integer)(sqlSession.selectOne("User.login",loginMap)) == 1;
	}
	
	// 정보 수정
	public boolean update(UserVO member) {
		return (sqlSession.update("User.modify", member)) == 1;
	}
	
	// 회원 탈퇴
	public boolean delete(String id) {
		return (sqlSession.delete("User.remove",id)) == 1;
	}
	
	// 아이디 조회(이름과 비밀번호)
	public String findId(String name, String pw) {
		HashMap<String, String> findIdMap = new HashMap<>();
		findIdMap.put("name", name);
		findIdMap.put("pw", pw);
		return sqlSession.selectOne("Member.findId",findIdMap);
	}
	
	// 나이 조회
	public List<UserVO> findMember() {
		return sqlSession.selectList("Member.findMember");
	}
	
}
