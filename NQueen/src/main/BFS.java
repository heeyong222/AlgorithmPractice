package main;

import java.util.LinkedList;
import java.util.List;

public class BFS implements Algorithm {
	public String getResult(int n) {
		return bfs(n, new boolean[n][n]);
	}

	private String bfs(int n, boolean[][] table) {
		List<boolean[][]> queue = new LinkedList<>();
		// Queue 초기화 작업
		for (int col = 0; col < n; col++) {
			table[0][col] = true; // 첫 번째 row의 각 칸 마다 퀸을 놓는다고 가정
			queue.add(deepCopy(table));
			table[0][col] = false; // 원상복귀
		}

		int indexRowArray[] = new int[n]; // Queue index의 row를 찾기 위한 배열
		indexRowArray[0] = 0;
		for (int row = 1; row < n; row++) { // index가 누적 n의 지수승을 지나갈 때마다 row 증가
			indexRowArray[row] = indexRowArray[row - 1] + (int) Math.pow(n, row);
		}

		int index = 0;
		while (!queue.isEmpty()) { // Queue가 비어있을 때까지 동작
			boolean beforeTable[][] = queue.get(index); // Queue에서 table get
			int row = n - 1;
			for (; row > 0; row--) { // 다음 변경할 row 찾기
				if (index >= indexRowArray[row]) {
					break;
				}
			}
			index++;
			if (row + 1 == n) { // 모든 row가 입력이 되었다면 nQueen 검사 수행
				if (Common.validCheck(n, beforeTable)) {
					return Common.getStringResult(n, beforeTable); // 결과를 찾았다면 문자열로 변환 후 반환
				} else {
					continue;
				}
			}
			for (int col = 0; col < n; col++) { // 다음 row의 각 col 마다 queen을 놓고 Queue에 추가
				beforeTable[row + 1][col] = true;
				queue.add(deepCopy(beforeTable));
				beforeTable[row + 1][col] = false;
			}
		}
		return Common.NO_SOLUTION; // 결과를 못찾았다면 NO_SOLUTION 반환
	}

	private boolean[][] deepCopy(boolean[][] original) { // 2차원 배열 깊은 복사용 메소드
		boolean[][] result = new boolean[original.length][original[0].length];
		for (int row = 0; row < original.length; row++) { // 각 row 마다 반복
			System.arraycopy(original[row], 0, result[row], 0, original[0].length); // 1차원 깊은 복사
		}
		return result;
	}

}
