package calculator;

import calculator.domain.InputValidator;
import calculator.domain.parser.CommaColonParser;
import calculator.domain.parser.CustomParser;
import calculator.domain.parser.Parser;
import calculator.io.Input;
import calculator.io.Output;
import camp.nextstep.edu.missionutils.Console;


class Add{
    private int sum;
    public int calc(String[] tokens) {
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
        return sum;
    }
}


class Calculator {
    private Input input = new Input();
    private Output output = new Output();
    private InputValidator inputValidator = new InputValidator();
    private Parser parser;
    private Add add = new Add();
    public void run() {
        openingMent();
        //입력받기
        String inputString = input.inputString();

        int ans = parse(inputString);

        //결과출력
        output.printAnswer(ans);
    }

    private int parse(String inputString) {
        String str = inputValidator.valid(inputString);
        if(str.equals("0")){
            return 0;
        }
        int sum = 0;
        Parser parser = resolver(str);
        String[] tokens = parser.parsing(str);
        sum = add.calc(tokens);
        return sum;
    }
    private Parser resolver(String input) {
        // 커스텀 규칙 확인
        String customFirstRule = "//";
        if (input.startsWith(customFirstRule)) {
            return new CustomParser();
        } else {
            return new CommaColonParser();
        }

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
