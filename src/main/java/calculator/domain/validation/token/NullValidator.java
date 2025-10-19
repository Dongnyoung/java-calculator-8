package calculator.domain.validation.token;

public class NullValidator implements TokenValidator {
    @Override
    public String[] valid(String[] tokens) {
        for (String t : tokens) {
            if (t.isEmpty()) {
                throw new IllegalArgumentException("구분자 사이에 빈 문자열이 있음");
            }
        }
        return tokens;
    }
}
