package jreprogen.codegen.syntax;

import jreprogen.generators.syntax.SableCC3xGeneratorVisitor;
import jreprogen.syntax.Grammar;
import jreprogen.syntax.VerificationVisitor;
import jreprogen.syntax.builder.BuildGrammar;

import org.junit.Test;


public class TestSableGenerator {

	@Test
	public void testSableGenerator() {
		
		Grammar g = BuildGrammar.inPackage("my.package")
			.production("disk_body")
				.alternative("default").is()
					.literal("speed")
			.production("disk")
				.alternative("basic").is()
					.literal("sata").optional()
					.literal("disk")
				.alternative("extended").is()
					.literal("sata").optional()
					.literal("disk")
					.literal("with")
					.ref("disk_body")
			.build();
		
		
		System.err.println(VerificationVisitor.verify(g));
		SableCC3xGeneratorVisitor.generateGrammarFile(g, System.out);
	}
}
