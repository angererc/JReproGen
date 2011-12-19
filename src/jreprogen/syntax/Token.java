package jreprogen.syntax;

public abstract class Token {

	public abstract void visit(SyntaxVisitor visitor);
}
