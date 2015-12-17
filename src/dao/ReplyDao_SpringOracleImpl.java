package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import vo.Bang5VO;
import vo.ReplyVO;

public class ReplyDao_SpringOracleImpl implements ReplyDao{
	private JdbcTemplate jdbcTemplate=null;
	 private RowMapper<ReplyVO> rowMapper = 
				new RowMapper<ReplyVO>() {
					public ReplyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReplyVO vo = new ReplyVO();
						vo.setNo( rs.getInt(1) );
						vo.setBang_no(rs.getInt(2));
						vo.setUserId( rs.getString(3) );
						vo.setText(rs.getString(4));
						return vo;
					}
				};
	public void setJdbcTemplate(JdbcTemplate jtpl) {
		this.jdbcTemplate = jtpl;
		
	}
	@Override
	public List<ReplyVO> findbyBn(int board_no) throws Exception {
		String sql = "SELECT * from reply where board_no="+board_no;
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int add(ReplyVO pvo) throws Exception {
		String sql = "INSERT INTO reply VALUES "+
				"(SEQ_REP.NEXTVAL,'"+pvo.getBang_no() +"','"+ pvo.getUserId() +"','"+ 
				pvo.getText() +"')";
		return this.jdbcTemplate.update(sql);
	}

	@Override
	public int delete(int number) throws Exception {
		String sql="delete from reply where no="+number;
		return this.jdbcTemplate.update(sql);
	}

	@Override
	public int updateReply(ReplyVO vo) throws Exception {
		String sql="update reply set text='"+vo.getText()+"' where no="+vo.getNo();
		return this.jdbcTemplate.update(sql);
	}

}
