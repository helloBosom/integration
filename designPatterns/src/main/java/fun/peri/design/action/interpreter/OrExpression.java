package fun.peri.design.action.interpreter;

public class OrExpression extends Expression {

    private Expression expressionOne;

    private Expression expressionTwo;

    public OrExpression(Expression expressionOne, Expression expressionTwo) {
        this.expressionOne = expressionOne;
        this.expressionTwo = expressionTwo;
    }

    @Override
    public boolean interpret(String s) {
        return expressionOne.interpret(s) || expressionTwo.interpret(s);
    }

}
