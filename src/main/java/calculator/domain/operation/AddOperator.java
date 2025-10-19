package calculator.domain.operation;

public class AddOperator implements Operator {
    private int sum;

    @Override
    public int calc(String[] tokens) {
        for (String t : tokens) {
            sum += Integer.parseInt(t);
        }
        return sum;
    }
}
