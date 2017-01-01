package rmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class AddrReader {
	private static final File PATH = new File("addr.txt");

	private static String ADDR;
	static {
		System.out.println(PATH.getAbsolutePath());
		try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
			ADDR = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String getAddr() {
		return ADDR == null ? "localhost" : ADDR;
	}
}
