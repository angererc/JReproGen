package jreprogen.codegen.syntax;

import org.junit.Test;
import static jreprogen.codegen.syntax.KeywordToken.*;

public class TestSableGenerator {

	@Test
	public void testSableGenerator() {
		Grammar g = new Grammar();
		g.setPackageName("my.package");
		
		KeywordElement ke;
		ComplexElement ce;
		
		/* disk_body
		 * 
		 */
		Production diskBody = new Production();
		diskBody.setName("disk_body");
		
		Alternative def = new Alternative();
		def.setName("default");
		diskBody.addAlternative(def);
		
		ke = new KeywordElement();
		ke.setToken(keyword("speed"));
		ke.setModifier(Element.Modifier.OPTIONAL);
		def.addElement(ke);
		
		/*
		 * DISK
		 */
		Production disk = new Production();
		disk.setName("disk");
		
		/*
		 * {basic} T.sata? T.disk 
		 */
		Alternative basic = new Alternative();
		basic.setName("basic");
		
		
		ke = new KeywordElement();
		ke.setToken(keyword("sata"));
		ke.setModifier(Element.Modifier.OPTIONAL);
		basic.addElement(ke);
		
		ke = new KeywordElement();
		ke.setToken(keyword("disk"));
		basic.addElement(ke);
		
		/*
		 * {extended} kw_sata? kw_disk with disk_body*;
		 */
		Alternative extended = new Alternative();
		extended.setName("extended");
		
		ke = new KeywordElement();
		ke.setToken(keyword("sata"));
		ke.setModifier(Element.Modifier.OPTIONAL);
		extended.addElement(ke);
		
		ke = new KeywordElement();
		ke.setToken(keyword("disk"));
		extended.addElement(ke);
		
		ke = new KeywordElement();
		ke.setToken(keyword("with"));
		extended.addElement(ke);
		
		ce = new ComplexElement();
		ce.setProduction(diskBody);
		extended.addElement(ce);
		
		/*
		 * 
		 */
		disk.addAlternative(basic);
		disk.addAlternative(extended);
		
		g.addProduction(disk);
		g.addProduction(diskBody);
		
		System.err.println(VerificationVisitor.verify(g));
		SableCC3xGeneratorVisitor.generateGrammarFile(g, System.out);
	}
}
