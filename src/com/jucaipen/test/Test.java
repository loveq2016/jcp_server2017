package com.jucaipen.test;
import com.jucaipen.utils.RandomUtils;

public class Test {
	public static void main(String[] args) {
		final Thread A=new Thread(){@Override
		public void run() {
			print("A");
		}};
		
		
		Thread B=new Thread(){@Override
		public void run() {
			try {
				A.join();
				print("B");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}};
		
		A.start();
		B.start();
	}
	
	public static void print(String s){
		int i=0;
		while (i<3) {
			i++;
			try {
				Thread.sleep(1000);
				System.out.println("Ïß³Ì"+s+":"+RandomUtils.getRandomData(5, 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
