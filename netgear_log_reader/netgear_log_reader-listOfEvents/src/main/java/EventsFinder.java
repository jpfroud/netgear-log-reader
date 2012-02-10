import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
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
		
		for (File f : logFiles){
			parse(f, eventSet);
		}
		
		List<String> listEvents = new ArrayList<String>();
		
		for (String s : eventSet){
			listEvents.add(s);
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
	
	private static void parse(File f, Set<String>evenSet) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		String line = reader.readLine();
		
		while (null != line){
			Pattern p = Pattern.compile("^\\[.+?\\]");
			Matcher m = p.matcher(line);
			if (m.find()){
				String group = m.group();
				evenSet.add(group.substring(1,group.length()-1));
			}
			line = reader.readLine();
		}
		
		reader.close();
	}

}
