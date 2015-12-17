package dao;

import java.util.HashMap;
import java.util.List;

import vo.Bang5VO;
import vo.RecommandVO;

public interface Bang5Dao {
	public int add( Bang5VO pvo ) throws Exception;
	public List<Bang5VO> findAll() throws Exception;
	
	
	public int delete( int number ) throws Exception;
	public Bang5VO findByNo(int no) throws Exception;
	public int updateCount(int number) throws Exception;
	
	public int updateBoard(Bang5VO pvo) throws Exception;
	
	public int findAllCount() throws Exception;
	public List<Bang5VO> findAll(int start, int end) throws Exception;
	public int findSearchCount(HashMap map) throws Exception;
	public List<Bang5VO> findSearch(HashMap map,int start, int end) throws Exception;
	
	public int updateRecommand(int no);
	
	
	
	
	
	
	
	
	
}
