package jp.bcat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import junit.framework.TestCase;
import org.junit.Test;

public class MainTest extends TestCase {
	
	@Test
	public void testIntegrated() throws IOException{
		String filename = "testinput.txt";
		BufferedReader in = new BufferedReader(new FileReader(filename));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true);
		Main main = new Main();
		System.out.println(in);
		try {
			main.process(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
