package grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.Program;
//import visitor.BuildSymbolTableVisitor;
import visitor.PrettyPrintVisitor;
//import visitor.TypeCheckVisitor;

public class Teste {
	
	public static void main(String[] args) throws IOException {
		File in = new File("codigos/QuickSort.txt");
		FileInputStream input = new FileInputStream(in);
		ANTLRInputStream antlr = new ANTLRInputStream(input);
		minijavaLexer lexer = new minijavaLexer(antlr);
		CommonTokenStream token = new CommonTokenStream(lexer);
		minijavaParser parser = new minijavaParser(token);
		GeradorAST ast = new GeradorAST();
		Program programa = ast.visitGoal(parser.goal());
		//BuildSymbolTableVisitor stVis = new BuildSymbolTableVisitor();
		PrettyPrintVisitor ppv = new PrettyPrintVisitor();
		programa.accept(ppv);
	}
}
