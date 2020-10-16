package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	private static int INIT_NUM_OF_CHROMOSOMES = 1000; // 초기 Chromosome 생성 개수 정의
	private static double CROSSOVER_PROBABILITY = 0.8; // Crossover 확률 정의
	private static double MUTATION_PROBABILITY = 0.01; // Mutation 확률 정의
	private List<Chromosome> population = new ArrayList<>(); // Chromosome을 저장할 Population

	// 입력받은 N*N 사이즈 판에서 NQueen 문제 찾기
	public String solveNQueen(int n) {
		if (n == 2 || n == 3) { // N이 2 또는 3인 경우 답이 없음
			return "Nothing";
		}

		initPopulation(n); // 초기 Population 세팅

		int count = 0;
		int[] resultTable;
		do {
			normalizeFitnessFunction(population); // Fitness 세팅
			Chromosome firstParentChromosome = selectChromosome(population, null); // 첫 번째 부모 Chromosome 선택
			Chromosome secondParentChromosome = selectChromosome(population, firstParentChromosome); // 두 번째 부모
																										// Chromosome 선택
			Chromosome childChromosome = crossOver(firstParentChromosome, secondParentChromosome); // 자식
																									// Chromosome(=Offspring)
																									// 생성
			mutate(childChromosome); // 돌연변이 생성
			population.add(childChromosome); // Population에 자식 Chromosome 추가
			eliminateWeakChromosome(firstParentChromosome, secondParentChromosome, childChromosome); // 약한 Chromosome 삭제
																										// 작업
			if (isSolution(childChromosome.getTable())) { // 정답을 찾을 경우 종료
				resultTable = childChromosome.getTable();
				break;
			}
			count++; // 카운트 증가
		} while (true); // table이 정답이 될 수 있는지 확인. 정답이라면 종료
		System.out.println("Count: " + count);
		return getStringResult(resultTable); // table을 출력 문자열로 변환하여 반환
	}

	private void initPopulation(int n) {
		for (int i = 0; i < INIT_NUM_OF_CHROMOSOMES; i++) { // 초기 정의된 개수만큼 생성
			Chromosome newChromosome = new Chromosome(generateTableRandomly(n)); // 퀸 배치는 랜덤하게 생성
			population.add(newChromosome);
		}
		return;
	}

	// Population에 저장된 Chromosome의 Fitness 계산
	private void normalizeFitnessFunction(List<Chromosome> population) {
		int maxCountCollisions = 0;
		// countCollisions가 계산되지 않은 Chromosome 계산
		for (Chromosome chromosome : population) {
			int countCollisions = chromosome.getCountCollisions();
			if (chromosome.getCountCollisions() == 0) {
				countCollisions = countCollision(chromosome.getTable());
				chromosome.setCountCollisions(countCollisions);
			}
			if (maxCountCollisions < countCollisions) {
				maxCountCollisions = countCollisions;
			}
		}

		// 가장 CountCollisions가 높은 Chromosome의 Fitness는 0점, 나머지는 maxCountCollisions를 기반으로
		// 계산
		for (Chromosome chromosome : population) {
			chromosome
					.setFitness(((double) (maxCountCollisions - chromosome.getCountCollisions()) / maxCountCollisions));
		}
	}

	// 부모 Chromosome 선택
	private Chromosome selectChromosome(List<Chromosome> population, Chromosome previousParentChromosome) {
		while (true) {
			// 랜덤하게 Chromosome 선택
			Chromosome chromosome = population.get(new Random().nextInt(population.size()));
			// 두 번째 부모 Chromosome 선택인 경우 첫 번째와 같지 않도록 조건 추가
			if (previousParentChromosome != null
					&& Arrays.equals(previousParentChromosome.getTable(), chromosome.getTable())) {
				continue;
			}
			// Chromosome의 Fitness에 따라 부모로 선택할지 결정
			double selectProbability = new Random().nextDouble();
			if (selectProbability <= chromosome.getCountCollisions()) {
				return chromosome;
			}
		}
	}

	// 두 개의 부모 Chromosome으로부터 하나의 자식 Chromosome 생성
	private Chromosome crossOver(Chromosome x, Chromosome y) {
		int[] table = new int[x.getTable().length];
		double crossoverRate = new Random().nextDouble();
		if (crossoverRate <= CROSSOVER_PROBABILITY) { // Crossover 확률적으로 수행
			int point = new Random().nextInt(x.getTable().length); // 인덱스 랜덤하게 선택
			for (int i = 0; i < point; i++) { // 0~인덱스까지는 첫 번째 부모로부터 복사
				table[i] = x.getTable()[i];
			}

			for (int i = point; i < y.getTable().length; i++) { // 인덱스~N까지는 두 번째 부모로부터 복사
				table[i] = y.getTable()[i];
			}
		} else { // Crossover를 하지 않을 경우 부모 Chromosome들 중 좋은 부모의 값을 복사
			if (x.getCountCollisions() < y.getCountCollisions()) {
				table = y.getTable();
			} else {
				table = x.getTable();
			}
		}

		Chromosome childChromosome = new Chromosome(table);
		childChromosome.setCountCollisions(countCollision(table));
		return childChromosome;
	}

	// 돌연변이 생성
	private boolean mutate(Chromosome chromosome) {
		double mutateRate = new Random().nextDouble();
		if (mutateRate <= MUTATION_PROBABILITY) { // Mutation 확률적으로 돌연변이 생성
			int[] table = chromosome.getTable();
			table[new Random().nextInt(table.length)] = new Random().nextInt(table.length) + 1;
			chromosome.setCountCollisions(countCollision(table));
			return true;
		}
		return false;
	}

	// 약한 Chromosome 삭제
	private void eliminateWeakChromosome(Chromosome firstParentChromosome, Chromosome secondParentChromosome,
			Chromosome childChromosome) {
		if (firstParentChromosome.getCountCollisions() > childChromosome.getCountCollisions()) {
			population.remove(firstParentChromosome);
		}
		if (secondParentChromosome.getCountCollisions() > childChromosome.getCountCollisions()) {
			population.remove(secondParentChromosome);
		}
		if (childChromosome.getCountCollisions() > firstParentChromosome.getCountCollisions()
				&& childChromosome.getCountCollisions() > secondParentChromosome.getCountCollisions()) {
			population.remove(childChromosome);
		}
	}

	// 랜덤하게 퀸 배치하는 메소드
	private int[] generateTableRandomly(int n) {
		int[] table = new int[n];
		for (int i = 0; i < n; i++) {
			table[i] = (int) (Math.random() * n) + 1;
		}
		return table;
	}

	// 현재 테이블에 놓여있는 퀸들이 서로 충돌하지 않는지 확인. 충돌이 없다면 정답
	private boolean isSolution(int[] table) {
		return countCollision(table) == 0;
	}

	// 충돌 횟수 카운트 (가로 + 대각선)
	private int countCollision(int[] table) {
		return countRowCollision(table) + countDiagonalCollision(table);
	}

	// Row(가로) Queen 충돌 발생 횟수 찾는 메소드
	private int countRowCollision(int[] table) {
		int collision = 0;
		for (int i = 0; i < table.length; i++) {
			for (int j = i + 1; j < table.length; j++) {
				if (table[i] == table[j]) { // 같은 Row에 있을 때 충돌 카운트 증가
					collision++;
				}
			}
		}
		return collision;
	}

	// Diagonal(대각선) Queen 충돌 발생 횟수 찾는 메소드
	private int countDiagonalCollision(int[] table) {
		int collision = 0;
		for (int i = 0; i < table.length; i++) {
			for (int j = i + 1; j < table.length; j++) {
				int slope = Math.abs(i - j); // 기울기 계산
				if (table[i] == table[j] + slope) { // 같은 대각선에 있다면 충돌 카운트 증가
					collision++;
				}
				if (table[i] == table[j] - slope) { // 같은 역대각선에 있다면 충돌 카운트 증가
					collision++;
				}
			}
		}
		return collision;
	}

	// 결과물 반환
	private String getStringResult(int[] table) {
		String result = "";
		for (int index : table) {
			result += index + " ";
		}
		return result;
	}
}
