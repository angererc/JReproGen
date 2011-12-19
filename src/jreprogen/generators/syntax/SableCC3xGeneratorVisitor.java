package jreprogen.generators.syntax;

import java.io.PrintStream;

import jreprogen.syntax.Alternative;
import jreprogen.syntax.ComplexElement;
import jreprogen.syntax.Element;
import jreprogen.syntax.Grammar;
import jreprogen.syntax.LiteralElement;
import jreprogen.syntax.LiteralToken;
import jreprogen.syntax.Production;
import jreprogen.syntax.SyntaxVisitor;

public class SableCC3xGeneratorVisitor implements SyntaxVisitor, Element.ModifierMapper<String> {

	public static void generateGrammarFile(Grammar grammar, PrintStream stream) {
		SableCC3xGeneratorVisitor generator = new SableCC3xGeneratorVisitor(stream);
		grammar.visit(generator);
	}
	
	private PrintStream stream;
	
	private SableCC3xGeneratorVisitor(PrintStream stream) {
		this.stream = stream;
	}
	
	@Override
	public void visit(Grammar grammar) {
		stream.printf("Package %s;\n", grammar.getPackageName());
		stream.println();
		
		stream.println("Tokens");
		stream.println();
		grammar.visitTokens(this);
		stream.println();
		
		stream.println("Productions");
		stream.println();
		grammar.visitProductions(this);
	}

	@Override
	public void visit(LiteralToken token) {
		stream.printf("\t%s = '%s';\n", token.asIdentifier(), token.getKeyword());
	}
	
	@Override
	public void visit(Production production) {
		stream.printf("\t%s =\n", production.getName());
		
		boolean first = true;
		for(Alternative alt : production.getAlternatives()) {
			if(! first)
				stream.println("|");
			first = false;
			alt.visit(this);
			
		}
		stream.println(";");
		stream.println();
	}

	@Override
	public void visit(Alternative alternative) {
		stream.printf("\t\t{%s} ", alternative.getName());
		alternative.visitElements(this);
	}

	@Override
	public void visit(LiteralElement keyword) {
		stream.print("T.");
		stream.print(keyword.getToken().asIdentifier());
		stream.print(keyword.getModifier().map(this));
		stream.print(" ");
	}

	@Override
	public void visit(ComplexElement complex) {
		stream.print("P.");
		stream.print(complex.getProduction().getName());
		stream.print(complex.getModifier().map(this));
		stream.print(" ");
	}

	@Override
	public String mapAtLeastOne() {
		return "+";
	}

	@Override
	public String mapMany() {
		return "*";
	}

	@Override
	public String mapOne() {
		return "";
	}

	@Override
	public String mapOptional() {
		return "?";
	}

}
