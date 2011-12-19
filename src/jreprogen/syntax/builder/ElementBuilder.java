package jreprogen.syntax.builder;

import jreprogen.syntax.Grammar;

public interface ElementBuilder {

	ProductionBuilder production(String name);
	AlternativeBuilder alternative(String name);
	ElementBuilder literal(String literal);
	ElementBuilder ref(String name);
	ElementBuilder one();
	ElementBuilder many();
	ElementBuilder optional();
	ElementBuilder oneOrMore();
	Grammar build();
}
