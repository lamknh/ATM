package org.kpu.atm.helpdesk;
import org.kpu.atm.bank.Account;
import java.util.Scanner;

public class CustomerSvc {
	private Account [] acctArray = new Account[1000]; 
	private int nCurrentAcctNum;
	
	Scanner scan = new Scanner(System.in);
	
	public CustomerSvc(Account[] acctArray, int nCurrentAcctNum) { //생성자
		this.acctArray = acctArray;
		this.nCurrentAcctNum = nCurrentAcctNum;
	}
	
	public void updatePasswdReq() {	//비밀번호 변경요청
		System.out.println("-------암호변경------");
		System.out.print(" 계좌번호 입력: ");
		int id = scan.nextInt();
		System.out.print(" 기존 비밀번호 입력: ");
		String oldPw = scan.next();
		System.out.print(" 신규 비밀번호 입력: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAcctNum; i++) {
			if(acctArray[i].updatePasswd(oldPw, pw)) {
				System.out.println(" 비밀번호를 수정하였습니다.");				
			}
			else {
				System.out.println("동일한 비밀번호를 입력하셨습니다.");
			}
		}
	}
}