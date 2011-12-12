package jreprogen.codegen.parser;

import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import jreprogen.codegen.CodeGenerator;
import jreprogen.codegen.CodeGeneratorPass;
import jreprogen.codegen.GeneratorException;
import jreprogen.model.Context;
import jreprogen.model.Generator;
import jreprogen.model.Message;
import jreprogen.model.Model;
import jreprogen.model.Value;

public class GenerateParserPass implements CodeGeneratorPass {

	/**
	 * for each value used in a context (= used as a parameter in an option)
	 * create a production of the form
	 * valType ::= generator1 | generator2 | generator3
	 * @param pool
	 * @param model
	 */
	protected void generateValueProductions(ClassPool pool, Context context) {
		
		StringBuilder builder = new StringBuilder();
		
		ValueToProductionNameMapper valueMapper = new ValueToProductionNameMapper(context);
		GeneratorToProductionMapper generatorMapper = new GeneratorToProductionMapper(context);
			
		for(Value usedValue : context.getUsedValues()) {
			//
			builder.append(usedValue.map(valueMapper));
			builder.append(" ::= ");
			boolean first = true;
			for(Generator gen : context.getGeneratorsForValue(usedValue)) {
				if(! first) builder.append(", ");
				builder.append(gen.map(generatorMapper));
			}
			builder.append("\n");
		}
		
		System.out.println(builder);
	}
	
	/**
	 * for every phrase option R foo(P1, P2, P3) create a production
	 * foo ::= P1 P2 P3
	 * @param pool
	 * @param model
	 */
	protected void generateOptionProductions(ClassPool pool, Context context) {
		
	}
	
	/**
	 * for every context C containing phrase options foo() and bar(), create a production
	 * C ::= (foo, bar)*
	 * @param pool
	 * @param model
	 */
	protected void generateContextProductions(ClassPool pool, Context context) {
		
	}
	
	@Override
	public void generate(CodeGenerator codeGenerator, List<Message> messages) throws Exception {
		
		ClassPool pool = codeGenerator.getClassPool();
		Model model = codeGenerator.getModel();
		
		if(! model.hasBeenValidated()) {
			throw new GeneratorException("You have to validate the model first!");
		}
		
		for(Context context : model.getContexts()) {
			generateValueProductions(pool, context);
			generateOptionProductions(pool, context);
			generateContextProductions(pool, context);
		}
		
		CtClass parserInterface = pool.get(IParser.class.getCanonicalName());
		
		CtClass clazz = pool.makeClass("MyTestClass");
		clazz.addInterface(parserInterface);
		
		CtMethod parseMethod = CtNewMethod.make("public void parse() { System.out.println(\"Hello World\"); }", clazz);
		clazz.addMethod(parseMethod);
		
		Class c = clazz.toClass();
		IParser p = (IParser)c.newInstance();
		p.parse();
	}

	
}
