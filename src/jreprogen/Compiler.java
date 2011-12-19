package jreprogen;

import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import jreprogen.model.Model;

public class Compiler {
	
	private final Model model;
	
	private final List<CompilerPass> passes = new ArrayList<CompilerPass>();
	
	private ClassPool classPool = ClassPool.getDefault();
	
	public Compiler(Model model) {
		this.model = model;
	}
	
	public void addPass(CompilerPass pass) {
		passes.add(pass);
	}
	
	public List<Message> generate() {
		List<Message> messages = new ArrayList<Message>();
		
		for(CompilerPass pass : passes) {
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
