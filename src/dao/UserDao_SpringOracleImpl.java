package dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import vo.Bang5VO;
import vo.UserVO;

public class UserDao_SpringOracleImpl implements UserDao{
	 private JdbcTemplate jdbcTemplate=null;
	 private RowMapper<UserVO> rowMapper = 
				new RowMapper<UserVO>() {
					public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserVO vo = new UserVO();
							vo.setUserId( rs.getString(1) );
							vo.setPasswd( rs.getString(2) );
							vo.setPoint( rs.getInt(3) );
							vo.setUserName( rs.getString(4) );
							vo.setPhoto( rs.getString(5) );
							vo.setAuth(rs.getString(6));
						return vo;
					}
				};
	
	public void setJdbcTemplate(JdbcTemplate jtpl) {
		this.jdbcTemplate = jtpl;
		System.out.println(jdbcTemplate.toString());
	}
	
	@Override
	public UserVO findByPk(String userId) throws Exception {
		String sql = "SELECT * FROM User4T WHERE user_id = '"+userId+"'";
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int add(UserVO pvo) throws Exception {
		String sql = "INSERT INTO User4T VALUES ('"+
				pvo.getUserId() +"','"+
				pvo.getPasswd() +"',1000,'"+
				pvo.getUserName() +"','"+pvo.getPhoto()+"','0001')";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int movePoint(int no, String downer) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserVO> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
