import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ringeis
 *
 */
public class RSA implements AbstractRSA{
	
	private BigInteger d;
	private BigInteger e;
	private BigInteger n;
	private static Random rand = new Random();
	
	RSA(int numBits){
		BigInteger p = BigInteger.probablePrime(numBits, rand);
		BigInteger q = BigInteger.probablePrime(numBits, rand);
		this.n = p.multiply(q);
		e = BigInteger.probablePrime(numBits, rand);
		while(e.compareTo(n) > 0){
			e = BigInteger.probablePrime(numBits, rand);
		}
		d = e.modInverse(n);
	}
	
	RSA(String keyFile) throws FileNotFoundException, IOException{ //not sure
		File file = new File(keyFile);
		Scanner scan = new Scanner(file);
		String str = scan.nextLine();
		n = new BigInteger(str,16);
		str = scan.nextLine();
		e = new BigInteger(str,16);
		d = e;
	}
	
	public void printKeys() {
		System.out.print(n.toString()+"\n"+e.toString()+"\n"+d.toString());	
	}

	
	public void encrypt() throws IOException {
		byte[] textBlock = new byte[n.bitLength()/8];
		int length;
		
		while((length = System.in.read(textBlock)) != -1){
			if(length < textBlock.length){
				//handle when i discover the problem edit: was unable to discover a solution
			}
			BigInteger a = new BigInteger(1, textBlock);
			BigInteger b = a.modPow(e, n);
			System.out.println(b.toString(16));
		}	
	}

	
	public void decrypt() throws IOException {
		
		Scanner enter = new Scanner(System.in);
		
		while(enter.hasNextBigInteger(16)){
			BigInteger b = enter.nextBigInteger(16);
			BigInteger a = b.modPow(d, n);
			System.out.write(a.toByteArray());
		}	
	}
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main (String[] args) throws IOException{
		String keyFile;
		if(args[0].equals("-e")){
			keyFile = args[1];
			RSA rsa = new RSA(keyFile);
			rsa.encrypt();
		}else if(args[0].equals("-d")){
			keyFile = args[1];
			RSA rsa = new RSA(keyFile);
			rsa.decrypt();
		}else if(args[0].equals("-g")){
			RSA rsa = new RSA(1024);
			rsa.printKeys();
		}
	}
}
