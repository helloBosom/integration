package fun.peri.design.action.interpreter;

public class Client {

    /**
     * 解析树
     */
    public static Expression init() {
        Expression terminalOne = new TerminalExpress("A");
        Expression terminalTwo = new TerminalExpress("B");
        Expression terminalThree = new TerminalExpress("C");
        Expression terminalFour = new TerminalExpress("D");
        //B C
        Expression alternationOne = new OrExpression(terminalTwo, terminalThree);
        //A or (B C)
        Expression alternationTwo = new OrExpression(terminalOne, alternationOne);
        //D and (A or (B C))
        return new AndExpression(terminalFour, alternationTwo);
    }

    public static void main(String[] args) {
        Expression expression = init();
        String contextOne = "D A";
        String contextTwo = "A B";
        System.out.println(expression.interpret(contextOne));
        System.out.println(expression.interpret(contextTwo));
    }

}
