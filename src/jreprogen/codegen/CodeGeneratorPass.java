package jreprogen.codegen;

import java.util.List;

import jreprogen.model.Message;

public interface CodeGeneratorPass {

	void generate(CodeGenerator codeGenerator, List<Message> messages) throws Exception;
	
}
