import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class SyntacticAnalyser {

	//use the stack as states?

	public static ParseTree parse(List<Token> tokens) throws SyntaxException {
		//Turn the List of Tokens into a ParseTree.

		//based on the top stack symbol and the current token, 
		//read into the parsing table, find the rule applicable for this setting, and operate on the stack and/or move to the next token. 
		ParseTree tree;
		TreeNode prog;
		boolean flag = false;
		ArrayList<Pair> stack = new ArrayList<Pair>();
		//int stackSize = 0;
		if (tokens.isEmpty() == false && tokens.get(0).getType() == Token.TokenType.PUBLIC && tokens.get(1).getType() == Token.TokenType.CLASS && tokens.get(2).getType() == Token.TokenType.ID && tokens.get(3).getType() == Token.TokenType.LBRACE && tokens.get(4).getType() == Token.TokenType.PUBLIC && tokens.get(5).getType() == Token.TokenType.STATIC && tokens.get(6).getType() == Token.TokenType.VOID && tokens.get(7).getType() == Token.TokenType.MAIN && tokens.get(8).getType() == Token.TokenType.LPAREN && tokens.get(9).getType() == Token.TokenType.STRINGARR && tokens.get(10).getType() == Token.TokenType.ARGS && tokens.get(11).getType() == Token.TokenType.RPAREN && tokens.get(12).getType() == Token.TokenType.LBRACE) {
			tree = new ParseTree(prog = new TreeNode(TreeNode.Label.prog, null));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PUBLIC), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.CLASS), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ID), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PUBLIC), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.STATIC), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.VOID), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.MAIN), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.STRINGARR), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ARGS), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), prog));
			prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), prog));
			prog.addChild(new TreeNode(TreeNode.Label.los, prog));
			stack.add(new Pair(TreeNode.Label.los, prog.getParent()));
		} else throw new SyntaxException("SyntaxException");

		for (Token token:tokens.subList(13, tokens.size())) {
			//how I think pair works: if want to make a leaf on the same level to the right, if there are several branches in the whole
			//tree that have different length, then you don't know where to put the leaf. You can just do 
			//Pair().snd().getChildren().get(childrenSize).setChild(yourNode) to do it. If you want to do a leaf on the level below, you
			//just put it on the rightmost leaf (the last one in the parent's list of children), as that is the most recently created one
			if (stack.get(0).fst() == TreeNode.Label.los) {
				if (stack.get(0).snd() == Token.TokenType.ID) {
					if (Character.isDigit(token.getValue().get().charAt(0))) {
						throw new SyntaxException("SyntaxException");
					}
				}
				//empty los
				//If any element is the "TYPE" like int.
				if (token.getType() == Token.TokenType.TYPE) {
					//Create the new child to the parent "los (2)" created above.
					prog.getChildren().get(13).addChild(new TreeNode(TreeNode.Label.stat, prog.getChildren().get(13)));
					//Create the new child decl to the parent "los (2)".
					prog.getChildren().get(13).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.decl, prog.getChildren().get(13)));
					//Create the new child type to the parent the "decl".
					prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.type, prog.getChildren().get(13)));
					//stack.add(new Pair(TreeNode.Label.los, Token.TokenType.TYPE));
					//stack.remove(0);
					//Create the new child (terminal, TYPE, int) to the parent "type".
					prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.TYPE, token.getValue().get()), prog.getChildren().get(13)));
					stack.add(new Pair(TreeNode.Label.los, Token.TokenType.ID));
					stack.remove(0);
				}
				if (stack.get(0).snd() == Token.TokenType.SQUOTE) {
					prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.CHARLIT, token.getValue().get()), prog.getChildren().get(13)));
					prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.SQUOTE), prog.getChildren().get(13)));
					stack.add(new Pair(TreeNode.Label.los, prog.getParent()));
					stack.remove(0);
				}//wip
				if (stack.get(0).snd() == Token.TokenType.TIMES) {
					if (token.getType() == Token.TokenType.TIMES) {
						throw new SyntaxException("SyntaxException");
					}
					else {
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.factor, prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.termprime, prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.NUM, "5"), prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).getChildren().get(1).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
					}
					stack.add(new Pair(TreeNode.Label.los, prog.getParent()));
					stack.remove(0);
				}
				if (stack.get(0).snd() == Token.TokenType.NUM) {
					if (token.getType() == Token.TokenType.NUM && flag == false) {
						throw new SyntaxException("SyntaxException");
					}
					else {
						flag = true;
					}
					if (token.getType() != Token.TokenType.SEMICOLON) {
						if (token.getType() == Token.TokenType.TIMES) {
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PLUS), prog.getChildren().get(13)));					
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.term, prog.getChildren().get(13)));					
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.arithexprprime, prog.getChildren().get(13)));					
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.factor, prog.getChildren().get(13)));					
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.termprime, prog.getChildren().get(13)));					
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.NUM, "2"), prog.getChildren().get(13)));
							prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.TIMES), prog.getChildren().get(13)));
							stack.add(new Pair(TreeNode.Label.los, Token.TokenType.TIMES));
							stack.remove(0);
						}
					}
					else {
						stack.add(new Pair(TreeNode.Label.los, prog.getParent()));
						stack.remove(0);
					}
				}
				if (stack.get(0).snd() == Token.TokenType.ASSIGN) {
					if (token.getType() == Token.TokenType.TRUE) {
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.expr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.relexpr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.TRUE), prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.boolexpr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));		
					}
					else if (token.getType() == Token.TokenType.SQUOTE) {
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.expr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.charexpr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.SQUOTE), prog.getChildren().get(13)));
						stack.add(new Pair(TreeNode.Label.los, Token.TokenType.SQUOTE));
						stack.remove(0);
					}
					else if (token.getType() == Token.TokenType.NUM) {
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.expr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.relexpr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.boolexpr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.arithexpr, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.relexprprime, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.term, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.arithexprprime, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.factor, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.termprime, prog.getChildren().get(13)));
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.NUM, token.getValue().get()), prog.getChildren().get(13)));
						stack.add(new Pair(TreeNode.Label.los, Token.TokenType.NUM));
						stack.remove(0);
					}
					else if (token.getType() == Token.TokenType.PLUS){
						throw new SyntaxException("SyntaxException");
					}
				}
				if (stack.get(0).snd() == TreeNode.Label.possassign) {
					if (token.getValue().get().equals(";")) {
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
					}
					else if (token.getValue().get().equals("=")){
						prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).getChildren().get(2).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ASSIGN), prog.getChildren().get(13)));
						stack.add(new Pair(TreeNode.Label.los, Token.TokenType.ASSIGN));
						stack.remove(0);
					}
				}
				//If any element is type "ID" like a variable declaration.
				if (token.getType() == Token.TokenType.ID) {
					
					String tempVal = token.getValue().get();
					System.out.println(tempVal);

					//Create the child (terminal, ID, i) to the parent "decl".
					prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ID, tempVal), prog.getChildren().get(13)));
					//Create the child "possassign" to the parent "decl".
					prog.getChildren().get(13).getChildren().get(0).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.possassign, prog.getChildren().get(13)));
					stack.add(new Pair(TreeNode.Label.los, TreeNode.Label.possassign));
					stack.remove(0);
					
					//Adds the child "los (2)" to the parent "los (1)".
					prog.getChildren().get(13).addChild(new TreeNode(TreeNode.Label.los, prog.getChildren().get(13)));
					//Adds the child "epsilon" to the parent "los (2)".
					prog.getChildren().get(13).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));
				}
				

				//If any of the elements is a "SEMICOLON" and the 15th element is a "ID".
				if (token.getType() == Token.TokenType.SEMICOLON && tokens.get(14).getType() == Token.TokenType.ID) {
					//Add the child "SEMICOLON" to the parent "stat".
					prog.getChildren().get(13).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.SEMICOLON), prog.getChildren().get(13)));
				}

				//if the 13th element is a "LBRACE" and the 14th element is a "RBRACE".
				if (tokens.get(12).getType() == Token.TokenType.LBRACE && tokens.get(13).getType() == Token.TokenType.RBRACE) {
				
				}
				//If the 3rd last element is a "SEMICOLON".
				else if (tokens.get(tokens.size()-3).getType() != Token.TokenType.SEMICOLON) {
					throw new SyntaxException("SyntaxException");
				}
				
				//If the 14th element is a "SEMICOLON".
				if (tokens.get(13).getType() == Token.TokenType.SEMICOLON) {
					//Adds child "stat" to the parent "los (1)".
					prog.getChildren().get(13).addChild(new TreeNode(TreeNode.Label.stat, prog.getChildren().get(13)));
					//Adds the child "SEMICOLON" to the parent "stat".
					prog.getChildren().get(13).getChildren().get(0).addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.SEMICOLON), prog.getChildren().get(13)));
					//Adds the child "los (2)" to the parent "los (1)".
					prog.getChildren().get(13).addChild(new TreeNode(TreeNode.Label.los, prog.getChildren().get(13)));
					//Adds the child "epsilon" to the parent "los (2)".
					prog.getChildren().get(13).getChildren().get(1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(13)));

					stack.add(new Pair(TreeNode.Label.prog, stack.get(0).snd()));
					stack.remove(0);
				}
				if (token.getType() == Token.TokenType.RBRACE) {
					//sets the child for the rightmost child of prog as "epsilon"
					if (token.getType() == Token.TokenType.RBRACE && tokens.get(13).getType() != Token.TokenType.TYPE) {
						prog.getChildren().get(prog.getChildren().size()-1).addChild(new TreeNode(TreeNode.Label.epsilon, prog.getChildren().get(prog.getChildren().size()-1)));
					}
					prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), prog));
					//set the first one to prog so that if theres another right brace the program ends
					stack.add(new Pair(TreeNode.Label.prog, stack.get(0).snd()));
					stack.remove(0);
				}
			}
			else if (stack.get(0).fst() == TreeNode.Label.prog) {
				if (token.getType() == Token.TokenType.RBRACE) {
					prog.addChild(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), prog));
				}
			}
		}
		//return new ParseTree(prog);
		return tree;
	}
}

// The following class may be helpful.

class Pair<A, B> {
	private final A a;
	private final B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A fst() {
		return a;
	}

	public B snd() {
		return b;
	}

	@Override
	public int hashCode() {
		return 3 * a.hashCode() + 7 * b.hashCode();
	}

	@Override
	public String toString() {
		return "{" + a + ", " + b + "}";
	}

	@Override
	public boolean equals(Object o) {
		if ((o instanceof Pair<?, ?>)) {
			Pair<?, ?> other = (Pair<?, ?>) o;
			return other.fst().equals(a) && other.snd().equals(b);
		}

		return false;
	}
}
