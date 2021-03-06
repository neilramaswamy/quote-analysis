import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuoteGame {
	
	public static final int MAX_DISTANCE = 2;
	
	Map<String, ArrayList<String>> passages;
	List<String> authors;
	
	public QuoteGame(List<String> authors) {
		Parse p = new Parse();
		passages = p.getData();
		
		
		this.authors = authors;
		
		
	}
	
	public void play() {
		System.out.println("Please enter identify the following passages"
				+ " using the syntax author>title. There will be 8 passages\n");
		
		Scanner s = new Scanner(System.in);
		
		int count = 0;
		int score = 0;
		
		while(count<8) {
			int passageIndex = (int) (Math.random()*passages.size());
			
			String author = authors.get(passageIndex);
			
			int sentenceIndex = (int) (Math.random()*(
					passages.get(author).size()-3));
			
			String quote="";
			if(passages.get(author).size()>sentenceIndex+2){
				quote = passages.get(author).get(sentenceIndex)+
						passages.get(author).get(sentenceIndex+1)+
						passages.get(author).get(sentenceIndex+2);
			}
			else{
				quote = passages.get(author).get(sentenceIndex);
			}
			
			System.out.println(quote);

			String answer = s.nextLine();
			
			count++;
			
			if(Levenshtein.distance(answer, authors.get(passageIndex))<=MAX_DISTANCE) {
				
				System.out.println("Correct! The identification was " + authors.get(passageIndex) + "."+
						"\n-------");
				
				score++;
			}
			else {
				System.out.println("Wrong! The identification was " + authors.get(passageIndex) + "."+
						"\n-------");
			}
						
			
		}
		s.close();
		
		System.out.println("Your score was "+ score+".");
	}
}
