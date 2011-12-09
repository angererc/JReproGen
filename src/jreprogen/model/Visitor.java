package jreprogen.model;

public interface Visitor {

	void visit(Model m);
	void visit(Context c);
	void visit(Phrase p);
	void visit(Option o);
}
