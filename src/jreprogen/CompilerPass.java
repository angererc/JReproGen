package jreprogen;

import java.util.List;



public interface CompilerPass {

	void generate(Compiler codeGenerator, List<Message> messages) throws Exception;
	
}
