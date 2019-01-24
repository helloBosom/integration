package fun.peri.design.action.interpreter;

import java.util.StringTokenizer;

public class TerminalExpress extends Expression {

    private String literal;

    /**
     * StringTokenizer 方法不区分标识符、数和带引号的字符串，它们也不识别并跳过注释。
     * 可以在创建时指定，也可以根据每个标记来指定分隔符（分隔标记的字符）集。
     */
    @Override
    public boolean interpret(String s) {
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            String temp = stringTokenizer.nextToken();
            if (temp.equals(literal)) {
                return true;
            }
        }
        return false;
    }

    public TerminalExpress(String s) {
        literal = s;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

}
