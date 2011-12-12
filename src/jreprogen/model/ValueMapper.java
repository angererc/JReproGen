package jreprogen.model;

public interface ValueMapper<T> {

	T mapVoidValue();
	T mapIntValue();
	T mapBoolValue();
	T mapStringValue();
	T map(Value.ContextRef val);
	T map(Value.ObjectVal val);
}
