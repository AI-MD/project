package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;


import vo.Bang5VO;


public class Bang5Dao_SpringOracleImpl implements Bang5Dao {
	
	
	 private JdbcTemplate jdbcTemplate=null;
	 private RowMapper<Bang5VO> rowMapper = 
				new RowMapper<Bang5VO>() {
					public Bang5VO mapRow(ResultSet rs, int rowNum) throws SQLException {
						Bang5VO vo = new Bang5VO();
						vo.setNo( rs.getInt(1) );
						vo.setText( rs.getString(2) );
						vo.setTheTime( rs.getString(3) );
						vo.setClientIp( rs.getString(4) );
						vo.setFsn( rs.getString(5) );
						vo.setOfn( rs.getString(6) );
						vo.setUserId( rs.getString(7) );
						vo.setPhoto( rs.getString(8) );
						vo.setTitle(rs.getString(9));
						vo.setCount(rs.getInt(10));
						vo.setRecommand(rs.getInt(11));
						return vo;
					}
				};
				private RowMapper<Bang5VO> rowMapper_page = 
						new RowMapper<Bang5VO>() {
							public Bang5VO mapRow(ResultSet rs, int rowNum) throws SQLException {
								Bang5VO vo = new Bang5VO();
								vo.setNo( rs.getInt(2) );
								vo.setText( rs.getString(3) );
								vo.setTheTime( rs.getString(4) );
								vo.setClientIp( rs.getString(5) );
								vo.setFsn( rs.getString(6) );
								vo.setOfn( rs.getString(7) );
								vo.setUserId( rs.getString(8) );
								vo.setPhoto( rs.getString(9) );
								vo.setTitle(rs.getString(10));
								vo.setCount(rs.getInt(11));
								vo.setRecommand(rs.getInt(12));
								return vo;
							}
						};		
		private RowMapper<Bang5VO> rowMapper_new = 
				new RowMapper<Bang5VO>() {
					public Bang5VO mapRow(ResultSet rs, int rowNum) throws SQLException {
						Bang5VO vo=new Bang5VO();
						vo.setNo( rs.getInt(1) );
						vo.setText( rs.getString(2) );
						vo.setTheTime( rs.getString(3) );
						vo.setClientIp( rs.getString(4) );
						vo.setFsn( rs.getString(5) );
						vo.setOfn( rs.getString(6) );
						vo.setUserId( rs.getString(7) );
						vo.setTitle(rs.getString(8));
						return vo;
					}
				};
	public void setJdbcTemplate(JdbcTemplate jtpl) {
		this.jdbcTemplate = jtpl;
		
	}
	/*
	@Override
	public int add(Bang5VO pvo) throws Exception {
		String sql="INSERT INTO Bang6T VALUES "+
				"(SEQ_BANG.NEXTVAL,'"+
				pvo.getText() +"',SYSDATE)";		
		return this.jdbcTemplate.update(sql);
	}
	*/
	@Override
	public int add(final Bang5VO pvo) throws Exception {
		
		
		String sql = "INSERT INTO Bang5T VALUES "+
				"(SEQ_BANG.NEXTVAL,?,SYSDATE,?,?,?,?,?,0,0)";	
		return this.jdbcTemplate.update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, pvo.getText());
				stmt.setString(2, pvo.getClientIp());
				stmt.setString(3, pvo.getFsn());
				stmt.setString(4, pvo.getOfn());
				stmt.setString(5, pvo.getUserId());
				stmt.setString(6, pvo.getTitle());
			}
		});
	}
	
	
	@Override
	public List<Bang5VO> findAll() throws Exception {
		
		String sql = "SELECT no, text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS'), client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0 ORDER BY no DESC";
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	@Override
	public int delete(int number) throws Exception {
		String sql="delete from Bang5T where no="+number;
		return jdbcTemplate.update(sql);
	}
	@Override
	public Bang5VO findByNo(int no) throws Exception {
		String sql = "SELECT * From Bang5T where no="+no;
		return this.jdbcTemplate.queryForObject(sql, rowMapper_new);
	}
	@Override
	public int updateCount(int number) throws Exception {
		String sql="update Bang5T set count=count+1 where no="+number;
		return jdbcTemplate.update(sql);
	}
	@Override
	public int updateBoard(Bang5VO pvo)
			throws Exception {
		String sql="UPDATE Bang5T set title="+pvo.getTitle()+", text="+pvo.getTitle()+"where no="+pvo.getNo();
		return jdbcTemplate.update(sql);
	}
	@Override
	public int findAllCount() throws Exception {
		String	sql= "SELECT count(*)  FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0 ORDER BY no DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	@Override
	public List<Bang5VO> findAll(int start, int end) throws Exception {
		String sql = "SELECT * FROM( SELECT ROWNUM AS rn, a.* FROM(SELECT no, text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS'), client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0 ORDER BY no DESC  ) a   )where  rn between "+start+" and  "+end;
		return this.jdbcTemplate.query(sql, rowMapper_page);
	}
	@Override
	public int findSearchCount(HashMap map) throws Exception {
		String field= (String)map.get("field");
		String value=(String)map.get("searchWord");
		
		String sql="";
		if(field.startsWith("re")){
			field="text";
			//System.out.println(field);
			sql= "select count(*) from (SELECT bang5T.no FROM User4T, Bang5T ,reply  WHERE User4T.user_id = Bang5T.user_id and Bang5T.no=reply.board_no and 0=0  and reply."+field+" like '%"+value+"%' group by bang5T.no)";
			//System.out.println(sql);
		}else{
			sql= "SELECT count(*)  FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0  and Bang5T."+field+" like '%"+value+"%' ORDER BY no DESC";
		}
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	@Override
	public List<Bang5VO> findSearch(HashMap map, int start, int end)
			throws Exception {
		String field= (String)map.get("field");
		String value=(String)map.get("searchWord");
		
		String sql="";
		if(field.startsWith("re")){
			field="text";
			
			sql = "SELECT * FROM( SELECT ROWNUM AS rn, a.* FROM(SELECT distinct bang5T.no, bang5T.text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS'), client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T,reply  WHERE User4T.user_id = Bang5T.user_id and Bang5T.no=reply.board_no and 0=0  and reply."+field+" like '%"+value+"%'  ORDER BY bang5T.no DESC  ) a   )where  rn between "+start+" and  "+end;
			
		}else{
			sql = "SELECT * FROM( SELECT ROWNUM AS rn, a.* FROM(SELECT no, text, TO_CHAR(the_time,'YYYY-MM-DD HH24:MI:SS'), client_ip, fsn, ofn, Bang5T.user_id, photo,title, count, recommand FROM User4T, Bang5T  WHERE User4T.user_id = Bang5T.user_id and 0=0  and Bang5T."+field+" like '%"+value+"%' ORDER BY no DESC  ) a   )where  rn between "+start+" and  "+end;
		}
		return this.jdbcTemplate.query(sql, rowMapper_page);
	}
	@Override
	public int updateRecommand(int no) {
		String t = "update bang5T set recommand=recommand+1 where no="+no;
		return jdbcTemplate.update(t);
	}
}
