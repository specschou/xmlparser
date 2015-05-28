package com.jrj.myfc;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		Panel p = new Panel();
		int i = 0;
		Random r = new Random();
		while (p.win() == 0) {
			i++;
			int x = r.nextInt(15);
			int y = r.nextInt(15);
			p.addItem(x, y);
			System.out.println(p);
			System.out.println("");
		}
		System.out.println(p.win() + ":" + i);
	}

}
