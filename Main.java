// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

import dataStructures.*;
import exceptionPackage.NoFrequencyOnTextException;
import exceptionPackage.TextAlreadyExistException;
import exceptionPackage.TextNotFoundException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import spelling.*;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

public class Main {

	private static final String AD_SUCCESS = "Atualizacao do dicionario com sucesso.\n";
	private static final String AD_ERRO = "Lista de palavras nao contem palavras novas.\n";


	private static final String PC_ERRO = "Erro ortografico.\n";
	private static final String PC_SUCCESS = "Palavra correta.\n";


	private static final String AT_ERRO = "Identificador de texto existente.\n";
	private static final String AT_SUCCESS = "Texto adicionado com sucesso.\n";


	private static final String RT_ERRO = "Texto inexistente.\n";
	private static final String RT_SUCCESS = "Remocao de texto com sucesso.\n";


	private static final String LT_ERRO = "Texto inexistente.\n";


	private static final String LR_ERRO = "Texto inexistente.\n";
	private static final String LR_ERRO1 = "Intervalo de numero de linhas mal definido.\n";
	private static final String LR_ERRO2 = "Troco inexistente.\n";


	private static final String LE_ERRO = "Texto inexistente.\n";
	private static final String LE_ERRO2 = "Inexistencia de erros ortograficos no texto.\n";


	private static final String FP_ERRO = "Texto inexistente.\n";


	private static final String LF_ERRO = "Texto inexistente.\n";
	private static final String LF_ERRO1 = "Inexistencia de palavras com a frequencia pedida.\n";


	private static Spelling s;
	
	
	
	
	public enum Command {
		AD, PC, AT, RT, LT, LR, LE, FP, LF, EXIT, UNKNOWN
	}
	
	private static Command getCommand(Scanner in) { 
		 try { 
		 String comm = in.next().toUpperCase(); 
		 return Command.valueOf(comm); 
		 } catch (IllegalArgumentException e) { 
		 return Command.UNKNOWN; 
		 } 
	}
	
	private static void addWord(Scanner in, Spelling s)  {
		int numWords = in.nextInt();
		in.nextLine();
		boolean addedWord = false;
		for(int i = 0; i < numWords; i++) {
			String word = in.next();
			in.nextLine();
			if(!s.hasWord(word)) {
				s.addWord(word);
				addedWord = true;
			}
		}
		if(addedWord) {
			System.out.println(AD_SUCCESS);
		} else System.out.println(AD_ERRO);
	}
	
	private static void searchWord(Scanner in, Spelling s) {
		String word = in.next();
		
		if(s.hasWord(word)) {
			System.out.println(PC_SUCCESS);
		} else System.out.println(PC_ERRO);
	}
	
	private static void addText(Scanner in, Spelling s) {
		String textId = in.next();
		int numLines = in.nextInt();
		in.nextLine();
		
		try {
			
			String[] text = new String[numLines];
			for(int i = 0; i < numLines; i++) {
				text[i] = in.nextLine();
			}
			s.createText(textId, text);
			System.out.println(AT_SUCCESS);
		}
		catch(TextAlreadyExistException taee){
			System.out.println(AT_ERRO);
		}
	}
	
	private static void remText(Scanner in, Spelling s) {
		String textId = in.next();
		in.nextLine();
		try{
			s.remText(textId);
			System.out.println(RT_SUCCESS);
		}
		catch (TextNotFoundException tnfe){
			System.out.println(RT_ERRO);
		}
	
		
	}
	
	private static void listText(Scanner in, Spelling s) {
		String textId = in.next();
		in.nextLine();
		
		if(!s.hasText(textId)) {
			System.out.println(LT_ERRO);
		} else {
			Iterator<String> a = (Iterator<String>) s.listText(textId);
			while(a.hasNext()) {
				System.out.println(a.next());
			}
			System.out.println();
		}
		
	}
	
