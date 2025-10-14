package calculator;

import calculator.io.Input;
import camp.nextstep.edu.missionutils.Console;

class Calculator {
    private Input input = new Input();
    public void run() {
        openingMent();
        //입력받기
        input.inputString();

        //결과출력
        int output =0;
        System.out.println("결과 : "+output);
    }
    public void openingMent(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }
}


public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Calculator calc = new Calculator();
        calc.run();
    }
}
