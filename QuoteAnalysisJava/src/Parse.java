import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

// looks at folder of all texts. looks at each individual file and puts all the
// sentences in an array list. makes and returns a HashMap where the key is
// AUTHOR-TITLE to that array list.
public class Parse {
	HashMap<String, ArrayList<String>> listifiedTexts;

	public Parse() {
		listifiedTexts = new HashMap<String, ArrayList<String>>();
	}

	public ArrayList<String> listifyFile(File path) {
		ArrayList<String> toReturn = new ArrayList<String>();
		
		
		return toReturn;
	}

	public HashMap<String, ArrayList<String>> getData() {
		return listifiedTexts;
	}
}