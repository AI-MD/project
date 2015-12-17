package dao;



import java.util.HashMap;
import java.util.List;

import vo.RecommandVO;

public interface RecommandDao {
	public int findbyID(int board_no, String userId)  throws Exception;
	public int add(RecommandVO vo) throws Exception;
}
