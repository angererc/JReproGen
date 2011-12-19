package jreprogen.syntax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jreprogen.Message;
import static jreprogen.Message.*;

public class VerificationVisitor implements SyntaxVisitor {

	public static List<Message> verify(Grammar grammar) {
		VerificationVisitor verifier = new VerificationVisitor();
		grammar.visit(verifier);
		return verifier.getMessages();
	}
	
	private List<Message> messages = new ArrayList<Message>();
	
	public List<Message> getMessages() {
		return messages;
	}
	
	@Override
	public void visit(Grammar grammar) {
		if(grammar.getPackageName() == null) {
			messages.add(message("Package name must not be null!"));
		}
		
		Set<String> prods = new HashSet<String>();
		for(Production p : grammar.getProductions()) {
			if(prods.contains(p.getName()))
				messages.add(message("Production with name " + p.getName() + " was defined multiple times!"));
			prods.add(p.getName());
		}
		
		grammar.visitTokens(this);
		grammar.visitProductions(this);
	}
	
	@Override
	public void visit(LiteralToken token) {
		
	}

	@Override
	public void visit(Production production) {
		if(production.getName() == null)
			messages.add(message("Production name must not be null"));
		
		production.visitAlternatives(this);		
	}

	@Override
	public void visit(Alternative alternative) {
		if(alternative.getName() == null)
			messages.add(message("Alternative name must not be null"));
		
		alternative.visitElements(this);
	}

	@Override
	public void visit(LiteralElement keyword) {
		
	}

	@Override
	public void visit(ComplexElement complex) {
		
	}

}
