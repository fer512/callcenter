package com.almundo.util;

import java.util.Random;

public class RandomDuracion {
	private Random random = new Random();
	private Integer MIN = 5;
	private Integer MAX = 10;
	
	public Integer esperar(){
		return random.nextInt((MAX - MIN) + 1) + MIN;
	}
	
}
