package org.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.parser.exception.ParserException;
import org.parser.exception.ScannerException;
import org.simalator.MemoryContent;
import org.simalator.command.CommandAdd86;
import org.simalator.command.CommandAdd87;
import org.simalator.command.CommandAni;
import org.simalator.command.CommandCall;
import org.simalator.command.CommandCpi;
import org.simalator.command.CommandJnz;
import org.simalator.command.CommandJp;
import org.simalator.command.CommandJz;
import org.simalator.command.CommandMov77;
import org.simalator.command.CommandMov7E;
import org.simalator.command.CommandMov7F;
import org.simalator.command.CommandMvi36;
import org.simalator.command.CommandMvi3E;
import org.simalator.command.CommandNop;
import org.simalator.command.CommandPopE1;
import org.simalator.command.CommandPushE5;
import org.simalator.command.CommandRet;
import org.util.Util;

public class Parser {

	
	public static void main(String args[]) throws ParserException, ScannerException{
	}
	private Map<String,DeclareLabel> mapDeclareLabel = new HashMap<String,DeclareLabel>();
	private Scanner scanner;
	private LinkedList<MemoryContent> linkedList;
	private Table table;

	public LinkedList<MemoryContent> parse(String program) throws ScannerException, ParserException {
		linkedList = new LinkedList<MemoryContent>();
		table = new Table();
		scanner = new Scanner(program);
		scanner.nextToken();
		parserProgram();
		accept(Token.EOF);
		doConfigLabel();
		return this.linkedList;
	}


	private void doConfigLabel() throws ParserException {
		for(int i = 0; i < this.linkedList.size(); i++){
			MemoryContent content = this.linkedList.get(i);
			//WA significa que é um comando que usa label
			if(content.getLabel() != null){
				DeclareLabel dl = getDeclareLabel(content.getLabel());
				String posHexa = Util.toHex16(dl.getPosMemory());
				byte posMemMenos = (byte) Util.stringToHex(posHexa.substring(2, 4));
				byte posMemMais = (byte) Util.stringToHex(posHexa.substring(0, 2));
				this.linkedList.get(i + 1).setValue(posMemMenos);
				this.linkedList.get(i + 2).setValue(posMemMais);
			}			

		}		
	}


	private void parserProgram() throws ScannerException, ParserException {		
		while(scanner.token() != Token.EOF){
			switch (scanner.token()) {
			case ADD:				
				parseAdd();
				break;
			case NOP:				
				parseNop();
				break;
			case MOV:
				parseMov();
				break;
			case MVI:
				parseMvi();
				break;
			case DECLARE_LABEL:
				parseDeclareLabel();
				break;
			case CALL:
				parseCall();
				break;
			case PUSH:
				parsePush();
				break;
			case RET:
				parseRet();
				break;
			case JP:
				parseJp();
				break;
			case CPI:
				parseCpi();
				break;
			case ANI:
				parseAni();
				break;
			case JZ:
				parseJz();
				break;
			case JNZ:
				parseJnz();
				break;
			case POP:
				parsePop();
				break;				
			default:
				throw new ParserException("Token fora de ordem: " + scanner.token() + ", erro na linha: " + scanner.getCountLine());
			}
		}
	}
	
	private void parseRet() throws ScannerException, ParserException {
		scanner.nextToken();
		if(table.isInstruction(scanner.token().name) || scanner.token() == Token.DECLARE_LABEL){
			this.linkedList.add(new MemoryContent(CommandRet.OP_CODE));
		}else{
			throw new ParserException("Após um RET deve-se ter uma outra instrução, erro na linha: " + scanner.getCountLine());
		}
	}
	private void parseJp() throws ScannerException, ParserException {
		scanner.nextToken();
		if(scanner.token() == Token.LABEL){
			MemoryContent mcCall = new MemoryContent(CommandJp.OP_CODE);
			mcCall.setLabel(scanner.getLexema());
			this.linkedList.add(mcCall);			
			this.linkedList.add(new MemoryContent((byte)0));
			this.linkedList.add(new MemoryContent((byte)0));
			
			
		}else{
			throw new ParserException("Após um JP deve-se ter um Label, erro na linha: " + scanner.getCountLine());
		}
		scanner.nextToken();
	}
	
