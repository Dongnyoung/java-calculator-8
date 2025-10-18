package calculator.domain.operation;

public class Add implements Operator {
    private int sum;

    @Override
    public int calc(String[] tokens) {
        for (String t : tokens) {
            sum += Integer.parseInt(t);
        }
        return sum;
    }
}
