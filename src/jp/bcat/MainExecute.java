package jp.bcat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainExecute {

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		String filename = "testinput.txt";
		BufferedReader in = new BufferedReader(new FileReader(filename));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true);
		Main main = new Main(true);
		try {
			main.process(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
