package jreprogen.codegen.syntax;

public class KeywordToken extends Token {

	public static KeywordToken keyword(String token) {
		return new KeywordToken(token);
	}
	
	private final String keyword;
	
	public KeywordToken(String keyword) {
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
		if(obj instanceof KeywordToken) {
			KeywordToken other = (KeywordToken)obj;
			return other.keyword.equals(this.keyword);
		} else {
			return false;
		}
	}
	
}
