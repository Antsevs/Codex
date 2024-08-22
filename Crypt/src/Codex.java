/* Anthony Sevarino
 * Spring 2022
 * Encryption Algorithm
 */

import java.util.Scanner;
import java.util.Random;


public class Codex {
	
	private Scanner scnr = new Scanner(System.in);
	private int cInput;
	private String phrase;
	
	public void greeting() //greet user
	{
		System.out.println("Welcome to Anthony's Codex\nWhat would you like to do?");
		System.out.println("1: Encrypt\n2: Decrypt\n3: Exit");
		
	}
	
	public void decision() //ask user what they want to do
	{
		Codex obj = new Codex();
		
		this.cInput = scnr.nextInt();
		if(cInput == 1)
		{
			System.out.println("Please enter your phrase to encrypt: ");
			obj.Encrypt();
		}
		else if(cInput == 2)
		{
			System.out.println("Please enter your phrase to decrypt: ");
			obj.Decrypt();
		}
		
		
	}
	
	public void Encrypt()
	{
		Random rand = new Random();
		long seed;
		int num = 0;
		long seedR = 0;
		long outputKey = 0;
		
		this.phrase = scnr.nextLine();
		
		System.out.println("Please input a key value: ");
		seed = scnr.nextInt();
		outputKey = seed;
		char[] arrEn = phrase.toCharArray(); // copy string to array
		for(char letter : arrEn)
		{
			seed += 1;
			seedR = seed;
			rand.setSeed(seedR); // should randomize the increment of each letter add
			num = rand.nextInt(10 - 0) + 0;
			letter += num;
			System.out.print(letter);
		}
		System.out.println("\nKey used: " + outputKey); // rand nextInt keeps giving 5
	}
	
	public void Decrypt()
	{
		int num;
		int seed;
		this.phrase = scnr.nextLine();
		Random rand2 = new Random();
		char[] arrEn = phrase.toCharArray();
		System.out.println("What is the Generator Key? ");
		seed = scnr.nextInt();
		for(char letter : arrEn)
		{
			seed += 1;
			long seedR = seed;
			rand2.setSeed(seedR); 
			num = rand2.nextInt(10 - 0) + 0;
			letter -= num;
			System.out.print(letter);
		}
	}
	
	public static void main(String [] args)
	{
		Codex obj2 = new Codex();
		obj2.greeting();
		obj2.decision();
		//maybe do a get and set for the decision input to loop this so the program doesnt end after encrpyting ot decrypting
	}
}


//eventually make a GUI for this software