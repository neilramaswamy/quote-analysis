import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;

// looks at folder of all texts. looks at each individual file and puts all the
// sentences in an array list. makes and returns a HashMap where the key is
// AUTHOR-TITLE to that array list.
public class Parse {
	/*
	 * this will be returned at the end. goes from text attributes (in the format, "author>title") 
	 * to an ArrayList of the sentences of that specific text
	 */
	private HashMap<String, ArrayList<String>> listifiedTexts;

	/**
	 * Constructor.
	 */
	public Parse() {
		listifiedTexts = new HashMap<String, ArrayList<String>>();
	}
	
	/**
	 * getter
	 */
	public HashMap<String, ArrayList<String>> getData() {
		return listifiedTexts;
	}

	/**
	 * Fills the HashMap with a text's attributes mapped to an ArrayList of its sentences
	 */
	public void fillHashMap() {
		// fill up listifiedTexts
		ArrayList<ArrayList<String>> fileInfo = getFileNames();
		for (int i = 0; i < fileInfo.get(0).size(); i++) {
			listifiedTexts.put(fileInfo.get(0).get(i).substring(0,fileInfo.get(0).get(i).length()-4), //remove ".txt" with substring
								splitFile(new File(fileInfo.get(1).get(i))));
		listifiedTexts.remove(".DS_S");
		}
	}
	
	/**
	 * *Incomplete Method: Change to Relative File Path
	 * Gives information about the files in the folder texts.
	 * Gives back [ [name of files], [Strings of filePaths to a file] ]
	 * @return an ArrayList of 2 ArrayLists: one of "author>title" and the 
	 * 			other of the file path to the text file. 
	 */
	public ArrayList<ArrayList<String>> getFileNames() {
		ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
		File folder = new File("/Users/NeilRamaswamy/dev/quote-analysis/QuoteAnalysisJava/src/texts");
		// use relative paths!!
		// File folder = new File("./texts"); doesn't work
		// 2 parts: file path; text attributes
		
		ArrayList<String> filePaths = new ArrayList<String>();
		ArrayList<String> textAttributes = new ArrayList<String>();
		
		File[] listOfFiles = folder.listFiles();

		// convert file paths into Strings 
		for (int i = 0; i < listOfFiles.length; i++) {
				// System.out.println(listOfFiles[i]);
				filePaths.add(listOfFiles[i].toString());
				String path = listOfFiles[i].toString();
				String attributes = path.substring(path.lastIndexOf('/') + 1);
				textAttributes.add(attributes);
		}
		toReturn.add(textAttributes);
		toReturn.add(filePaths);
		for (int i = 0; i < 2; i++) {
			// for (String s: toReturn.get(i)) System.out.println("" + i + ": " + s);
		}
		return toReturn;
	}

	/**
	 * (Attempts) to separate a file (given a filePath) by sentences. Tries to 
	 * distinguish sentence-stopping periods from abbreviation periods (like Dr. or i.e.).
	 * @param filePath, the path to the file that is going to be split.
	 * @return	an ArrayList of the split sentences of a file.
	 */
	public static ArrayList<String> splitFile(File filePath) {
		ArrayList<String> toReturn = new ArrayList<String>();
		String content;
		try {
			// split the content by sentence and put into the ArrayList
			// code from here below is from 
			// https://stackoverflow.com/questions/2687012/split-string-into-sentences
			content = FileUtils.readFileToString(filePath);
			BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
			iterator.setText(content);
			int start = iterator.first();
			for (int end = iterator.next();
			    end != BreakIterator.DONE;
			    start = end, end = iterator.next()) {
			  toReturn.add(content.substring(start,end));
			}
			return toReturn;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		Parse p = new Parse();
		p.fillHashMap();
		HashMap<String, ArrayList<String>> map = p.getData();
		Set<String> keys = map.keySet();
		for (String k: keys) {
			System.out.println(k + ": ");
			ArrayList<String> sentences = map.get(k);
			for (int i = 0; i < sentences.size(); i++) {
				System.out.println(sentences.get(i));
			}
		}
	}
	
}