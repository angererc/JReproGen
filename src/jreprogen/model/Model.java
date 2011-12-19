package jreprogen.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jreprogen.Message;

/**
 * The model collects all the contexts
 * @author angererc
 *
 */
public class Model {

	private Map<String, Context> contexts = new HashMap<String, Context>();
	
	public Model(Map<String, Context> contexts) {
		for(Context c : contexts.values()) {
			this.contexts.put(c.getName(), c);
		}
	}
	
	public void addContext(Context ctxt) {
		if(contexts.containsKey(ctxt.getName())) {
			throw new ModelException("Context with name " + ctxt.getName() + " already exists!");
		}
		contexts.put(ctxt.getName(), ctxt);
		ctxt.setModel(this);
	}
	
	public Collection<Context> getContexts() {
		return contexts.values();
	}
	
	public Context getContext(String name) {
		return contexts.get(name);
	}
	
	public List<Message> validate() {
		List<Message> messages = new ArrayList<Message>();
		for(Context c : contexts.values()) {
			c.validate(messages);
		}
		return messages;
	}
	
	public boolean hasBeenValidated() {
		for(Context c : contexts.values()) {
			if(! c.hasBeenValidated())
				return false;
		}
		return true;
	}
	public void traverse(Visitor v) {
		v.visit(this);
		for(Context c : contexts.values()) {
			c.traverse(v);
		}
	}
	public void describeTo(PrintStream out) {
		out.println("<model>");
		for(Context c : contexts.values()) {
			c.describeTo(out);
		}
		out.println("</model>");
	}
}
