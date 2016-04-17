package com.br.pso;

import java.util.ArrayList;

public class PSOParticle {

	private ArrayList<Double> velocity;
	private ArrayList<Double> position;
	private Double pBest;
	private Double gBest;

	public ArrayList<Double> getVelocity() {
		return velocity;
	}

	public void setVelocity(ArrayList<Double> velocity) {
		this.velocity = velocity;
	}

	public ArrayList<Double> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Double> position) {
		this.position = position;
	}

	public Double getpBest() {
		return pBest;
	}

	public void setpBest(Double pBest) {
		this.pBest = pBest;
	}

	public Double getgBest() {
		return gBest;
	}

	public void setgBest(Double gBest) {
		this.gBest = gBest;
	}
	
	

}
