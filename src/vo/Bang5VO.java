package vo;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;



public class Bang5VO {
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTheTime() {
		return theTime;
	}
	public void setTheTime(String theTime) {
		this.theTime = theTime;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getFsn() {
		return fsn;
	}
	public void setFsn(String fsn) {
		this.fsn = fsn;
	}
	public String getOfn() {
		return ofn;
	}
	public void setOfn(String ofn) {
		this.ofn = ofn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Integer no = null;
	
	
	private String title = null;
	
	private String text = null;
	private String theTime = null;
	private String clientIp = null;
	private String fsn = null;
	private String ofn = null;
	private String userId = null;
	private Integer count=null;
	private Integer recommand=null;
	
	private MultipartFile one=null;
	@Override
	public String toString() {
		return "Bang5VO [no=" + no + ", text=" + text + ", theTime=" + theTime
				+ ", clientIp=" + clientIp + ", fsn=" + fsn + ", ofn=" + ofn
				+ ", userId=" + userId + "]";
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Integer getRecommand() {
		return recommand;
	}
	public void setRecommand(Integer recommand) {
		this.recommand = recommand;
	}

	public MultipartFile getOne() {
		return one;
	}
	public void setOne(MultipartFile one) {
		this.one = one;
	}

	private String photo = null;
}

/*
CREATE TABLE Bang4T(
no NUMBER(6) , 
text VARCHAR2(900) ,
the_time DATE , 
client_ip VARCHAR2(15),
fsn VARCHAR2(32) , 
ofn VARCHAR2(32) , 
user_id VARCHAR2(16)
);
*/