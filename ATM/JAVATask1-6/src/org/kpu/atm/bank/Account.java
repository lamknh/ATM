package org.kpu.atm.bank;

public class Account {
	private int nID;	// 계좌 번호
	private int nBalance;	// 계좌 잔고
	private String strAccountName;	// 고객 명
	private String strPassword;	// 계좌 비밀번호
	
	public Account(int id, int money, String name, String password) { // 생성자
		nID = id;
		nBalance = money;
		strAccountName = name;
		strPassword = password;
	}
	
	boolean authenticate(int id, String passwd) { // 계정 확인
		if(nID == id && strPassword.equals(passwd)) {
			return true;
		}
		return false;
	}
	
	public int getnID () {
		return nID;
	}
	 
	public int getnBalance () {		
		return nBalance;
	}
	int deposit(int money) {	//입금
		return nBalance += money;
	}
	
	public int widraw(int money) {	//출금
		return nBalance -= money;
	}
	
	public void setPassword(String pw) {
		strPassword = pw;
	}
	
	public boolean updatePasswd(String oldPasswd, String newPasswd) {
		if(!oldPasswd.equals(newPasswd) && oldPasswd.equals(strPassword)) {
			strPassword = newPasswd;
			return true;
		}
		return false;
	}
	
	public String getAccountName() {	//고객명 읽기
		return strAccountName;
	}
}
