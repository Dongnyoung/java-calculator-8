package calculator.domain.parser;

public class CommaColonParser implements Parser{
    private static final String RULE = "[,:]";
    @Override
    public String[] parsing(String inputString) {
        return inputString.split(RULE);
    }
}