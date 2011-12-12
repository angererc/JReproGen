package jreprogen.codegen.parser;

import jreprogen.model.Context;
import jreprogen.model.GeneratorMapper;
import jreprogen.model.Option;

public class GeneratorToProductionMapper implements GeneratorMapper<String> {

	private final Context context;
	
	GeneratorToProductionMapper(Context c) {
		this.context = c;
	}
	
	@Override
	public String map(Option o) {
		return (context.getName() +  "." + o.getPhrase().getName()).replace('.', '_');
	}

	@Override
	public String mapImmediateBool() {
		return "TRUE | FALSE";
	}

	@Override
	public String mapImmediateInt() {
		return "INT";
	}

	@Override
	public String mapImmediateString() {
		return "IDENT";
	}

}
