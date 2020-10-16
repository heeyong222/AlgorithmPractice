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

	public void write(String result, double elapsedTime) {
		try {
			// 전달받은 Hill Climbing 정보 출력
			FileWriter fw = new FileWriter(resultFile);
			fw.write(">Hill Climbing\n");
			fw.write(result + "\n");
			fw.write("Total Elapsed Time : " + elapsedTime + "\n");
			fw.close();
			System.out.println("Result File: " + resultFile.getAbsolutePath()); // Result File 경로 출력
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
