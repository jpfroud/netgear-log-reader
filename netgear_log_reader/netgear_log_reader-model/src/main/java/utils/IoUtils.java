package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class IoUtils {

	public static List<String> convert(String toParse) throws IOException {
		List<String> result = new ArrayList<String>();
		if (null != toParse) {
			BufferedReader reader = new BufferedReader(
					new StringReader(toParse));
			String line = reader.readLine();
			while (null != line) {
				String trimmed = line.trim();
				if (trimmed.length() > 0) {
					result.add(trimmed);
				}
				line = reader.readLine();
			}
			reader.close();
		}
		return result;
	}

	public static String fileToString(File input) throws FileNotFoundException,
			IOException {
		StringBuilder linesBuffer = new StringBuilder();
		if (null != input && input.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String line = reader.readLine();
			while (null != line) {
				linesBuffer.append(line + '\n');
				line = reader.readLine();
			}
			reader.close();
		}
		return linesBuffer.toString();
	}

}
