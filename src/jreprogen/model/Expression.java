package jreprogen.model;

public interface Expression {

	public interface Mapper<T> {

		T mapImmediateBool();
		T mapImmediateInt();
		T mapImmediateString();
		T map(Option o);
	}
	
	public static final Expression ImmediateString = new Expression() {
		@Override
		public Value getGeneratedValue() {
			return Value.StringVal;
		}
		@Override
		public <T> T map(Mapper<T> mapper) { return mapper.mapImmediateString(); }
	};
	public static final Expression ImmediateInt = new Expression() {
		@Override
		public Value getGeneratedValue() {
			return Value.IntVal;
		}
		@Override
		public <T> T map(Mapper<T> mapper) { return mapper.mapImmediateInt(); }
	};
	public static final Expression ImmediateBool = new Expression() {
		@Override
		public Value getGeneratedValue() {
			return Value.BoolVal;
		}
		@Override
		public <T> T map(Mapper<T> mapper) { return mapper.mapImmediateBool(); }
	};
	
	Value getGeneratedValue();
	<T> T map(Mapper<T> mapper);
}
