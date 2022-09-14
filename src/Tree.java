import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
	private String bigString;
	private String shawed;
	private HashMap<String, String> mapped;
	
	public Tree(ArrayList<String> listOfString) throws NoSuchAlgorithmException, IOException {
		mapped = new HashMap<String, String>();
		bigString = listOfString.get(0);
		mapped.put(listOfString.get(0).substring(0,4), listOfString.get(0).substring(7));
		for(int i=1; i<listOfString.size(); i++) {
			bigString = bigString + "\n" +listOfString.get(i);
			mapped.put(listOfString.get(i).substring(0,4), listOfString.get(i).substring(7));
		}
		shawed = GenerateHash(bigString);
		makeAndWriteToFile();
	}
	
	public String getBigString() {
		return bigString;
	}
	
	public String getShawed() {
		return shawed;
	}
	
	public void makeAndWriteToFile() throws IOException {
		File fil = new File("./objects/" + shawed);
		if(!fil.exists()) { //only makes new file if file does not already exist
			fil.createNewFile();
			FileWriter fw = new FileWriter(fil, true);
			fw.write(bigString);
			fw.close();
		}
		
	}
	
	public String GenerateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest objSHA = MessageDigest.getInstance("SHA-1");
        byte[] bytSHA = objSHA.digest(input.getBytes());
        BigInteger intNumber = new BigInteger(1, bytSHA);
        String strHashCode = intNumber.toString(16);
		
        // pad with 0 if the hexa digits are less then 40.
        while (strHashCode.length() < 40) {
            strHashCode = "0" + strHashCode;
        }
        return strHashCode;
    }
}
