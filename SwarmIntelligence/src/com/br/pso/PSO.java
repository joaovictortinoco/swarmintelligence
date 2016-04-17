package com.br.pso;

import java.util.ArrayList;

import com.br.pso.utils.PSOConstants;

public class PSO {

	private int numParticles, numDimensions, numIterations;
	private String functionType, topology;
	private boolean hasClerc;

	public PSO(int numParticles, int numDimensions, int numIterations,
			String functionType, String topology, boolean hasClerc) {
		this.numParticles = numParticles;
		this.numIterations = numIterations;
		this.numDimensions = numDimensions;
		this.functionType = functionType;
		this.topology = topology;
		this.setHasClerc(hasClerc);
	}

	public int getNumParticle() {
		return numParticles;
	}

	public int getNumDimension() {
		return numDimensions;
	}

	public int getNumIterations() {
		return numIterations;
	}

	public String getFunctionType() {
		return functionType;
	}

	public String getTopology() {
		return topology;
	}

	public ArrayList<PSOParticle> initParticles(String functionType) {

		ArrayList<PSOParticle> particles = new ArrayList<PSOParticle>(
				numParticles);
		int i = 0;

		while (i < numParticles) {
			initParticle(functionType);
		}

		return particles;
	}

	public PSOParticle initParticle(String functionType) {
		PSOParticle particle = new PSOParticle();

		while (numDimensions < numParticles) {

			if (functionType.equals(PSOConstants.SPHERE_FUNCTION)) {
				int i=0;
				ArrayList<Double> velocity = new ArrayList<Double>(numDimensions);
				ArrayList<Double> position = new ArrayList<Double>(numDimensions);
				ArrayList<Double> pBest = new ArrayList<Double>(numDimensions);
				ArrayList<Double> gBest = new ArrayList<Double>(numDimensions);
				
				while(i<numDimensions){
					//COMO GERAR OS VALORES ALEATORIOS?
					
				}
				particle.setVelocity(velocity);
				particle.setPosition(position);
				particle.setpBest(pBest);
				particle.setgBest(gBest);
			}
		}

		return particle;
	}

	public boolean isHasClerc() {
		return hasClerc;
	}

	public void setHasClerc(boolean hasClerc) {
		this.hasClerc = hasClerc;
	}

	public void calculatePSO() {
		if (topology.equals(PSOConstants.LOCAL_TOPOLOGY)) {
			calculatePSOLocal(hasClerc);
		} else if (topology.equals(PSOConstants.FOCAL_TOPOLOGY)) {
			calculatePSOFocal(hasClerc);
		} else if (topology.equals(PSOConstants.GLOBAL_TOPOLOGY)) {
			calculatePSOGlobal(hasClerc);
		}
	}

	public void calculatePSOLocal(boolean hasClerc) {
		double r1, r2, x = 1;

		if (hasClerc) {
			x = PSOConstants.clercConstrinctionFactor;
		}

		if (functionType.equals(PSOConstants.SPHERE_FUNCTION)) {

			ArrayList<PSOParticle> particles = initParticles(PSOConstants.SPHERE_FUNCTION);

		} else if (functionType.equals(PSOConstants.RASTRIGIN_FUNCTION)) {

		} else if (functionType.equals(PSOConstants.ROSENBROCK_FUNCTION)) {

		}

	}

	public void calculatePSOGlobal(boolean hasClerc) {
		double r1, r2, x = 1;

		if (hasClerc) {
			x = PSOConstants.clercConstrinctionFactor;
		}

		if (functionType.equals(PSOConstants.SPHERE_FUNCTION)) {

		} else if (functionType.equals(PSOConstants.RASTRIGIN_FUNCTION)) {

		} else if (functionType.equals(PSOConstants.ROSENBROCK_FUNCTION)) {

		}
	}

	public void calculatePSOFocal(boolean hasClerc) {
		double r1, r2, x = 1;

		if (hasClerc) {
			x = PSOConstants.clercConstrinctionFactor;
		}

		if (functionType.equals(PSOConstants.SPHERE_FUNCTION)) {

		} else if (functionType.equals(PSOConstants.RASTRIGIN_FUNCTION)) {

		} else if (functionType.equals(PSOConstants.ROSENBROCK_FUNCTION)) {

		}
	}

}