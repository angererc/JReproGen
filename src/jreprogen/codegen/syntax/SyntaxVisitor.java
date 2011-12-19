package jreprogen.codegen.syntax;

public interface SyntaxVisitor {

	void visit(Grammar grammar);
	void visit(KeywordToken token);
	void visit(Production production);
	void visit(Alternative alternative);
	void visit(KeywordElement keyword);
	void visit(ComplexElement complex);
}
