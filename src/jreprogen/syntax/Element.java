package jreprogen.syntax;

import java.util.List;

import jreprogen.Message;

public abstract class Element {

	public interface ModifierMapper<T> {
		T mapOne();
		T mapOptional();
		T mapMany();
		T mapAtLeastOne();
	}
	public enum Modifier {
		ONE {
			@Override
			public <T> T map(ModifierMapper<T> mapper) {
				return mapper.mapOne();
			}
		},
		OPTIONAL {
			@Override
			public <T> T map(ModifierMapper<T> mapper) {
				return mapper.mapOptional();
			}
		},
		MANY {
			@Override
			public <T> T map(ModifierMapper<T> mapper) {
				return mapper.mapMany();
			}
		},
		AT_LEAST_ONE {
			@Override
			public <T> T map(ModifierMapper<T> mapper) {
				return mapper.mapAtLeastOne();
			}
		};
		public abstract <T> T map(ModifierMapper<T> mapper);
	}
	
	private Alternative alternative;
	private Modifier modifier = Modifier.ONE;
	
	public Alternative getAlternative() {
		return alternative;
	}
	
	void setAlternative(Alternative alternative) {
		this.alternative = alternative;
	}
	
	public Modifier getModifier() {
		return modifier;
	}
	
	public void setModifier(Modifier modifier) {
		if(modifier == null)
			this.modifier = Modifier.ONE;
		else
			this.modifier = modifier;
	}
	
	public abstract void visit(SyntaxVisitor visitor);
	
	public void verify(List<Message> messages) {
		
	}
	
}
