import java.util.Arrays;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		Parse p = new Parse();
		List<String> authors = p.getAnswers();
		QuoteGame g = new QuoteGame(authors);
		
		g.play();
	}

}
