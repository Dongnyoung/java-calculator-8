package calculator;

import calculator.domain.InputValidator;
import calculator.domain.validation.CompositeValidator;
import calculator.domain.validation.NullValidator;
import calculator.domain.validation.NumberTokenValidator;
import calculator.domain.validation.TokenValidator;
import calculator.domain.operation.AddOperator;
import calculator.domain.operation.Operator;
import calculator.domain.parser.CommaColonParser;
import calculator.domain.parser.CustomParser;
import calculator.domain.parser.Parser;
import calculator.io.Input;
import calculator.io.Output;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;


class CalculatorController {
    //의존성 주입되면 바뀌지않도록 final 이용
    private final Input input;
    private final Output output;
    private final InputValidator inputValidator;
    private final Operator operator;
    public CalculatorController() {
        this.input = new Input();
        this.output = new Output();
        this.inputValidator = new InputValidator();
        this.operator=new AddOperator(); //해당 문제에서 더하기만 수행하기에
    }
    public void run() {
        openingMent();
        //입력받기
        String inputString = input.inputString();

        int ans = parse(inputString);

        //결과출력
        output.printAnswer(ans);
    }

    private int parse(String inputString) {
        // 1) 기본 검증
        String str = inputValidator.valid(inputString);
        if(str.equals("0")){
            return 0;
        }

        //2) 파서 선택
        Parser parser = resolver(str);

        //3) 토큰화
        String[] tokens = parser.parsing(str);

        //4) 토큰 검증
        TokenValidator validator = validatorResolver();
        validator.valid(tokens);

        //5) 계산
        return operator.calc(tokens);
    }
    private TokenValidator validatorResolver(){
        List<TokenValidator> tokenValidatorList = new ArrayList<>();
        tokenValidatorList.add(new NumberTokenValidator());
        tokenValidatorList.add(new NullValidator());

        return new CompositeValidator(tokenValidatorList);
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
            new CalculatorController().run();
        }
        finally {
            Console.close();  //자원정리
        }
    }
}
