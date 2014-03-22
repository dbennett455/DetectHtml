import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.github.DetectHtml;
import org.junit.Test;

public class DetectHtmlTest {

	@Test
	public void test() {

		boolean reportToConsole=false;
		boolean fileNotFound=false;

		// iterate through HTML files
		File[] htmlFiles = new File("testdata/html/").listFiles();
		if (reportToConsole)
			System.out.println("Testing HTML files\n-----");
		for (File htmlFile : htmlFiles) {
			fileNotFound=false;
			try {
				String htmlContent = new Scanner(htmlFile).useDelimiter("\\Z").next();
				boolean isHtml=DetectHtml.isHtml(htmlContent);
				assertTrue(isHtml);
				if (reportToConsole && isHtml)
					System.out.println(htmlFile.getName() + " is HTML");
			} catch (FileNotFoundException fnfe) {
				fileNotFound=true;
			}
			assertFalse(fileNotFound);
		}
		// iterate through non-HTML files
		File[] textFiles = new File("testdata/text/").listFiles();
		if (reportToConsole)
			System.out.println("\nTesting non-HTML test files\n-----");
		for (File textFile : textFiles) {
			fileNotFound=false;
			try {
				String textContent = new Scanner(textFile).useDelimiter("\\Z").next();
				boolean isHtml=DetectHtml.isHtml(textContent);
				assertFalse(isHtml);
				if (reportToConsole && !isHtml)
					System.out.println(textFile.getName() + " is not HTML");
			} catch (FileNotFoundException fnfe) {
				fileNotFound=true;
			}
			assertFalse(fileNotFound);
		}

	}

}
