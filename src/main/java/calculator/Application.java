package calculator;

import calculator.domain.Validator;
import calculator.domain.parser.CommaColonParser;
import calculator.domain.parser.CustomParser;
import calculator.io.Input;
import calculator.io.Output;
import camp.nextstep.edu.missionutils.Console;





class Calculator {
    private Input input = new Input();
    private Output output = new Output();
    private Validator validator = new Validator();
    private CustomParser customParser = new CustomParser();
    private CommaColonParser commaColonParser = new CommaColonParser();
    public void run() {
        openingMent();
        //입력받기
        String inputString = input.inputString();

        int ans = parse(inputString);

        //결과출력
        output.printAnswer(ans);
    }

    private int parse(String inputString) {
        String str = validator.valid(inputString);
        if(str.equals("0")){
            return 0;
        }
        int sum = 0;
        //입력검사
        //콤마,콜론
        String commaColonRule = "[,:]"; //정규식에서는 [,]는 포함되지않는다
        //커스텀구분자 체크
        String firstCustomRule = "//";

        if(str.startsWith(firstCustomRule)) {
            String[] tokens = customParser.parsing(str);
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
            String[] tokens = commaColonParser.parsing(str);

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
