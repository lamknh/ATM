package org.kpu.atm.bank;

public class Account {
	private int nID;	// ���� ��ȣ
	private int nBalance;	// ���� �ܰ�
	private String strAccountName;	// �� ��
	private String strPassword;	// ���� ��й�ȣ
	
	public Account(int id, int money, String name, String password) { // ������
		nID = id;
		nBalance = money;
		strAccountName = name;
		strPassword = password;
	}
	
	boolean authenticate(int id, String passwd) { // ���� Ȯ��
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
	int deposit(int money) {	//�Ա�
		return nBalance += money;
	}
	
	public int widraw(int money) {	//���
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
	
	public String getAccountName() {	//���� �б�
		return strAccountName;
	}
}