	private void parseJz() throws ScannerException, ParserException {
		scanner.nextToken();
		if(scanner.token() == Token.LABEL){
			MemoryContent mcCall = new MemoryContent(CommandJz.OP_CODE);
			mcCall.setLabel(scanner.getLexema());
			this.linkedList.add(mcCall);			
			this.linkedList.add(new MemoryContent((byte)0));
			this.linkedList.add(new MemoryContent((byte)0));
		}else{
			throw new ParserException("Após um JZ deve-se ter um Label, erro na linha: " + scanner.getCountLine());
		}
		scanner.nextToken();
	}
	private void parseJnz() throws ScannerException, ParserException {
		scanner.nextToken();
		if(scanner.token() == Token.LABEL){
			MemoryContent mcCall = new MemoryContent(CommandJnz.OP_CODE);
			mcCall.setLabel(scanner.getLexema());
			this.linkedList.add(mcCall);			
			this.linkedList.add(new MemoryContent((byte)0));
			this.linkedList.add(new MemoryContent((byte)0));
		}else{
			throw new ParserException("Após um JNZ deve-se ter um Label, erro na linha: " + scanner.getCountLine());
		}
		scanner.nextToken();
	}
	private void parseCpi() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		if(t == Token.BIN || t == Token.DECIMAL || t == Token.HEXA_0X){
			this.linkedList.add(new MemoryContent(CommandCpi.OP_CODE));			
			this.linkedList.add(new MemoryContent( (byte)scanner.getNumberValue() ));						
		}else{
			throw new ParserException("Após um CPI deve-se ter um valor de 8 bits, erro na linha: " + scanner.getCountLine());
		}
		scanner.nextToken();
	}
	private void parseAni() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		if(t == Token.BIN || t == Token.DECIMAL || t == Token.HEXA_0X){
			this.linkedList.add(new MemoryContent(CommandAni.OP_CODE));			
			this.linkedList.add(new MemoryContent( (byte)scanner.getNumberValue() ));						
		}else{
			throw new ParserException("Após um ANI deve-se ter um valor de 8 bits, erro na linha: " + scanner.getCountLine());
		}
		scanner.nextToken();
	}


	private void parsePush() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		if(t == Token.H){
			this.linkedList.add(new MemoryContent(CommandPushE5.OP_CODE));
			scanner.nextToken();
		}else{
			throw new ParserException("Após um PUSH deve-se ter um H, erro na linha: " + scanner.getCountLine());
		}
		
	}

	private void parsePop() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		if(t == Token.H){
			this.linkedList.add(new MemoryContent(CommandPopE1.OP_CODE));
			scanner.nextToken();
		}else{
			throw new ParserException("Após um POP deve-se ter um H, erro na linha: " + scanner.getCountLine());
		}
	}
	
	private void parseCall()  throws ScannerException, ParserException {
		scanner.nextToken();
		if(scanner.token() == Token.LABEL){
			/*
			DeclareLabel dl = getDeclareLabel(scanner.getLexema());
			String posHexa = Util.toHex16(dl.getPosMemory());
			
			byte posMemMenos = (byte) Util.stringToHex(posHexa.substring(2, 4));
			byte posMemMais = (byte) Util.stringToHex(posHexa.substring(0, 2));
			
			this.linkedList.add(new MemoryContent(CommandCall.OP_CODE));			
			this.linkedList.add(new MemoryContent(posMemMenos));
			this.linkedList.add(new MemoryContent(posMemMais));
			*/
			
			MemoryContent mcCall = new MemoryContent(CommandCall.OP_CODE);
			mcCall.setLabel(scanner.getLexema());
			this.linkedList.add(mcCall);			
			this.linkedList.add(new MemoryContent((byte)0));
			this.linkedList.add(new MemoryContent((byte)0));
			
			
		}else{
			throw new ParserException("Após um Call deve-se ter um Label, erro na linha: " + scanner.getCountLine());
		}
		scanner.nextToken();
		
	}
	private void parseDeclareLabel()  throws ScannerException, ParserException {
		//TODO TRATAR PRA VER SE O PRÓXIMO É UMA INSTRUÇÃO VALIDA
		DeclareLabel dl = new DeclareLabel(scanner.getLexema(), (short)linkedList.size());
		putDeclareLabel(dl);
		scanner.nextToken();

	}

	private DeclareLabel getDeclareLabel(String key) throws ParserException{
		if(mapDeclareLabel.containsKey(key)){
			return mapDeclareLabel.get(key);
		}else{
			throw new ParserException("O Label: " + key + " não foi declarado.");
		}
		
	}
	private void putDeclareLabel(DeclareLabel dl) throws ParserException {
		if(!mapDeclareLabel.containsKey(dl.getKey())){
			mapDeclareLabel.put(dl.getKey(), dl);
		}else{
			throw new ParserException("O Label: " + dl.getKey() + " já existe, erro na linha: " + scanner.getCountLine());	
		}
	}


	private void parseMvi() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		
		if(table.isRegister(t.name) && t != Token.FLAGFF){
			if(t == Token.HL){
				scanner.nextToken();
				t = scanner.token();
				if(t == Token.BIN || t == Token.DECIMAL || t == Token.HEXA_0X){
					this.linkedList.add(new MemoryContent(CommandMvi36.OP_CODE));
					this.linkedList.add(new MemoryContent((byte)scanner.getNumberValue()));
					scanner.nextToken();
				}else{
					throw new ParserException("Deveria ser um valor de 8 bits, erro na linha: " + scanner.getCountLine());
				}
			}else{
				if(table.isRegister(t.name)&& t != Token.FLAGFF ){
					Token tAnt = t;
					scanner.nextToken();
					t = scanner.token();
					if(t == Token.BIN || t == Token.DECIMAL || t == Token.HEXA_0X){
						this.linkedList.add(new MemoryContent(CommandMvi3E.OP_CODE));
						this.linkedList.add(new MemoryContent((byte)Util.stringToHexRegister(tAnt.name)));
						this.linkedList.add(new MemoryContent((byte)scanner.getNumberValue()));
						scanner.nextToken();
					}else{
						throw new ParserException("Deveria ser um valor de 8 bits, erro na linha: " + scanner.getCountLine());
					}					
				}else{
					throw new ParserException("Deveria ser um registrador ou M, erro na linha: " + scanner.getCountLine());
				}
			}
		}else{
			throw new ParserException("Deveria ser um registrador ou M, erro na linha: " + scanner.getCountLine());
		}
	}


	private void parseMov()  throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		
		if(table.isRegister(t.name)){
			//mov 77
			if(t == Token.HL){
				scanner.nextToken();
				t = scanner.token();
				if(table.isRegister(t.name)&& t != Token.FLAGFF){
					this.linkedList.add(new MemoryContent(CommandMov77.OP_CODE));
					this.linkedList.add(new MemoryContent( (byte)Util.stringToHexRegister(t.name) ));
					scanner.nextToken();
				}else{
					throw new ParserException("Deveria ser um registrador válido, erro na linha: " + scanner.getCountLine());
				}
			}else{				
				if(table.isRegister(t.name)&& t != Token.FLAGFF ){
					Token tokenAnt = t;
					scanner.nextToken();
					t = scanner.token();
					//mov 7F
					if(table.isRegister(t.name) && t != Token.FLAGFF && t != Token.HL){
						this.linkedList.add(new MemoryContent(CommandMov7F.OP_CODE));
						this.linkedList.add(new MemoryContent( (byte)Util.stringToHexRegister(tokenAnt.name) ));
						this.linkedList.add(new MemoryContent( (byte)Util.stringToHexRegister(t.name) ));
						scanner.nextToken();						
					}
					//mov 7E
					else{
						if(t == Token.HL){
							this.linkedList.add(new MemoryContent(CommandMov7E.OP_CODE));
							this.linkedList.add(new MemoryContent( (byte)Util.stringToHexRegister(tokenAnt.name) ));	
							scanner.nextToken();
						}else{
							throw new ParserException("Deveria ser um registrador ou M, erro na linha: " + scanner.getCountLine());
						}
					}
					
				}else{
					throw new ParserException("Deveria ser um registrador válido, erro na linha: " + scanner.getCountLine());
				}
			}
		}else{
			throw new ParserException("Deveria ser um registrador ou M, erro na linha: " + scanner.getCountLine());
		}		
	}
	private void parseNop() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();

		if(table.isInstruction(t.name) || t == Token.EOF || t == Token.DECLARE_LABEL){
			this.linkedList.add(new MemoryContent(CommandNop.OP_CODE));
		}else{
			throw new ParserException("Após um NOP deve haver outra instrução ou Label, erro na linha: " + scanner.getCountLine());
		}
	}


	private void parseAdd() throws ScannerException, ParserException {
		scanner.nextToken();
		Token t = scanner.token();
		
		if(table.isRegister(t.name)){
			if(t == Token.HL){
				this.linkedList.add(new MemoryContent(CommandAdd86.OP_CODE));
				scanner.nextToken();
			}else{
				if(t != Token.FLAGFF && t != Token.H && t != Token.L ){
					this.linkedList.add(new MemoryContent(CommandAdd87.OP_CODE));					
					this.linkedList.add(new MemoryContent( (byte)Util.stringToHexRegister(t.name) ));					
					scanner.nextToken();
				}else{
					throw new ParserException("O registrador deveria ser diferente de: " + t.name + ", erro na linha: " + scanner.getCountLine());
				}
			}
		}else{
			throw new ParserException("Deveria ser um registrador ou M, erro na linha: " + scanner.getCountLine());
		}
	}
	
	
	private void accept(Token token) throws ScannerException {
		if (scanner.token() == token){
			scanner.nextToken();
		} else{
			throw new RuntimeException("Erro de sintaxe");
		}
	}


	public Map<String, DeclareLabel> getMapDeclareLabel() {
		return mapDeclareLabel;
	}
	
}