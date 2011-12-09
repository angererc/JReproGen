package jreprogen.ast;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import jreprogen.model.AbstractVisitor;
import jreprogen.model.Model;
import jreprogen.model.Option;
import jreprogen.model.Value;

public class ASTGenerator {

	private PrintStream out = System.out;
	
	public void generateAST(Model model) {
		
		//for each value type that is used as a parameter or return value, generate a node interface
		generateValueInterfaces(model);
		
		//for each option, generate a node
		generateOptionNodes(model);
	}
	
	private void generateValueInterfaces(Model model) {
		final Set<Value> result = new HashSet<Value>();
		
		model.traverse(new AbstractVisitor() { 
			@Override public void visit(Option o) {
				result.add(o.getGeneratedValue());
				for(Value v : o.getValues()) {
					result.add(v);
				}
			} 
		});
		
		for(Value v : result) generateValueInterface(v);
		
	}
	private void generateValueInterface(Value val) {
		out.println("interface " + val.asNodeName() + " {");
		out.println("\t" + val.asJavaType() +  " getResult();");
		out.println("}");
	}
	
	private void generateOptionNodes(Model model) {
		model.traverse(new AbstractVisitor() {
			@Override public void visit(Option o) {
				generateOptionNode(o);
			}
		});
	}
	private void generateOptionNode(Option o) {
		out.print("class " + o.getPhrase().getName() + "Node extends Node");
		if(o.getGeneratedValue().equals(Value.VoidVal)) {
			out.println(" {");
		} else {
			out.println(" implements " + o.getGeneratedValue().asNodeName() + " {");
		}
		
		out.println("\t" + o.getGeneratedValue().asJavaType() + " result;");
		out.println("\t" + o.getGeneratedValue().asJavaType() + " getResult() { return result; }");
		out.println("");
		
		Value[] params = o.getValues();
		for(int i = 0; i < params.length; i++) {
			out.println("\t" + params[i].asNodeName() + " p" + i + ";");
		}
		
		out.println("}");
	}
}
