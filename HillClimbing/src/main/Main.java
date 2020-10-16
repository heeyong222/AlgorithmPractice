package main;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		if (args.length != 2) { // Java Argument 2개 입력 필수
			System.out.println("Arguments have to include 2 arguments");
			return;
		}
		int n = Integer.valueOf(args[0]); // 첫 번째 Argument N
		File directory = new File(args[1]); // 두 번째 Argument Result 파일 디렉토리
//		n = 8;
//		int limit = 1000;
//		long sumCount = 0;
//		double totalElapsedTime = 0;
//		for (int i = 0; i < limit; i++) {
		System.out.println(">Hill Climbing"); // Hill Climbing 결과 콘솔 출력
		long startTime = System.currentTimeMillis();
		String result = new HillClimbing().solveNQueen(n);
//			sumCount += new HillClimbing().solveNQueen4Test(n);
		double elapsedTime = (System.currentTimeMillis() - startTime) / 1000F;
		System.out.println(result);
		System.out.println("Total Elapsed Time : " + elapsedTime);
//			totalElapsedTime += elapsedTime;
//		}
//		System.out.println("평균: " + (double) sumCount / limit + " totalElapsedTime: " + totalElapsedTime / limit);
		ResultFileWriter resultFileWriter = new ResultFileWriter(directory, n);
		resultFileWriter.write(result, elapsedTime);
	}
}
