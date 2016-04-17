package com.br.pso.utils;

import com.br.pso.PSO;

public class Main {

	public static void main(String[] args) {
		PSO pso = new PSO(5, 5, 10000, "sphere", "local", false);
		
		pso.calculatePSO();

	}

}
