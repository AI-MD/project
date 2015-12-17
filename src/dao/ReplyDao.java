package dao;

import java.util.List;

import vo.ReplyVO;

public interface ReplyDao {
	
	public List<ReplyVO> findbyBn(int board_no) throws Exception;
	public int add( ReplyVO pvo ) throws Exception;
	public int delete( int number ) throws Exception;
	public int updateReply(ReplyVO vo) throws Exception;
	
}
