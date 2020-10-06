package org.kpu.atm.bank;
import java.util.Scanner;

import org.kpu.atm.helpdesk.CustomerSvc;
import org.kpu.atm.util.Statistics;

public class ATMachine {
	private Account [] accountArray;	// 고객계좌배열 참조변수
	private int nMachineBalance;	// ATM 잔고
	private int nMaxAccountNum;	// 고객계좌 참조변수 배열크기
	private int nCurrentAccountNum = 0; // 개설된 고객계좌 수
	private String strManagerPassword;	// 관리자 비밀번호
			
	public static final int BASE_ACCOUNT_ID = 100;	// 고객 계좌 발급시 시작 번호
			
	Scanner scan = new Scanner(System.in);
			
	public ATMachine(int size, int balance, String password) { // 생성자
		nMaxAccountNum = size;
		nMachineBalance = balance;
		strManagerPassword = password;
		accountArray = new Account[nMaxAccountNum];
	}
			
	public void createAccount () { // 계좌 개설
		System.out.println("--------개설--------");
		System.out.print(" 이름 입력: ");
		String name = scan.next();
		System.out.print(" 암호 입력: ");
		String pw = scan.next();
		
		accountArray[nCurrentAccountNum] = new Account(nCurrentAccountNum + BASE_ACCOUNT_ID, 0, name, pw);
		System.out.printf(" %s님 %d번 계좌번호가 정상적으로 개설되었읍니다. 감사합니다", name, nCurrentAccountNum + BASE_ACCOUNT_ID);
		nCurrentAccountNum++;
		System.out.println("\n");
	}
			
	public void checkMoney () { // 계좌 조회
		System.out.println("--------조회--------");
		System.out.print(" 계좌번호 입력: ");
		int id = scan.nextInt();
		System.out.print(" 비밀번호 입력: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			if(accountArray[i].authenticate(id, pw)) {
				System.out.printf(" 계좌 잔액: %d\n", accountArray[i].getnBalance());		
			}
		}	
	}
	
	public void depositMoney() {
		System.out.println("--------입금--------");
		System.out.print(" 계좌번호 입력: ");
		int id = scan.nextInt();
		System.out.print(" 비밀번호 입력: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			if(accountArray[i].authenticate(id, pw)) {
				System.out.print(" 입금 액 입력: ");
				int money = scan.nextInt();
				
				System.out.printf(" 입금 후 잔액: %d\n", accountArray[i].deposit(money));
				nMachineBalance += money;
			}
		}
	}
	
	public void widrawMoney() {
		System.out.println("--------출금--------");
		System.out.print(" 계좌번호 입력: ");
		int id = scan.nextInt();
		System.out.print(" 비밀번호 입력: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			if(accountArray[i].authenticate(id, pw)) {
				System.out.print(" 출금 액 입력: ");
				int money = scan.nextInt();
				
				System.out.printf(" 출금 후 잔액: %d\n", accountArray[i].widraw(money));	
			}
		}
	}
	
	public void transfer() {	// 계좌 이체
		System.out.println("--------이체--------");
		System.out.print(" 계좌번호 입력: ");
		int id = scan.nextInt();
		System.out.print(" 비밀번호 입력: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			try {
				if(accountArray[i].authenticate(id, pw)) {
					System.out.print(" 이체계좌 입력: ");
					int idT = scan.nextInt();
					System.out.print(" 이체금액 입력: ");
					int money = scan.nextInt();
				
					accountArray[i].widraw(money);
					accountArray[idT-BASE_ACCOUNT_ID].deposit(money);
					System.out.printf(" 현재 잔액: %d\n", accountArray[i].getnBalance());
					System.out.println(" 계좌 이체를 완료하였습니다.");
				}
			} catch(Exception e) {
				System.out.println(" 이체 계좌를 다시 확인하세요.");
			}
		}
	}
	
	public void requestSvc() {	//비밀번호 변경 요청
		CustomerSvc svcArray = new CustomerSvc(accountArray, nCurrentAccountNum); // 객체 생성		
		svcArray.updatePasswdReq();
	}
	
	public void managerMode () { // 고객 관리
		System.out.println("-------고객관리------");
		System.out.print(" 관리자 비밀번호 입력: ");
		String pw = scan.next();
		if(pw.equals(strManagerPassword)) {
			System.out.printf(" ATM 현금 잔고:	%d\n", nMachineBalance);
			System.out.printf(" 고객 잔고 총액:	%d원(%d명)\n", Statistics.sum(accountArray, nCurrentAccountNum), nCurrentAccountNum);
			System.out.printf(" 고객 잔고 평균:	%d원\n", (int)Statistics.average(accountArray, nCurrentAccountNum));
			System.out.printf(" 고객 잔고 최고:	%d원\n", Statistics.max(accountArray, nCurrentAccountNum));
			System.out.println(" 고객 계좌 현황(고객 잔고 내림 차순 정렬)");
			
			accountArray = Statistics.sort(accountArray, nCurrentAccountNum);
			for(int i = 0; i < nCurrentAccountNum; i++) {
				System.out.printf("%s	%d	%d원\n", accountArray[i].getAccountName(), accountArray[i].getnID(), accountArray[i].getnBalance());
			}
		}
	}
	
	public void displayMenu () { // 메인 메뉴 표시
		System.out.println("------------------");
		System.out.println("-    KPU bank    -");
		System.out.println("------------------");
		System.out.println(" 1. 계좌 개설");
		System.out.println(" 2. 계좌 조회");
		System.out.println(" 3. 계좌 입금");
		System.out.println(" 4. 계좌 출금");
		System.out.println(" 5. 계좌 이체");
		System.out.println(" 6. 고객 센터");
		System.out.println(" 7. 고객 관리");
		System.out.println(" 9. 업무 종료");
		System.out.println("------------------");
	}
}
