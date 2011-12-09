package jreprogen.frontend;

import java.lang.reflect.Method;

import jreprogen.model.Model;
import jreprogen.model.builder.ContextBuilder;
import jreprogen.model.builder.ModelBuilder;
import jreprogen.model.builder.OptionBuilder;
import jreprogen.model.builder.PhraseBuilder;
import jreprogen.model.builder.ValueBuilder;

public class ModelFromJavaClassParser {

	/**
	 * The list of builders defines the build contexts; other external class types ocurring in the
	 * builder interfaces are treated as blobs
	 * @param builders
	 * @return
	 */
	private ValueBuilder valueBuilder;
	
	public Model parse(Class<?>...builders) {
		valueBuilder = new ValueBuilder(builders);
		
		ModelBuilder model = new ModelBuilder();
		for(Class<?> clazz : builders) {
			parseClass(model, clazz);
		}
		return model.build();
	}
	
	private void parseClass(ModelBuilder model, Class<?> jClazz) {
		
		ContextBuilder context = model.context(jClazz.getCanonicalName());
		
		for(Method jMethod : jClazz.getMethods()) {
			parseMethod(context, jMethod);
		}
		
	}
	
	private void parseMethod(ContextBuilder clazz, Method jMethod) {
		
		PhraseBuilder method = clazz.phrase(jMethod.getName());
		
		//depending on the return type of the method, we 
		OptionBuilder variant = method.option(valueBuilder.fromJavaType(jMethod.getReturnType()));
		
		for(Class<?> jParam : jMethod.getParameterTypes()) {
			variant.param(valueBuilder.fromJavaType(jParam));
		}
		
	}

}