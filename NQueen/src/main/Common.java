package main;

public class Common { // 공통적으로 사용하는 유틸성 클래스
	public static String NO_SOLUTION = "No solution";

	public static boolean validCheck(int n, boolean[][] table) { // 입력으로 들어온 테이블의 NQueen 유효성 체크
		boolean validRow[] = new boolean[n]; // Row 체크용
		boolean validCol[] = new boolean[n]; // Col 체크용
		boolean validDiagonal[] = new boolean[n + n]; // 대각선 체크용
		boolean validInverseDiagonal[] = new boolean[n + n]; // 역대각선 체크용

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (!table[row][col]) { // Queen이 있는 곳만 검사
					continue;
				}
				// 유효성 체크용 배열에 이미 들어가 있다면 유효하지 않음
				if (validRow[row] || validCol[col] || validDiagonal[row + col] || validInverseDiagonal[n + row - col]) {
					return false;
				}
				validRow[row] = table[row][col];
				validCol[col] = table[row][col];
				validDiagonal[row + col] = table[row][col]; // 대각선은 row와 col을 더한 것
				validInverseDiagonal[n + row - col] = table[row][col]; // 역대각선은 row-col인데, 음수가 나올 수 있으므로 n을 더함
			}
		}
		return true;
	}

	public static String getStringResult(int n, boolean[][] table) { // NQueen을 찾은 테이블 결과를 문자열로 변환해주는 메소드
		String result = "";
		for (int col = 0; col < n; col++) {
			for (int row = 0; row < n; row++) {
				if (table[row][col]) {
					result = result + row + " "; // row append 및 white space 한 칸 추가
				}
			}
		}
		return result;
	}
}
