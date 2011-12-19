package jreprogen.syntax;

public class LiteralToken extends Token {

	public static LiteralToken literal(String token) {
		return new LiteralToken(token);
	}
	
	private final String keyword;
	
	public LiteralToken(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	public String asIdentifier() {
		//TODO far from perfect but enough for simple keyword-style tokens!
		return this.keyword.toLowerCase().replace(' ', '_').replace('.', '_');
	}
	
	@Override
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public int hashCode() {
		return keyword.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LiteralToken) {
			LiteralToken other = (LiteralToken)obj;
			return other.keyword.equals(this.keyword);
		} else {
			return false;
		}
	}
	
}
