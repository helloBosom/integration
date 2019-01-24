package fun.peri.design.action.memento;

public class Client {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator.setFirstNumber(0);
        calculator.setSecondNumber(100);
        System.out.println(calculator.getCalculationResult());
        PreviousCalculationToCareTaker memento = calculator.backupLastCalculation();
        calculator.setFirstNumber(17);
        calculator.setSecondNumber(-290);
        System.out.println(calculator.getCalculationResult());
        calculator.restorePreviousCalculation(memento);
        System.out.println(calculator.getCalculationResult());
    }

}
