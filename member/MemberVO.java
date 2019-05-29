package member;

import java.io.Serializable;

import global.SQL;

public class MemberVO implements SQL, Serializable{
	private static final long serialVersionUID = 1L;
	
	protected String userid;
	protected String password;
	protected String name;
	protected String birth;
	protected String phone;
	protected String email;
	protected String gender;
	protected String addr;
	protected String regdate;
	protected String profile;
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String userid,String password,String name,String birth,
			String gender,String phone, String email, String addr) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.addr = addr;
		this.profile = "default.jpg";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getGender() {
		return gender;
	}
	public String getAddr() {
		return addr;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "회원 [아이디 : " + userid 
				+ ", 비밀번호 : " + password 
				+ ", 이름 : " + name 
				+ ", 생년 : " + birth
				+ ", 성별 : " + gender
				+ ", 전화 : " + phone 
				+ ", 이메일 : " + email 
				+ ", 주소 : " + addr 
				+ ", 프로필 : " + profile + "]";
	}
	/**
	 * executeUpdate
	 */
	// 추가
	@Override
	public String insert() {
		String query = "insert into member("
				+ "userid,"
				+ "password,"
				+ "name,"
				+ "birth,"
				+ "gender,"
				+ "email,"
				+ "phone,"
				+ "addr,"
				+ "regdate,"
				+ "profile"
				+ ") values ("
				+make(this.userid)+","
				+make(this.password)+","
				+make(this.name)+","
				+make(this.birth)+","
				+make(this.gender)+","
				+make(this.email)+","
				+make(this.phone)+","
				+make(this.addr)+","
				+"sysdate,"
				+make(this.profile)
				+ ")";
		return query;
	}
	// 수정
	@Override
	public String update() {
		String query = "update member"
				+ "set password"+make(this.password)
				+ "set email"+make(this.email)
				+ "set phone"+make(this.phone)
				+ "set addr"+make(this.addr)
				+ "set profile"+make(this.profile)
				;
		return query;
	}
	// 삭제
	@Override
	public String delete() {
		String query = "delete from member"
				+ "where userid="+make(this.userid);
		return query;
	}
	/**
	 *  executeQuery
	 */
	// 조회
	@Override
	public String selectAll() {
		String query = "select * from member";
		return query;
	}
	
	@Override
	public String selectOneBy(String s) {
		String query = "select * from member "
				+ "where userid = "+make(s);
		return query;
	}
	// 카운트
	@Override
	public String count() {
		String query = "select count(*) count from member";
		return query;
	}
	
	@Override
	public String make(String s) {
		String query = "'"+s+"'";
		return query;
	}
}
