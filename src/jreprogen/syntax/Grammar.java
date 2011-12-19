package jreprogen.syntax;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grammar {

	private String packageName;
	private Set<Token> ignoredTokens = new HashSet<Token>();
	private List<Production> productions = new ArrayList<Production>();
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Set<Token> collectTokens() {
		final Set<Token> tokens = new HashSet<Token>();
		this.visitProductions(new AbstractSyntaxVisitor() {
			@Override
			public void visit(LiteralElement keyword) {
				tokens.add(keyword.getToken());
			}
		});
		return tokens;
	}
	public Set<Token> getIgnoredTokens() {
		return ignoredTokens;
	}
	public void addIgnoredToken(Token ignoredToken) {
		this.ignoredTokens.add(ignoredToken);
	}
	public List<Production> getProductions() {
		return productions;
	}
	public void addProduction(Production production) {
		production.setGrammar(this);
		this.productions.add(production);
	}
	
	void removeProduction(Production production) {
		this.productions.remove(production);
		production.setGrammar(null);
	}
	
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
	
	public void visitTokens(SyntaxVisitor visitor) {
		for(Token tok : collectTokens())
			tok.visit(visitor);
	}
	
	public void visitProductions(SyntaxVisitor visitor) {
		for(Production prod : productions)
			prod.visit(visitor);
	}

}
