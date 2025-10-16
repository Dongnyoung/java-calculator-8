package calculator.domain.parser;

public class CustomParser{
    String firstCustomRule = "//";
    String str;
    public String[] parsing(String inputString) {
        str = inputString;
        int firstCustomRuleIndex = str.indexOf(firstCustomRule);
        char[] charList = str.toCharArray();
        boolean flag = false;
        int secondCustomRuleIndex = 0;
        int endIndex = 0;
        for (int i = 2; i < charList.length; i++) {
            if (charList[i] == '\\') {
                if (i + 1 < charList.length && charList[i + 1] == 'n') {
                    flag = true;
                    endIndex = i + 2;
                    secondCustomRuleIndex = i - 1;
                    break;
                }
            }


        }
        String custom = String.valueOf(charList[secondCustomRuleIndex]);//커스텀 구분자
        str = inputString.substring(endIndex);
        String[] tokens =  str.split(custom);
        return tokens;
    }
}