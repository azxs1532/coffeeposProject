package orange;

import java.text.SimpleDateFormat;
import java.util.Date;

//	메모 1건을 기억하는 클래스
public class HugiVO {

	private int idx;			// 글번호, 자동증가
	private String name;		// 닉네임
	private String password;	// 비번
	private String han;			// 한 줄평
	private String review;		// 별점
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String gethan() {
		return han;
	}
	public void sethan(String han) {
		this.han = han;
	}
	public String getreview() {
		return review;
	}
	public void setreview(String review) {
		this.review = review;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return idx + ". " + name + "(" + han + ")님이 " +  "에 남긴글\n";
	}
	
}