package jreprogen.codegen.parser;

import jreprogen.codegen.GeneratorException;
import jreprogen.model.Context;
import jreprogen.model.ValueMapper;
import jreprogen.model.Value.ContextRef;
import jreprogen.model.Value.ObjectVal;

public class ValueToProductionNameMapper implements ValueMapper<String> {

	private final Context context;
	
	ValueToProductionNameMapper(Context c) {
		this.context = c;
	}
	
	@Override
	public String map(ContextRef val) {
		return (context.getName() + "." + val.getName()).replace('.', '_');
	}

	@Override
	public String map(ObjectVal val) {
		return (context.getName() + "." + val.getType().getCanonicalName()).replace('.', '_');
	}

	@Override
	public String mapBoolValue() {
		return (context.getName() + ".BoolValue").replace('.', '_');
	}

	@Override
	public String mapIntValue() {
		return (context.getName() + ".IntValue").replace('.', '_');
	}

	@Override
	public String mapStringValue() {
		return (context.getName() + ".StringValue").replace('.', '_');
	}

	@Override
	public String mapVoidValue() {
		throw new GeneratorException("Should not happen");
	}

}
