package vo;



public class UserVO {
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", passwd=" + passwd + ", point="
				+ point + ", userName=" + userName + ", photo=" + photo + "]";
	}
	
	private String userId = null;
	private String passwd = null;
	private Integer point = null;
	private String userName = null;
	private String photo = null;
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	private String auth = null;
}
/*
CREATE TABLE User2T(
user_id VARCHAR2(16) ,
passwd VARCHAR2(16) , 
point NUMBER(8) ,
user_name VARCHAR2(16) , 
photo VARCHAR2(12)
);
*/