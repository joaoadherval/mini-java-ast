package grammar;

import java.util.List;
import org.antlr.v4.runtime.tree.*;
import ast.*;
import grammar.minijavaParser.*;

public class GeradorAST {
	
	public Program visitGoal(GoalContext g){
		MainClass main = this.visitMain(g.mainclass());
		ClassDeclList classDecList = this.visitClassDecList(g.classdeclaration());
		return new Program(main, classDecList);
	}
	
	private MainClass visitMain(MainclassContext m){
		List<TerminalNode> tn = m.IDENTIFIER();
		TerminalNode id1 = tn.get(0);
		TerminalNode id2 = tn.get(1);
		Statement sm = this.visitStatement(m.statement());
		return new MainClass(new Identifier(id1.getText()), new Identifier(id2.getText()), sm);
	}
	
	private ClassDeclList visitClassDecList(List<ClassdeclarationContext> cdl){
		ClassDeclList classDecList = new ClassDeclList();
		for(int i = 0; i < cdl.size(); i++){
			classDecList.addElement(this.visitClassDeclaration(cdl.get(i)));
		}
		return classDecList;
	}
	
	private ClassDecl visitClassDeclaration(ClassdeclarationContext cd){
		List<TerminalNode> ids = cd.IDENTIFIER();
		ClassDecl classDecl;
		if(ids.size() < 2){
			classDecl = new ClassDeclSimple(new Identifier(ids.get(0).getText()), this.visitVarDecList(cd.vardeclaration()), this.visitMethodDecList(cd.methoddeclaration()));
		}else{
			classDecl = new ClassDeclExtends(new Identifier(ids.get(0).getText()), new Identifier(ids.get(3).getText()), this.visitVarDecList(cd.vardeclaration()), this.visitMethodDecList(cd.methoddeclaration()));
		}
		return classDecl;
	}
	
	private VarDeclList visitVarDecList(List<VardeclarationContext> vdl){
		VarDeclList varDeclList = new VarDeclList();
		for(int i = 0; i < vdl.size(); i++){
			varDeclList.addElement(this.visitVarDecl(vdl.get(i)));
		}
		return varDeclList;
	}
	
	private VarDecl visitVarDecl(VardeclarationContext vd){
		TerminalNode id = vd.IDENTIFIER();
		VarDecl varDecl = new VarDecl(this.visitType(vd.type()), new Identifier(id.getText()));
		return varDecl;
	}
	
	private MethodDeclList visitMethodDecList(List<MethoddeclarationContext> mdl){
		MethodDeclList methodDeclList = new MethodDeclList();
		for(int i = 0; i < mdl.size(); i++){
			methodDeclList.addElement(this.visitMethodDecl(mdl.get(i)));
		}
		return methodDeclList;
	}
	
	private MethodDecl visitMethodDecl(MethoddeclarationContext md){
		List<TypeContext> types = md.type();
		List<TerminalNode> ids = md.IDENTIFIER();
		
		FormalList arguments = new FormalList();
		Type methodType = this.visitType(types.get(0));
		Identifier methodName = new Identifier(ids.get(0).toString());
		
		for(int i = 0; i < types.size(); i++){
			arguments.addElement(new Formal(this.visitType(types.get(i)), new Identifier(ids.get(i).toString())));
		}
		
		VarDeclList variables = this.visitVarDecList(md.vardeclaration());
		StatementList statements = this.visitStatementList(md.statement());
		Exp expression = this.visitExpression(md.expression());
		return new MethodDecl(methodType, methodName, arguments, variables, statements, expression);
		
	}
	
	private Type visitType(TypeContext t){
		String type = t.getText();
		if(type.contains("boolean")){
			return new BooleanType();
		}else if(type.contains("int[]")){
			return new IntArrayType();
		}else if(type.contains("int")){
			return new IntegerType();
		}else{
			return new IdentifierType(t.IDENTIFIER().getText());
		}
	}
	
	private StatementList visitStatementList(List<StatementContext> sl){
		StatementList statementList = new StatementList();
		for(int i = 0; i < sl.size(); i++){
			statementList.addElement(this.visitStatement(sl.get(i)));
		}
		return statementList;
	}
	
