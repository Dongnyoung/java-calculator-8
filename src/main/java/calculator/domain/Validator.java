package calculator.domain;

public class Validator{
    private String str;

    public String valid(String inputString){
        str = inputString;
        if(str==null || str.isEmpty()){
            return "0";
        }
        //콤마,콜론
        String commaColonRule = "[,:]"; //정규식에서는 [,]는 포함되지않는다
        //커스텀구분자 체크
        String firstCustomRule = "//";
        if(str.startsWith(firstCustomRule)) {
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
            if (!flag) {
                throw new IllegalArgumentException("잘못된 형식의 커스텀 구분자");
            }

            String custom = String.valueOf(charList[secondCustomRuleIndex]);//커스텀 구분자
            if (custom.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 단일 문자만 허용");
            }
            if (endIndex == charList.length) {
                //빈 문자열
                return "0";
            }

        }

        return str;
    }
}
