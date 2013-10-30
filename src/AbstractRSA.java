/**
 * 
 */
import java.io.*;
/**
 * @author Ringeis
 *
 */
interface AbstractRSA {

	//RSA(int numBits);
	//RSA(String keyFile) throws FileNotFoundException, IOException;
	
	public void printKeys();
	
	public void encrypt() throws IOException;
	public void decrypt() throws IOException;
	
	//public static void main (String[] arguments) throws IOException;
}
