package jreprogen.syntax;

public class LiteralElement extends Element {

	private LiteralToken token;
	
	public void setToken(LiteralToken token) {
		this.token = token;
	}
	
	public LiteralToken getToken() {
		return this.token;
	}
	
	@Override
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
}
