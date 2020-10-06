package org.kpu.atm.bank;
import java.util.Scanner;

import org.kpu.atm.helpdesk.CustomerSvc;
import org.kpu.atm.util.Statistics;

public class ATMachine {
	private Account [] accountArray;	// �����¹迭 ��������
	private int nMachineBalance;	// ATM �ܰ�
	private int nMaxAccountNum;	// ������ �������� �迭ũ��
	private int nCurrentAccountNum = 0; // ������ ������ ��
	private String strManagerPassword;	// ������ ��й�ȣ
			
	public static final int BASE_ACCOUNT_ID = 100;	// �� ���� �߱޽� ���� ��ȣ
			
	Scanner scan = new Scanner(System.in);
			
	public ATMachine(int size, int balance, String password) { // ������
		nMaxAccountNum = size;
		nMachineBalance = balance;
		strManagerPassword = password;
		accountArray = new Account[nMaxAccountNum];
	}
			
	public void createAccount () { // ���� ����
		System.out.println("--------����--------");
		System.out.print(" �̸� �Է�: ");
		String name = scan.next();
		System.out.print(" ��ȣ �Է�: ");
		String pw = scan.next();
		
		accountArray[nCurrentAccountNum] = new Account(nCurrentAccountNum + BASE_ACCOUNT_ID, 0, name, pw);
		System.out.printf(" %s�� %d�� ���¹�ȣ�� ���������� �����Ǿ����ϴ�. �����մϴ�", name, nCurrentAccountNum + BASE_ACCOUNT_ID);
		nCurrentAccountNum++;
		System.out.println("\n");
	}
			
	public void checkMoney () { // ���� ��ȸ
		System.out.println("--------��ȸ--------");
		System.out.print(" ���¹�ȣ �Է�: ");
		int id = scan.nextInt();
		System.out.print(" ��й�ȣ �Է�: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			if(accountArray[i].authenticate(id, pw)) {
				System.out.printf(" ���� �ܾ�: %d\n", accountArray[i].getnBalance());		
			}
		}	
	}
	
	public void depositMoney() {
		System.out.println("--------�Ա�--------");
		System.out.print(" ���¹�ȣ �Է�: ");
		int id = scan.nextInt();
		System.out.print(" ��й�ȣ �Է�: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			if(accountArray[i].authenticate(id, pw)) {
				System.out.print(" �Ա� �� �Է�: ");
				int money = scan.nextInt();
				
				System.out.printf(" �Ա� �� �ܾ�: %d\n", accountArray[i].deposit(money));
				nMachineBalance += money;
			}
		}
	}
	
	public void widrawMoney() {
		System.out.println("--------���--------");
		System.out.print(" ���¹�ȣ �Է�: ");
		int id = scan.nextInt();
		System.out.print(" ��й�ȣ �Է�: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			if(accountArray[i].authenticate(id, pw)) {
				System.out.print(" ��� �� �Է�: ");
				int money = scan.nextInt();
				
				System.out.printf(" ��� �� �ܾ�: %d\n", accountArray[i].widraw(money));	
			}
		}
	}
	
	public void transfer() {	// ���� ��ü
		System.out.println("--------��ü--------");
		System.out.print(" ���¹�ȣ �Է�: ");
		int id = scan.nextInt();
		System.out.print(" ��й�ȣ �Է�: ");
		String pw = scan.next();
		
		for(int i = 0; i < nCurrentAccountNum; i++) {
			try {
				if(accountArray[i].authenticate(id, pw)) {
					System.out.print(" ��ü���� �Է�: ");
					int idT = scan.nextInt();
					System.out.print(" ��ü�ݾ� �Է�: ");
					int money = scan.nextInt();
				
					accountArray[i].widraw(money);
					accountArray[idT-BASE_ACCOUNT_ID].deposit(money);
					System.out.printf(" ���� �ܾ�: %d\n", accountArray[i].getnBalance());
					System.out.println(" ���� ��ü�� �Ϸ��Ͽ����ϴ�.");
				}
			} catch(Exception e) {
				System.out.println(" ��ü ���¸� �ٽ� Ȯ���ϼ���.");
			}
		}
	}
	
	public void requestSvc() {	//��й�ȣ ���� ��û
		CustomerSvc svcArray = new CustomerSvc(accountArray, nCurrentAccountNum); // ��ü ����		
		svcArray.updatePasswdReq();
	}
	
	public void managerMode () { // �� ����
		System.out.println("-------������------");
		System.out.print(" ������ ��й�ȣ �Է�: ");
		String pw = scan.next();
		if(pw.equals(strManagerPassword)) {
			System.out.printf(" ATM ���� �ܰ�:	%d\n", nMachineBalance);
			System.out.printf(" �� �ܰ� �Ѿ�:	%d��(%d��)\n", Statistics.sum(accountArray, nCurrentAccountNum), nCurrentAccountNum);
			System.out.printf(" �� �ܰ� ���:	%d��\n", (int)Statistics.average(accountArray, nCurrentAccountNum));
			System.out.printf(" �� �ܰ� �ְ�:	%d��\n", Statistics.max(accountArray, nCurrentAccountNum));
			System.out.println(" �� ���� ��Ȳ(�� �ܰ� ���� ���� ����)");
			
			accountArray = Statistics.sort(accountArray, nCurrentAccountNum);
			for(int i = 0; i < nCurrentAccountNum; i++) {
				System.out.printf("%s	%d	%d��\n", accountArray[i].getAccountName(), accountArray[i].getnID(), accountArray[i].getnBalance());
			}
		}
	}
	
	public void displayMenu () { // ���� �޴� ǥ��
		System.out.println("------------------");
		System.out.println("-    KPU bank    -");
		System.out.println("------------------");
		System.out.println(" 1. ���� ����");
		System.out.println(" 2. ���� ��ȸ");
		System.out.println(" 3. ���� �Ա�");
		System.out.println(" 4. ���� ���");
		System.out.println(" 5. ���� ��ü");
		System.out.println(" 6. �� ����");
		System.out.println(" 7. �� ����");
		System.out.println(" 9. ���� ����");
		System.out.println("------------------");
	}
}
