package jreprogen.codegen;

import java.util.List;


public interface CodeGeneratorPass {

	void generate(CodeGenerator codeGenerator, List<Message> messages) throws Exception;
	
}
