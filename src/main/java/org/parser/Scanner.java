package org.parser;

import org.parser.exception.ScannerException;

public class Scanner {	

	private static final int CONST_NEGATIVE = -1;

	private static final int CONST_POSITIVE = 1;

	private Token token;

	private char []program;

	private char currentChar;

	private int posChar;

	private Table table;

	private StringBuilder stringBuilder;
	
	private int signal;
	
	private int countLine;

	public Scanner(String program) {
		program = program + "\n";
		this.program = program.toUpperCase().toCharArray();
		this.stringBuilder = new StringBuilder();
		this.nextChar();
		this.table = new Table();	
		countLine = 1;
	}

	private void nextChar() {
		if (posChar < program.length) {
			this.currentChar = program[posChar++];
		} else {
			this.currentChar = '\0';
		}
	}

	public void nextToken() throws ScannerException {
		setSignal(CONST_POSITIVE);
		while (this.currentChar != '\0') {
			switch (this.currentChar) {
			case ' ':
			case '\t':
				nextChar();
				break;
			case '\n':	
				countLine++;
				nextChar();				
				break;
			case ';':
				while(this.currentChar != '\n'){
					nextChar();					
				}
				break;
			case ',':
				initBuffer();
				putChar(this.currentChar);
				token = Token.COMMA;
				nextChar();
				return;
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':	
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':				
			case 'X':	
			case 'Z':
				scanLetters();
				return;
			case '-':
				putChar(this.currentChar);
				nextChar();
				if(isValidCharDec(this.currentChar)){
					setSignal(CONST_NEGATIVE);
					scanNumber();
				}else{
					throw new ScannerException("Caracter não esperado: "+ this.currentChar+ ", caracter não é um número negativo, erro na linha: " + this.countLine);
				}
				return;
			case '0':
			case '1':	
			case '2':
			case '3':	
			case '4':
			case '5':	
			case '6':
			case '7':
			case '8':
			case '9':
				scanNumber();
				return;
			}
		}

		if (this.currentChar == '\0') {
			initBuffer();
			putChar(this.currentChar);			
			this.token = Token.EOF;
			return;
		}
	}

	private void scanNumber() throws ScannerException {
		initBuffer();
		putChar(this.currentChar);
		nextChar();
		if(this.currentChar == 'X'){
			initBuffer();
			nextChar();
			while (notTerminal()) {
				if(isValidCharHexa(this.currentChar)){
					putChar(this.currentChar);
					this.nextChar();					
				}else{
					throw new ScannerException("Caracter não esperado: "+ this.currentChar+ ", hexadecimal fora do padrão, erro na linha: " + this.countLine);
				}
				this.token = Token.HEXA_0X;
		}
		}else if(this.currentChar == 'B'){
			initBuffer();
			nextChar();
			while (notTerminal()) {
				if(isValidCharBin(this.currentChar)){
					putChar(this.currentChar);
					this.nextChar();					
				}else{
					throw new ScannerException("Caracter não esperado: "+ this.currentChar+ ", binário fora do padrão, erro na linha: " + this.countLine);
				}
				this.token = Token.BIN;
			}
		}else {
			if(this.currentChar == '\n'){
				this.token = Token.DECIMAL;
			}else{
				while(notTerminal()){
					if(isValidCharDec(this.currentChar)){
						putChar(this.currentChar);
						this.nextChar();					
					}else{
						throw new ScannerException("Caracter não esperado: "+ this.currentChar+ ", decimal fora do padrão, erro na linha: " + this.countLine);
					}
					this.token = Token.DECIMAL;				
				}				
			}
		}
		
	}

	private boolean notTerminal() {
		return this.currentChar != ' ' && this.currentChar != ','
			&& this.currentChar != '\0' && this.currentChar != '\n' && this.currentChar != ';';
	}
	
	private boolean isValidCharHexa(char c){
		return "0123456789ABCDEF".indexOf(c) >= 0;
	}
	private boolean isValidCharBin(char c){
		return "01".indexOf(c) >= 0;
	}
	private boolean isValidCharDec(char c){
		return "0123456789".indexOf(c) >= 0;
	}
	
	private void putChar(char c) {
		this.stringBuilder.append(c);
	}

	private void scanLetters() throws ScannerException {
		initBuffer();
		while (notTerminal()) {
			putChar(this.currentChar);
			this.nextChar();
		}
		String word = getWord();
		if(word.endsWith(":")){
			String wordNotColon = word.substring(0, word.length() - 1);
			if(!table.isToken(wordNotColon)){
				initBuffer();
				this.stringBuilder.append(wordNotColon);
				this.token = Token.DECLARE_LABEL;	
			}else{
				throw new ScannerException("Label não pode ser uma palavra reservada: " + wordNotColon + ", erro na linha: " + this.countLine);
			}
			
		}else if (table.isToken(word)) {
			this.token = table.getToken(word);
		}else {
			this.token = Token.LABEL;
		}
	}

	private void initBuffer() {
		this.stringBuilder.setLength(0);
	}

	public int getNumberValue() throws ScannerException {
		if(this.token == Token.BIN){
			return getSignal() * Integer.parseInt(getWord(), 2);
		}
		if(this.token == Token.DECIMAL){
			return getSignal() * Integer.parseInt(getWord(), 10);
		}
		if(this.token == Token.HEXA_0X){
			return getSignal() * Integer.parseInt(getWord(), 16);
		}
		throw new ScannerException("Não existe um valor numérico para esse Token: " + this.token+ ", erro na linha: " + this.countLine);
	}

	// pegar do str
	private String getWord() {
		return this.stringBuilder.toString();
	}

	public String getLexema() {
		return this.getWord();
	}

	public Token token() {
		return this.token;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public int getCountLine() {
		return countLine;
	}

}
