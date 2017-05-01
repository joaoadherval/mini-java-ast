// Generated from minijava.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link minijavaParser}.
 */
public interface minijavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link minijavaParser#vardeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVardeclaration(@NotNull minijavaParser.VardeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#vardeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVardeclaration(@NotNull minijavaParser.VardeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#mainclass}.
	 * @param ctx the parse tree
	 */
	void enterMainclass(@NotNull minijavaParser.MainclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#mainclass}.
	 * @param ctx the parse tree
	 */
	void exitMainclass(@NotNull minijavaParser.MainclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#classdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassdeclaration(@NotNull minijavaParser.ClassdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#classdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassdeclaration(@NotNull minijavaParser.ClassdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(@NotNull minijavaParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(@NotNull minijavaParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull minijavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull minijavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#methoddeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethoddeclaration(@NotNull minijavaParser.MethoddeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#methoddeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethoddeclaration(@NotNull minijavaParser.MethoddeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull minijavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull minijavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link minijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull minijavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link minijavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull minijavaParser.TypeContext ctx);
}