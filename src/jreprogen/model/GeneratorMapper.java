package jreprogen.model;

public interface GeneratorMapper<T> {

	T mapImmediateBool();
	T mapImmediateInt();
	T mapImmediateString();
	T map(Option o);
}
