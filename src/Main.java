import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter writer = new PrintWriter("testfile.txt", "UTF-8");
		scanLocalDirectory(writer, System.getProperty("user.dir"), System.getProperty("user.dir"));
	    writer.close();
	}

	public static void scanLocalDirectory(PrintWriter writer,String directoryName, String baseDirectory) throws NoSuchAlgorithmException, IOException {
	    File directory = new File(directoryName);
	    File[] fileList = directory.listFiles();
	    if(fileList != null) {
	        for (File file : fileList) {      
	            if (file.isFile()) {
	                System.out.println("processLocalPatchFile: Adding file path to fileList: " + file.getPath());
		        	    writer.println(file.getPath().replace(baseDirectory, "") + "$" + createFileChecksum(file.getAbsolutePath()));  //$ to split on
	            } else if (file.isDirectory()) {
	            	scanLocalDirectory(writer, file.getAbsolutePath(), baseDirectory);
	            }
	        }   
	    }
	}
	
	public static String createFileChecksum(String filePath) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
        // file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
	}
}
