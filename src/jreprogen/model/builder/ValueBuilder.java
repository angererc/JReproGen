package jreprogen.model.builder;

import jreprogen.model.Value;

public class ValueBuilder {

	private final Class<?>[] builderTypes;
	
	public ValueBuilder(Class<?>[] builderTypes) {
		this.builderTypes = builderTypes;
	}
	
	public Value fromJavaType(Class<?> type) {
		if(type.equals(Void.class)) {
			return Value.VoidVal;
		} else if(type.equals(String.class)) {
			return Value.StringVal;
		} else if(type.equals(boolean.class) || type.equals(Boolean.class)) {
			return Value.BoolVal;
		} else if(type.equals(int.class) || type.equals(Integer.class)) {
			return Value.IntVal;
		//TODO add more here
		} else if(isBuilderType(type)) {
			return new Value.ContextRef(type.getCanonicalName());
		} else {
			return new Value.ObjectVal(type);
		}
	}

	private boolean isBuilderType(Class<?> type) {
		for(Class<?> bt : builderTypes) {
			if(bt.equals(type)) return true;
		}
		return false;
	}
}
