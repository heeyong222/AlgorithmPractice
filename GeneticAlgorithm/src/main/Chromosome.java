package main;

public class Chromosome {
	private int table[]; // 퀸 배치를 저장할 체스판 인덱스
	private int countCollisions = 0; // 체스판 중 퀸들이 서로 충돌하는 카운트
	private double fitness = 0.0; // 적합도를 나타내는 변수

	public Chromosome(int[] table) {
		this.table = table;
	}

	public int[] getTable() {
		return table;
	}

	public void setTable(int[] table) {
		this.table = table;
	}

	public int getCountCollisions() {
		return countCollisions;
	}

	public void setCountCollisions(int countCollisions) {
		this.countCollisions = countCollisions;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

}