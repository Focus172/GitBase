package testers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import git.Tree;

class treeTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File f =new File("objects");
		f.mkdir();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File objects = new File ("objects");
        objects.mkdir();
        for (File file: Objects.requireNonNull(objects.listFiles())) {
        	if (!file.isDirectory()) {
        		file.delete();
        	}
        }
		objects.delete();
	}

	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		ArrayList<String> alisted = new ArrayList<String>();
		alisted.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f filename1.txt");
		alisted.add("blob : 01d82591292494afd1602d175e165f94992f6f5f someOtherFile.jpg");
		alisted.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 anotherFileWhichDoesntExist.txt");
		alisted.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b file4.txt");
		alisted.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976 makeupThisName.txt");
		Tree branch = new Tree(alisted);
		File myObj = new File ("objects/ed0786deb4cfc3b2f44f2f2cc379e0270efa8e44");
		assertTrue (myObj.exists());
		Path filePath = Path.of("objects/ed0786deb4cfc3b2f44f2f2cc379e0270efa8e44");
		assertTrue (Files.readString(filePath).equals("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f filename1.txt\n"
				+ "blob : 01d82591292494afd1602d175e165f94992f6f5f someOtherFile.jpg\n"
				+ "blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83 anotherFileWhichDoesntExist.txt\n"
				+ "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b file4.txt\n"
				+ "tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976 makeupThisName.txt"));
	}

}
