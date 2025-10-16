package calculator.domain.parser;

public class CustomParser implements Parser {
    private static final String FIRSTRULE = "//";
    private static final char SECONDRULE = '\\';
    @Override
    public String[] parsing(String inputString) {
        int firstCustomRuleIndex = inputString.indexOf(FIRSTRULE);
        char[] charList = inputString.toCharArray();
        boolean flag = false;
        int secondCustomRuleIndex = 0;
        int endIndex = 0;
        for (int i = 2; i < charList.length; i++) {
            if (charList[i] == SECONDRULE) {
                if (i + 1 < charList.length && charList[i + 1] == 'n') {
                    flag = true;
                    endIndex = i + 2;
                    secondCustomRuleIndex = i - 1;
                    break;
                }
            }


        }
        String custom = String.valueOf(charList[secondCustomRuleIndex]);//커스텀 구분자
        inputString = inputString.substring(endIndex);
        return inputString.split(custom);
    }
}