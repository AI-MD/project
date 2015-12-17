package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import vo.Bang5VO;
import vo.RecommandVO;

public class RecommandDao_SpringOracleImpl implements RecommandDao {
	private JdbcTemplate jdbcTemplate=null;
	
	public void setJdbcTemplate(JdbcTemplate jtpl) {
		this.jdbcTemplate = jtpl;
		
	}
	@Override
	public int findbyID(int board_no, String userId) throws Exception {
		String sql = "SELECT count(*) from recommand where board_no="+board_no +" and user_id='"+userId+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	@Override
	public int add(RecommandVO vo) throws Exception {
		String l = "insert into recommand values(seq_rec.nextval,'"+vo.getUserId()+"',"+vo.getBoardNo()+")";
		return jdbcTemplate.update(l);
	}

}
