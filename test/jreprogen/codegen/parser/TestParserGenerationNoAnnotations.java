package jreprogen.codegen.parser;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.junit.Test;


public class TestParserGenerationNoAnnotations {

	@Test
	public void otherTest() {
		Parser<?> ignored = Parsers.or(Scanners.JAVA_BLOCK_COMMENT, Scanners.WHITESPACES);
		
		//
		Parser<?> identTokenizer = Terminals.Identifier.TOKENIZER;
		Parser<?> stringTokenizer = Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER;
		Parser<?> tokenizer = Parsers.or(identTokenizer, stringTokenizer);
		
		//
		Parser<String> identParser = Terminals.Identifier.PARSER;
		Parser<String> stringParser = Terminals.StringLiteral.PARSER;
		Parser<String> methodParser = Parsers.sequence(identParser, stringParser);
		
		//
		Parser<String> parser = methodParser.from(tokenizer, ignored.skipMany());
		
		System.out.println(parser.parse("foo /* comment */ \"bar\""));
	}
	
	@Test
	public void test() {
//		CodeGenerator generator = new CodeGenerator(null);
//		
//		generator.addPass(new GenerateParserPass());
//		List<Message> messages = generator.generate();
//		System.out.println(messages);
//		assertSame(messages.size(), 0);
		
		
		
		Terminals operators = Terminals.operators(",");
		
		Parser<?> intTokenizer = Terminals.IntegerLiteral.TOKENIZER;
		
		Parser<?> ignored = Parsers.or(Scanners.JAVA_BLOCK_COMMENT, Scanners.WHITESPACES);
		Parser<?> tokenizer = Parsers.or(operators.tokenizer(), intTokenizer);
		
		Parser<String> integerSyntacticParser = Terminals.IntegerLiteral.PARSER;
		Parser<List<String>> integers = integerSyntacticParser.sepBy(operators.token(",")).from(tokenizer, ignored.skipMany());
		
		assertEquals(Arrays.asList("1", "2", "3"), integers.parse("1, /*this is comment*/2, 3"));
		
	}
	
}