	private Statement visitStatement(StatementContext s){
		//String statement = s.getText();
		TerminalNode ids = s.IDENTIFIER();
		
		List<StatementContext> statementList = s.statement();
		List<ExpressionContext> expressionList = s.expression();
		
		if ((statementList.size() == 1) && (expressionList.size() == 1)) {
			return new While(this.visitExpression(expressionList.get(0)), this.visitStatement(statementList.get(0)));
		} else if ((statementList.size() == 2) && (expressionList.size() == 1)) {
			return new If(this.visitExpression(expressionList.get(0)), this.visitStatement(statementList.get(0)), this.visitStatement(statementList.get(1)));
		} else if ((statementList.size() == 0) && (expressionList.size() == 1) && (ids == null)) {
			return new Print(this.visitExpression(expressionList.get(0)));
		} else if (ids != null && expressionList.size() == 1) {
			return new Assign(new Identifier(ids.getText()), this.visitExpression(expressionList.get(0)));
		} else if (ids != null && expressionList.size() == 2) {
			return new ArrayAssign(new Identifier(ids.getText()), this.visitExpression(expressionList.get(0)), this.visitExpression(expressionList.get(1)));
		
		} else return new Block(this.visitStatementList(statementList));
		
//		if(statement.startsWith("if(") || statement.startsWith("if ")){
//			return new If(this.visitExpression(expressions.get(0)), this.visitStatement(s.statement(0)), this.visitStatement(s.statement(1)));
//		}else if(statement.startsWith("while")){
//			return new While(this.visitExpression(expressions.get(0)), this.visitStatement(s.statement(0)));
//		}else if(ids != null && expressions.size() == 2){
//			return new ArrayAssign(new Identifier(ids.getText()), this.visitExpression(expressions.get(0)), this.visitExpression(expressions.get(1)));
//		}else if(ids != null && expressions.size() == 1){
//			return new Assign(new Identifier(ids.getText()), this.visitExpression(expressions.get(0)));
//		}else if(statement.startsWith("System.out.println")){
//			return new Print(this.visitExpression(expressions.get(0)));
//		}else{
//			return new Block(this.visitStatementList(s.statement()));
//		}
	}
	
	private ExpList visitExpList(List<ExpressionContext> el){
		ExpList expList = new ExpList();
		for(int i = 0; i < el.size(); i++){
			expList.addElement(this.visitExpression(el.get(i)));
		}
		return expList;
	}
	
	private Exp visitExpression(ExpressionContext e){
		String expr = e.getText();
		TerminalNode operator = e.OP();
		TerminalNode ids = e.IDENTIFIER();
		TerminalNode integer = e.INTEGER();
		
		List<ExpressionContext> expList = e.expression();
		
		if (operator != null) {
			if (operator.equals("&&")) {				
				return new And(this.visitExpression(expList.get(0)), this.visitExpression(expList.get(1)));				
			} else if (operator.equals("<")) {				
				return new LessThan(this.visitExpression(expList.get(0)), this.visitExpression(expList.get(1)));				
			} else if (operator.equals("+")) {				
				return new Plus(this.visitExpression(expList.get(0)), this.visitExpression(expList.get(1)));				
			} else if (operator.equals("-")) {				
				return new Minus(this.visitExpression(expList.get(0)), this.visitExpression(expList.get(1)));				
			} else if (operator.equals("*")) {				
				return new Times(this.visitExpression(expList.get(0)), this.visitExpression(expList.get(1)));				
			}
		} else if (expList.size() == 2 && ids == null) {			
			return new ArrayLookup(this.visitExpression(expList.get(0)), this.visitExpression(expList.get(1)));			
		} else if (expr.contains("length")) {			
			return new ArrayLength(this.visitExpression(expList.get(0)));			
		} else if (expList.size() >= 1 && ids != null) {
			return new Call(this.visitExpression(expList.get(0)), new Identifier(ids.getText()), this.visitExpList(expList));
		} else if (integer != null) {			
			return new IntegerLiteral(Integer.parseInt(integer.getText()));			
		} else if (expr.equals("true")) {			
			return new True();			
		} else if (expr.equals("false")) {			
			return new False();			
		} else if (ids != null && !expr.contains("(")) {			
			return new IdentifierExp(ids.getText());			
		} else if (expr.equals("this")) {			
			return new This();			
		} else if (expr.contains("new")) {
			if (expList.size() == 1) {				
				return new NewArray(this.visitExpression(expList.get(0)));				
			} else {				
				return new NewObject(new Identifier(ids.getText()));				
			}
		} else if (expr.startsWith("!")) {			
			return new Not(this.visitExpression(expList.get(0)));			
		}
		return this.visitExpression(expList.get(0));
	}
}
