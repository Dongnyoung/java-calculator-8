package calculator;

import calculator.io.Input;
import camp.nextstep.edu.missionutils.Console;

class Calculator {
    private Input input = new Input();
    public void run() {
        openingMent();
        //입력받기
        String inputString = input.inputString();

        int ans = validAndParsing(inputString);

        //결과출력
        int output =ans;
        System.out.println("결과 : "+output);
    }

    private static int validAndParsing(String inputString) {
        if(inputString ==null || inputString.isEmpty()) return 0;
        String str = inputString;
        int sum = 0;
        //입력검사
        //콤마,콜론
        String commaColonRule = "[,:]"; //정규식에서는 [,]는 포함되지않는다
        //커스텀구분자 체크
        String firstCustomRule = "//";
        String secondCustomRule = "\\n";
        if(str.startsWith(firstCustomRule)) {
            int firstCustomRuleIndex = str.indexOf(firstCustomRule);
            if(!str.contains(secondCustomRule)){
                throw new IllegalArgumentException("잘못된 형식의 커스텀 구분자");
            }
            int secondCustomRuleIndex = str.indexOf(secondCustomRule);
            String custom = str.substring(firstCustomRuleIndex+2,secondCustomRuleIndex);//커스텀 구분자
            if(custom.length()!=1){
                throw new IllegalArgumentException("커스텀 구분자는 단일 문자만 허용");
            }
            str = inputString.substring(secondCustomRuleIndex+2);
            String[] tokens = str.split(custom);
            for (String t : tokens) {
                if (t.isEmpty()) {
                    throw new IllegalArgumentException("구분자 사이에 빈 문자열이 있음");
                }
                if (!t.matches("\\d+")) // "\\d+"는 정규식, 숫자 한개이상으로만 포함된 문자열
                {
                    if (t.matches("-\\d+")) {
                        throw new IllegalArgumentException("음수는 허용되지않음");
                    }
                    throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있음 " + t);
                }
                sum += Integer.parseInt(t);
            }
        }
        else {
            String[] tokens = str.split(commaColonRule);

            for (String t : tokens) {
                if (t.isEmpty()) {
                    throw new IllegalArgumentException("구분자 사이에 빈 문자열이 있음");
                }
                if (!t.matches("\\d+")) // "\\d+"는 정규식, 숫자 한개이상으로만 포함된 문자열
                {
                    if (t.matches("-\\d+")) {
                        throw new IllegalArgumentException("음수는 허용되지않음");
                    }
                    throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있음 " + t);
                }
                sum += Integer.parseInt(t);
            }
        }
        return sum;
    }

    public void openingMent(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }
}


public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try {
            new Calculator().run();
        }
        finally {
            Console.close();  //자원정리
        }
    }
}
