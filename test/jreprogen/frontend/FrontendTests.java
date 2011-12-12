package jreprogen.frontend;

import static org.junit.Assert.*;

import java.util.Map;

import jreprogen.model.Model;

import org.junit.Test;


public class FrontendTests {

	public static interface ComputerBuilder {
		ProcessorBuilder processor();
		DiskBuilder disk();
	}
	
	public static interface ProcessorBuilder {
		ProcessorBuilder cores(int num, String vendor);
		ProcessorBuilder speed(int speed);
		ProcessorBuilder i386();
	}
	
	public static interface DiskBuilder {
		DiskBuilder size(int sice);
		DiskBuilder speed(int speed);
		DiskBuilder sata();
		int getSize();
		Map<String, String> getVendorMap();
	}
	
	@Test
	public void testParsingComputerBuilder() {
		
		JavaClassParser parser = new JavaClassParser();
		Model model = parser.parse(ComputerBuilder.class, ProcessorBuilder.class, DiskBuilder.class);
		
		model.describeTo(System.out);
		
		assertNotNull(model.getContext(ProcessorBuilder.class.getCanonicalName()));
		
	}
}
