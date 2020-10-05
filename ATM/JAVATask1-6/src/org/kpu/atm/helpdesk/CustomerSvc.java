package org.kpu.atm.helpdesk;
import org.kpu.atm.bank.Account;
import java.util.Scanner;

public class CustomerSvc {
	private Account [] acctArray = new Account[1000]; 
	private int nCurrentAcctNum;
	
	Scanner scan = new Scanner(System.in);
	
	public CustomerSvc(Account[] acctArray, int nCurrentAcctNum) { //������
		this.acctArray = acctArray;
		this.nCurrentAcctNum = nCurrentAcctNum;
	}
	
	public void updatePasswdReq() {	//��й�ȣ �����û
		System.out.println("-------��ȣ����------");
		System.out.print(" ���¹�ȣ �Է�: ");
		int id = scan.nextInt();
		System.out.print(" ���� ��й�ȣ �Է�: ");
		String oldPw = scan.next();
		System.out.print(" �ű� ��й�ȣ �Է�: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAcctNum; i++) {
			if(acctArray[i].updatePasswd(oldPw, pw)) {
				System.out.println(" ��й�ȣ�� �����Ͽ����ϴ�.");				
			}
			else {
				System.out.println("������ ��й�ȣ�� �Է��ϼ̽��ϴ�.");
			}
		}
	}
}