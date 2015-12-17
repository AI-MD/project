package dao;



import java.util.List;

import vo.UserVO;

public interface UserDao {
	public UserVO findByPk( String userId ) throws Exception;
	public int add( UserVO pvo ) throws Exception;
	public int movePoint( int no, String downer ) throws Exception;
	public	List<UserVO> findAll() throws Exception;
}
