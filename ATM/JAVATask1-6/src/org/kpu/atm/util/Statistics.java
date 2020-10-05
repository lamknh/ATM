package org.kpu.atm.util;

import org.kpu.atm.bank.Account;

public class Statistics {
	public static int sum(Account [] account, int size) { //총액
		int sum = 0;
		
		for (int i=0; i < size; i++) {
			sum += account[i].getnBalance();
		}
		return sum;
	}
	public static double average(Account [] account, int size) {	//평균
		int sum = 0;
		
		for (int i=0; i < size; i++) {
			sum += (double)account[i].getnBalance();
		}
		double avr = sum / (double)size;
		return avr;
	}
	public static int max(Account [] account, int size) {	//최고
		int max = 0;
		
		for (int i=0; i < size; i++) {
			if (max < account[i].getnBalance()) {
				max = account[i].getnBalance();
			}
		}
		return max;
	}
	public static Account [] sort(Account [] account, int size) {	//정렬
		for (int i = 0; i < size; i++) {
			for(int j = 1; j < size; j++) {
				if(account[j-1].getnBalance() < account[j].getnBalance()) {
					Account tmpAcc = account[i];
					account[i] = account[j];
					account[j] = tmpAcc; // 버블 정렬
				}
			}
		}
		return account;
	}

}
