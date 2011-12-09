package jreprogen.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The model collects all the contexts
 * @author angererc
 *
 */
public class Model {

	private Map<String, Context> contexts = new HashMap<String, Context>();
	
	public Model() {
		
	}
	
	public void addContext(Context ctxt) {
		contexts.put(ctxt.getName(), ctxt);
	}
}
