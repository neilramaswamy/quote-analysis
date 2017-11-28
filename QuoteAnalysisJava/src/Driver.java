import java.util.Arrays;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		List<String> authors = Arrays.asList("Emerson");
		QuoteGame g = new QuoteGame(authors);
		
		g.play();
	}

}
