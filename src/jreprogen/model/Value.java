package jreprogen.model;

public interface Value {
	
	public static final Value VoidVal = new Value() {
		@Override public Value resolveReferences(Model model) { return this; }
		@Override public String toString() { return "Void"; } 
		@Override public String asNodeName() { return "VoidNode"; } 
		@Override public String asJavaType() { return "Void"; } 
	};
	public static final Value IntVal = new Value() { 
		@Override public Value resolveReferences(Model model) { return this; }
		@Override public String toString() { return "Int"; } 
		@Override public String asNodeName() { return "IntNode"; }
		@Override public String asJavaType() { return "Integer"; }
	};
	public static final Value BoolVal = new Value() { 
		@Override public Value resolveReferences(Model model) { return this; }
		@Override public String toString() { return "Bool"; } 
		@Override public String asNodeName() { return "BoolNode"; }
		@Override public String asJavaType() { return "Boolean"; }
	};
	public static final Value StringVal = new Value() { 
		@Override public Value resolveReferences(Model model) { return this; }
		@Override public String toString() { return "String"; } 
		@Override public String asNodeName() { return "StringNode"; }
		@Override public String asJavaType() { return "String"; }
	};
	
	public static class ObjectVal implements Value {
		private final Class<?> type;
		public ObjectVal(Class<?> type) {
			this.type = type;
		}
		public Class<?> getType() {
			return type;
		}
		@Override 
		public Value resolveReferences(Model model) { 
			return this; 
		}
		@Override 
		public boolean equals(Object obj) {
			if(obj instanceof ObjectVal) {
				ObjectVal other = (ObjectVal)obj;
				return type.equals(other.type);
			} else {
				return false;
			}
		}
		@Override
		public int hashCode() { 
			return type.hashCode(); 
		}
		@Override 
		public String toString() { 
			return "ObjectVal(" + type.getCanonicalName() + ")"; 
		} 
		
		@Override public String asNodeName() { return type.getCanonicalName().replace('.', '_') + "Node"; }
		@Override public String asJavaType() { return type.getCanonicalName(); }
	}
	
	public static class ContextRef implements Value {
		private final String name;
		public ContextRef(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		@Override 
		public Value resolveReferences(Model model) { 
			return model.getContext(name); 
		}
		@Override 
		public boolean equals(Object obj) {
			if(obj instanceof ContextRef) {
				ContextRef other = (ContextRef)obj;
				return name.equals(other.name);
			} else {
				return false;
			}
		}
		@Override
		public int hashCode() { 
			return name.hashCode(); 
		}
		@Override 
		public String toString() { 
			return "ContextRef(" + name + ")"; 
		} 
		
		@Override public String asNodeName() { return name.replace('.', '_') + "Node"; }
		@Override public String asJavaType() { return name; }
	};
	
	/*
	 * Value interface
	 */
	Value resolveReferences(Model model);
	String asNodeName();
	String asJavaType();
}
