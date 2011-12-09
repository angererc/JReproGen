package jreprogen.model;

import jreprogen.model.builder.ContextBuilder;
import jreprogen.model.builder.ModelBuilder;
import jreprogen.model.builder.New;

public class ModelFromJavaClassParser {

	public Model parse(Class<?> clazz) {
		ModelBuilder builder = New.modelBuilder();
		return parse(builder, clazz);		
	}
	
	private Model parse(ModelBuilder mBuilder, Class<?> clazz) {
		
		ContextBuilder cBuilder = mBuilder.context(clazz.getCanonicalName());
		
		return null;
	}
}
