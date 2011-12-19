package jreprogen.codegen;

import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import jreprogen.model.Model;

public class CodeGenerator {
	
	private final Model model;
	
	private final List<CodeGeneratorPass> passes = new ArrayList<CodeGeneratorPass>();
	
	private ClassPool classPool = ClassPool.getDefault();
	
	public CodeGenerator(Model model) {
		this.model = model;
	}
	
	public void addPass(CodeGeneratorPass pass) {
		passes.add(pass);
	}
	
	public List<Message> generate() {
		List<Message> messages = new ArrayList<Message>();
		
		for(CodeGeneratorPass pass : passes) {
			try {
				pass.generate(this, messages);
			} catch(Exception ex) {
				messages.add(new Message(ex));
			}
		}
		
		return messages;
	}
	
	public Model getModel() {
		return model;
	}
	
	public ClassPool getClassPool() {
		return classPool;
	}

}
