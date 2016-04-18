package com.br.pso;

import java.util.ArrayList;

public class PSOParticle {

	private ArrayList<Double> velocity;
	private ArrayList<Double> position;
	private ArrayList<Double> pBest;
	private ArrayList<Double> gBest;

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

	public ArrayList<Double> getpBest() {
		return pBest;
	}

	public void setpBest(ArrayList<Double> pBest) {
		this.pBest = pBest;
	}

	public ArrayList<Double> getgBest() {
		return gBest;
	}

	public void setgBest(ArrayList<Double> gBest) {
		this.gBest = gBest;
	}
	
	

}
