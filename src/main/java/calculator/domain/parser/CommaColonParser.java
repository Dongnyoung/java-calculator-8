package calculator.domain.parser;

public class CommaColonParser{
    String commaColonRule = "[,:]";
    String str;
    public String[] parsing(String inputString) {
        str = inputString;
        String[] tokens = str.split(commaColonRule);
        return tokens;
    }
}