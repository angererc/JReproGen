package jreprogen.codegen.syntax;

public class KeywordElement extends Element {

	private KeywordToken token;
	
	public void setToken(KeywordToken token) {
		this.token = token;
	}
	
	public KeywordToken getToken() {
		return this.token;
	}
	
	@Override
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
}
