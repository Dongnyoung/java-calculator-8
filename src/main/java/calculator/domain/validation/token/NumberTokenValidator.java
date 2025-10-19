package calculator.domain.validation.token;

public class NumberTokenValidator implements TokenValidator {
    @Override
    public String[] valid(String[] tokens) {
        for (String t : tokens) {
            if (!t.matches("\\d+")) // "\\d+"는 정규식, 숫자 한개이상으로만 포함된 문자열
            {
                if (t.matches("-\\d+")) {
                    throw new IllegalArgumentException("음수는 허용되지않음");
                }
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있음 " + t);
            }

        }
        return tokens;
    }
}
