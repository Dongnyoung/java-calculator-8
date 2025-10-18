package calculator.domain.TokenValidator;

import java.util.List;

public class CompositeValidator implements TokenValidator {
    private final List<TokenValidator> validators;
    public CompositeValidator(List<TokenValidator> validators) {
        this.validators = validators;
    }

    @Override
    public String[] valid(String[] tokens) {
        for(TokenValidator validator : validators) {
            validator.valid(tokens);
        }
        return tokens;
    }
}
