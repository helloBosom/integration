package fun.peri.design.action.interpreter;

public class AndExpression extends Expression {

    private Expression expressionOne;

    private Expression expressionTwo;

    public AndExpression(Expression expressionOne, Expression expressionTwo) {
        this.expressionOne = expressionOne;
        this.expressionTwo = expressionTwo;
    }

    @Override
    public boolean interpret(String s) {
        return expressionOne.interpret(s) && expressionTwo.interpret(s);
    }

}
