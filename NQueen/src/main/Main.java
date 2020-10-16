package main;

import java.io.File;

//Java Application 실행 시 동작할 클래스
public class Main {
	public static void main(String[] args) {
		if (args.length != 2) { // Java Argument 2개 입력 필수
			System.out.println("Arguments have to be 2");
			return;
		}
		int n = Integer.valueOf(args[0]); // 첫 번째 Argument N
		File directory = new File(args[1]); // 두 번째 Argument Result 파일 디렉토리

		long dfsStart = System.currentTimeMillis();
		String dfsResult = new DFS().getResult(n); // DFS 수행
		long dfsFinish = System.currentTimeMillis();
		double dfsElapsedTime = (dfsFinish - dfsStart) / 1000F; // DFS 소요 시간 측정
		System.out.println(">DFS"); // DFS 결과 콘솔 출력
		System.out.println("Location : " + dfsResult);
		System.out.println("Time : " + dfsElapsedTime);

		System.out.println();

		long bfsStart = System.currentTimeMillis();
		String bfsResult = new BFS().getResult(n); // BFS 수행
		long bfsFinish = System.currentTimeMillis();
		double bfsElapsedTime = (bfsFinish - bfsStart) / 1000F;
		System.out.println(">BFS"); // BFS 결과 콘솔 출력
		System.out.println("Location : " + bfsResult);
		System.out.println("Time : " + bfsElapsedTime);

		ResultFileWriter resultFileWriter = new ResultFileWriter(directory, n); // DFS & BFS 결과를 파일 출력하기 위한 객체 생성
		resultFileWriter.write(dfsResult, dfsElapsedTime, bfsResult, bfsElapsedTime); // 파일 내용 전달하여 파일 출력
	}
}
