package com.br.pso;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.print.attribute.standard.NumberOfDocuments;

import com.br.pso.utils.PSOConstants;

public class PSO {

	private int numParticles, numDimensions, numIterations;
	private String functionType, topology;
	private boolean hasClerc;

	private final static Logger log = Logger.getLogger(PSO.class.getName());

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

		log.info("START initParticles");
		while (i < numParticles) {
			particles.add(initParticle(functionType));
			i++;
		}

		log.info("END initParticles");

		return particles;
	}

	public PSOParticle initParticle(String functionType) {
		PSOParticle particle = new PSOParticle();
		int k = 0;

		log.info("initParticle: Initialize Particles");

		while (k < numParticles) {

			if (functionType.equals(PSOConstants.SPHERE_FUNCTION)) {
				int i = 0;
				Double min = -100.0, max = 100.0;
				Random random = new Random();

				ArrayList<Double> velocity = new ArrayList<Double>(
						numDimensions);
				ArrayList<Double> position = new ArrayList<Double>(
						numDimensions);
				ArrayList<Double> pBest = new ArrayList<Double>(numDimensions);
				ArrayList<Double> gBest = pBest;

				log.info("initParticle: Initialize velocity and position with random values");

				while (i < numDimensions) {
					random = new Random();

					for (int j = 0; j < numDimensions; j++) {
						velocity.add(j, min + (max - min) * random.nextDouble());
						position.add(j, min + (max - min) * random.nextDouble());
					}

					i++;

				}

				log.info("initParticle: Velocity size: " + velocity.size());
				log.info("initParticle: Position size: " + position.size());

				particle.setVelocity(velocity);
				particle.setPosition(position);
				particle.setpBest(position);
				particle.setgBest(position);
			}

			k++;
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
		} else
			throw new RuntimeException("No topology with name " + topology);

	}

	public void calculatePSOLocal(boolean hasClerc) {
		Random random = new Random();
		double r1 = random.nextDouble(), r2 = random.nextDouble(), w = 1, c1 = PSOConstants.c1, c2 = PSOConstants.c2;

		log.info("calculatePSOLocal: Initialize function");

		if (hasClerc) {
			w = PSOConstants.clercConstrinctionFactor;
		}

		ArrayList<PSOParticle> particles = initParticles(PSOConstants.SPHERE_FUNCTION);
		ArrayList<Double> pbest1 = new ArrayList<Double>(numDimensions);

		for (int i = 0; i < numIterations; i++) {

			for (int k = 0; k < particles.size(); k++) {
				PSOParticle psoParticle = particles.get(k);				
				// Evaluate fitness for each particle with respective function
				// type
				if (functionType.equals(PSOConstants.SPHERE_FUNCTION)) {
					log.info("calculatePSOLocal: Calculates PSO for sphere function");

					Double fitness = calculateFitnessSphere(psoParticle
							.getPosition());

					if (fitness < calculateFitnessSphere(pbest1)) {
						psoParticle.setpBest(psoParticle.getPosition());
						pbest1 = psoParticle.getPosition();
						System.out.println("PBEST " + k + ": "+ psoParticle.getPosition());

						if (fitness < calculateFitnessSphere(psoParticle
								.getgBest())) {
							psoParticle.setgBest(psoParticle.getPosition());
						}
					}

				} else if (functionType.equals(PSOConstants.RASTRIGIN_FUNCTION)) {
					log.info("calculatePSOLocal: Calculate PSO for sphere function");

					
					// // Double fitness =
					// calculateFitnessRastrigin(psoParticle.getPosition());
					//
					// if (fitness < psoParticle.getpBest()) {
					// psoParticle.setpBest(fitness);
					//
					// if (fitness < psoParticle.getgBest()) {
					// psoParticle.setgBest(fitness);
					// }
					// }

				} else if (functionType
						.equals(PSOConstants.ROSENBROCK_FUNCTION)) {
					log.info("calculatePSOLocal: Calculate PSO for sphere function");
					//
					// Double fitness =
					// calculateFitnessRosenbrock(psoParticle.getPosition());
					//
					// if (fitness < psoParticle.getpBest()) {
					// psoParticle.setpBest(fitness);
					//
					// if (fitness < psoParticle.getgBest()) {
					// psoParticle.setgBest(fitness);
					// }
					// }
				}

				// Calculates velocity for each particle
				ArrayList<Double> velocityParticle = psoParticle.getVelocity();

				for (int l = 0; l < velocityParticle.size(); l++) {
					Double v = velocityParticle.get(l);
					Double position = psoParticle.getPosition().get(l);
					Double pBest = psoParticle.getpBest().get(l);
					Double gBest = psoParticle.getgBest().get(l);
					v = w * v + c1 * r1 * (pBest - position) + c2 * r2
							* (gBest - position);
					velocityParticle.set(l, v);
				}

				psoParticle.setVelocity(velocityParticle);

				// Calculates position for each particle

				ArrayList<Double> positions = psoParticle.getPosition();

				for (int m = 0; m < positions.size(); m++) {
					Double pos = positions.get(m);
					pos = pos + velocityParticle.get(m);
					positions.set(m, pos);
				}

				psoParticle.setPosition(positions);

			}
		}

	}

	private Double calculateFitnessSphere(ArrayList<Double> position) {

		Double sum = 0.0;

		for (Double n : position) {

			sum += Math.pow(n, 2);

		}

		return sum;

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