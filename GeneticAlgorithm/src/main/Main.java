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
		System.out.println(">Genetic Algorithm"); // Genetic Algorithm 결과 콘솔 출력
		long startTime = System.currentTimeMillis();
		String result = new GeneticAlgorithm().solveNQueen(n);
		double elapsedTime = (System.currentTimeMillis() - startTime) / 1000F;
		System.out.println(result);
		System.out.println("Total Elapsed Time : " + elapsedTime);
		ResultFileWriter resultFileWriter = new ResultFileWriter(directory, n);
		resultFileWriter.write(result, elapsedTime);
	}
}
