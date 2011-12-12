package jreprogen.model;

public interface Value {
	
	public static final Value VoidVal = new Value() {
		@Override public String toString() { return "Void"; }
		@Override public <T> T map(ValueMapper<T> mapper) { return mapper.mapVoidValue(); }
	};
	public static final Value IntVal = new Value() { 
		@Override public String toString() { return "Int"; }
		@Override public <T> T map(ValueMapper<T> mapper) { return mapper.mapIntValue(); }
	};
	public static final Value BoolVal = new Value() { 
		@Override public String toString() { return "Bool"; }
		@Override public <T> T map(ValueMapper<T> mapper) { return mapper.mapBoolValue(); }
	};
	public static final Value StringVal = new Value() { 
		@Override public String toString() { return "String"; }
		@Override public <T> T map(ValueMapper<T> mapper) { return mapper.mapStringValue(); }
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
		@Override public <T> T map(ValueMapper<T> mapper) { return mapper.map(this); }
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
		@Override public <T> T map(ValueMapper<T> mapper) { return mapper.map(this); }
	};
	
	/*
	 * Value interface
	 */
	<T> T map(ValueMapper<T> mapper);
}
