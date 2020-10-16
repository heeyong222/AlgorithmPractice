package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// 파일 출력 클래스
public class ResultFileWriter {
	private File resultFile; // 출력 파일

	public ResultFileWriter(File directory, int n) {
		if (!directory.exists()) { // 입력받은 디렉토리 경로에 폴더가 없다면 폴더 생성
			directory.mkdirs();
		}
		resultFile = new File(directory, "result" + n + ".txt"); // Result File: 폴더 하위 resultN.txt 파일
	}

	public void write(String dfsResult, double dfsElapsedTime, String bfsResult, double bfsElapsedTime) {
		try {
			// 전달받은 DFS & BFS 정보 출력
			FileWriter fw = new FileWriter(resultFile);
			fw.write(">DFS");
			fw.write("Location : " + dfsResult);
			fw.write("Time : " + dfsElapsedTime);
			fw.write("");
			fw.write(">BFS");
			fw.write("Location : " + bfsResult);
			fw.write("Time : " + bfsElapsedTime);
			fw.close();
			System.out.println("Result File: " + resultFile.getAbsolutePath()); // Result File 경로 출력
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
