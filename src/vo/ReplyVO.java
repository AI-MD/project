package vo;

public class ReplyVO {
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Integer no = null;
	private String text = null;
	private Integer bang_no = null;
	private String userId = null;
	
	
	
	
	
	
	

	public Integer getBang_no() {
		return bang_no;
	}
	public void setBang_no(Integer bang_no) {
		this.bang_no = bang_no;
	}

	public String getUserId() {
		return userId;
	}

	
}

