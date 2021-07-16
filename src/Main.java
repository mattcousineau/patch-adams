import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import com.twmacinta.util.MD5;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter writer = new PrintWriter("aipatchfile.txt", "UTF-8");
		scanLocalDirectory(writer, System.getProperty("user.dir"), System.getProperty("user.dir"));
	    writer.close();
	}

	public static void scanLocalDirectory(PrintWriter writer,String directoryName, String baseDirectory) throws NoSuchAlgorithmException, IOException {
	    File directory = new File(directoryName);
	    File[] fileList = directory.listFiles();
	    if(fileList != null) {
	        for (File file : fileList) {      
	            if (!file.getName().contains("patchadams.jar") && file.isFile()) {
	                System.out.println("processLocalPatchFile: Adding file path to fileList: " + file.getPath());
		        	writer.println(file.getPath().replace(baseDirectory, "") + "$" + createFileChecksumAlt(file.getAbsolutePath()));  //$ to split filename/checksum on
	            } else if (file.isDirectory()) {
	            	scanLocalDirectory(writer, file.getAbsolutePath(), baseDirectory);
	            }
	        }   
	    }
	}
	
	public static String createFileChecksumAlt(String filePath) throws IOException {
		String hash = MD5.asHex(MD5.getHash(new File(filePath)));
		return hash;
	}
}
