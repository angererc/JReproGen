package jreprogen.model;

public interface Generator {

	public static final Generator ImmediateString = new Generator() {
		@Override
		public Value getGeneratedValue() {
			return Value.StringVal;
		}
		@Override
		public <T> T map(GeneratorMapper<T> mapper) { return mapper.mapImmediateString(); }
	};
	public static final Generator ImmediateInt = new Generator() {
		@Override
		public Value getGeneratedValue() {
			return Value.IntVal;
		}
		@Override
		public <T> T map(GeneratorMapper<T> mapper) { return mapper.mapImmediateInt(); }
	};
	public static final Generator ImmediateBool = new Generator() {
		@Override
		public Value getGeneratedValue() {
			return Value.BoolVal;
		}
		@Override
		public <T> T map(GeneratorMapper<T> mapper) { return mapper.mapImmediateBool(); }
	};
	
	Value getGeneratedValue();
	<T> T map(GeneratorMapper<T> mapper);
}
