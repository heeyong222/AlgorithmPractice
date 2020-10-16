package main;

public class DFS implements Algorithm {
	public String getResult(int n) {
		return dfs(n, new boolean[n][n], 0);
	}

	private String dfs(int n, boolean[][] table, int row) {
		if (n == row) { // 모든 row에 Queen을 배치했다면 유효성 체크
			if (Common.validCheck(n, table)) {
				return Common.getStringResult(n, table); // 유효성 체크를 통과했다면 결과를 찾은 것이므로 결과 반환
			} else {
				return Common.NO_SOLUTION; // 결과를 못 찾았다면 재귀
			}
		}
		for (int col = 0; col < n; col++) {
			table[row][col] = true; // 각 col마다 Queen 배치
			String result = dfs(n, table, row + 1); // 다음 row로 이동
			if (!result.equals(Common.NO_SOLUTION)) { // 결과를 정상적으로 찾았다면 재귀 더이상 동작하지 않고 반환
				return result;
			}
			table[row][col] = false; // Queen 배치 원상 복귀
		}
		return Common.NO_SOLUTION; // 결과를 못 찾았다면 재귀
	}
}