	private static void listTextSection(Scanner in, Spelling s) {
		String textId = in.next();
		int minNumLine = in.nextInt();
		int maxNumLine = in.nextInt();
		in.nextLine();
		
		
		if(!s.hasText(textId)) {
			System.out.println(LR_ERRO);
		} else if(maxNumLine < minNumLine && maxNumLine != 0) {
			System.out.println(LR_ERRO1);
		} else if(maxNumLine > s.getTextLenght(textId)) {
			System.out.println(LR_ERRO2);
		} else {

			Iterator<String> a = (Iterator<String>) s.listTextSection(textId, minNumLine, maxNumLine);
			while(a.hasNext()) {
				System.out.println(a.next());
			}
			System.out.println();
		}
	}
	
	private static void listSpellingErrors(Scanner in, Spelling s) {
		String textId = in.next();
		in.nextLine();
		
		if(!s.hasText(textId)) {
			System.out.println(LE_ERRO);
		} else if(!s.hasErrors(textId)) {
			System.out.println(LE_ERRO2);
		} else {
			Iterator<Entry<String, Word>> a = s.listSpelingErrors(textId);
			while(a.hasNext()) {
				Entry<String, Word> b = a.next();
				Word c = b.getValue();
				System.out.println(c.getWord());
				int[] d = c.getLines(); // TODO este getLines tem que ser um iterador 
				for(int i = 0;i < c.getCounter();i++) {
					System.out.println(d[i]);
				}
			}
			System.out.println();
		}
	}
	
	private static void wordFrequencyOnText(Scanner in, Spelling s) {
		String textId = in.next();
		String word = in.next();
		in.nextLine();
		try{
		int frequency = s.wordFrequencyOnText(textId, word);
		System.out.println(word + " ocorre " + frequency + " vezes no texto referido.\n");
		}
		catch (TextNotFoundException tfne) {
			System.out.println(FP_ERRO);
		}
		
	}
	
	

	private static void wordsWithTypeAndFrequency(Scanner in, Spelling s) {
		String textId = in.next();
		String tipeWords = in.next();
		int frequency = in.nextInt();
		in.nextLine();
		
		try {
		Iterator<Entry<String, Word>> a = s.wordsWithTypeAndFrequency(textId, tipeWords, frequency);
		while(a.hasNext()) {
			Word b = a.next().getValue();
			System.out.println(b.getWord()); 
			
		}
		System.out.println();
		} catch(TextNotFoundException tnfe) {
			System.out.println(LF_ERRO);
			
		} catch(NoFrequencyOnTextException nfote) {
			System.out.println(LF_ERRO1);
		}
		
		
	}
	
	public static void store( ){ 
		try{
		ObjectOutputStream file = new ObjectOutputStream( new FileOutputStream("file") );
		file.writeObject(s);
		file.flush();
		file.close();
		}
	catch ( IOException e ) {
		
		}
	}
	
	public static void load(){ 
		try{
			ObjectInputStream file = new ObjectInputStream( new FileInputStream("file") );
			// Compiler gives a warning.
			s = (Spelling) file.readObject();
			file.close();
		}
		catch ( IOException e ) {
			s = new SpellingClass();
		}
		catch ( ClassNotFoundException e )
		{	
			
		} 
	}
	
	
	public static void main(String[] args) {
		load();
		commands();
	}
	
	private static void commands() {
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			Command cmd = getCommand(in);
			switch(cmd) {
			case AD : addWord(in, s);
				break;
			case PC : searchWord(in, s);
				break;
			case AT : addText(in, s);
				break;
			case RT : remText(in, s);
				break;
			case LT : listText(in, s);
				break;
			case LR : listTextSection(in, s);
				break;
			case LE : listSpellingErrors(in, s);
				break;
			case FP : wordFrequencyOnText(in, s);
				break;
			case LF : wordsWithTypeAndFrequency(in, s);
				break;
			default:
				break;
			}
		}
		store();
	}

}
