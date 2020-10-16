package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HillClimbing {

	// 입력받은 N*N 사이즈 판에서 NQueen 문제 찾기
	public String solveNQueen(int n) {
		if (n == 2 || n == 3) { // N이 2 또는 3인 경우 답이 없음
			return "Nothing";
		}
		int count = 0;
		int[] table;
		do {
			table = generateTableRandomly(n); // 랜덤하게 테이블 생성
			count++; // 카운트 증가
			table = hillClimbing(table); // HillClimbing 알고리즘 수행
		} while (!isSolution(table)); // table이 정답이 될 수 있는지 확인. 정답이라면 종료
		System.out.println("Count: " + count);
		return getStringResult(table); // table을 출력 문자열로 변환하여 반환
	}

	public int solveNQueen4Test(int n) {
		int count = 0;
		int[] table;
		do {
			table = generateTableRandomly(n); // 랜덤하게 테이블 생성
			count++; // 카운트 증가
			table = hillClimbing(table); // HillClimbing 알고리즘 수행
		} while (!isSolution(table)); // table이 정답이 될 수 있는지 확인. 정답이라면 종료
		return count; // table을 출력 문자열로 변환하여 반환
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

	// Hill Climbing 알고리즘
	private int[] hillClimbing(int[] table) {
		int[] best = table;
		int[] current;
		int countCollisionOfCurrent = countCollision(table); // 현재 테이블의 충돌 카운트
		while (true) {
			current = best;
			List<int[]> candidateList = makeCandidates(table); // 현재 테이블로부터 나올 수 있는 후보 테이블들 생성
			for (int[] candidate : candidateList) { // 후보의 충돌 카운트를 비교하며 가장 충돌이 적은 후보 테이블을 선택
				int countCollisionOfCandidate = countCollision(candidate);
				if (countCollisionOfCurrent > countCollisionOfCandidate) {
					current = candidate;
					countCollisionOfCurrent = countCollisionOfCandidate;
				}
			}
			if (best == current) { // 만약 후보 테이블보다 기존 테이블이 더 좋다면 종료
				return best;
			}
			best = current; // 가장 좋은 테이블을 찾을 때까지 반복
		}
	}

	// 충돌 횟수 카운트 (가로 + 대각선)
	private int countCollision(int[] table) {
		return countRowCollision(table) + countDiagonalCollision(table);
	}

	// Row(가로) Queen 충돌 발생 횟수 찾는 메소드
	private int countRowCollision(int[] table) {
		int collision = 0;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				if (i == j) { // i와 j가 같을 땐 스킵
					continue;
				}
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
			for (int j = 0; j < table.length; j++) {
				if (i == j) { // i와 j가 같을 때 스킵
					continue;
				}
				int slope = Math.abs(i - j); // 기울기 계산
				if (table[i] == table[j] + slope) { // 같은 대각선에 있다면 충돌 카운트 증가
					collision++;
				}
				if (table[i] == table[j] - slope) { // 같은 역대각선에 있다면 충돌 카운트 증가
					collision++;
				}
			}
		}
		return collision / 2; // 중복 제거하기 위해 / 2
	}

	// 입력받은 테이블을 기준으로 후보 테이블 리스트 생성
	private List<int[]> makeCandidates(int[] table) {
		List<int[]> candidateList = new ArrayList<>();
		for (int i = 0; i < table.length; i++) { // 기존 테이블에서 하나의 퀸만 랜덤하게 다른 퀸 위치로 변경
			int[] preTable = Arrays.copyOfRange(table, 0, i);
			int[] postTable = Arrays.copyOfRange(table, i + 1, table.length);
			for (int j = 0; j < table.length; j++) {
				int[] candidate = new int[table.length];
				System.arraycopy(preTable, 0, candidate, 0, preTable.length);
				candidate[i] = (int) (Math.random() * table.length) + 1;
				System.arraycopy(postTable, 0, candidate, preTable.length + 1, postTable.length);
				candidateList.add(candidate);
			}
		}
		return candidateList;
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
