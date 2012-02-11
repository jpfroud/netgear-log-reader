import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EventsFinder {

	private static final class EmlFileFilter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			return pathname.getName().endsWith(".eml");
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		if (null == args || args.length == 0){
			System.out.println("use: java EventsFinder <pathToNetgearLogs>");
			return;
		}
		
		String path = args[0];
		File logFolder = new File(path);
		
		if (!logFolder.isDirectory()){
			System.out.println("<"+path+"> is not a directory");
			return;
		}
		
		File[] logFiles = logFolder.listFiles(new EmlFileFilter());
		Set<String> eventSet = new HashSet<String>();
		List<String> listEvents = new ArrayList<String>();
		
		for (File f : logFiles){
			parse(f, eventSet, listEvents);
		}
		
		Collections.sort(listEvents);
		
		File result =  new File("src/main/resources/listOfEventsSoFar.txt");
		
		result.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(result));
		
		for(String s : listEvents){
			writer.write(s+'\n');
		}
		writer.close();
	}
	
	private static void parse(File f, Set<String>evenSet, List<String> listEvents) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		String line = reader.readLine();
		
		while (null != line){
			Pattern p = Pattern.compile("^(\\[.+?\\])(.+)");
			Matcher m = p.matcher(line);
			if (m.find()){
				String event = m.group(1);
				if (!evenSet.contains(event)){
					evenSet.add(event);
					listEvents.add(event+m.group(2));
				}
			}
			line = reader.readLine();
		}
		
		reader.close();
	}

}
