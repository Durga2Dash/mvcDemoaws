package com.mindtree.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyBoardUtil {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int getInt() throws IOException {
		int i = 0;
		while (true) {
			try {
				i = Integer.parseInt(br.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Enter valid data!!!");
			}
		}

		return i;
	}

	public static float getFloat() throws IOException {
		float i = 0.0f;
		while (true) {
			try  {
				i = Float.parseFloat(br.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Enter valid data!!!");
			}
		}

		return i;
	}

	public static double getDouble() throws IOException {
		double i = 0.0;
		while (true) {
			try {
				i = Double.parseDouble(br.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Enter valid data!!!");
			}
		}
		
		return i;
	}

	public static String getString() throws IOException {
		return br.readLine();
	}

	public static long getLong() throws IOException {
		long i = 0;
		while (true) {
			try {
				i = Long.parseLong(br.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Enter valid data!!!");
			}
		}

		return i;
	}
}
