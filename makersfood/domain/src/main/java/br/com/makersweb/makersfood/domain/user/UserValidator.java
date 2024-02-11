package br.com.makersweb.makersfood.domain.user;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author aaristides
 */
public class UserValidator extends Validator {

    private final User user;

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;
    private static final String MAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public UserValidator(final User user, final ValidationHandler handler) {
        super(handler);
        this.user = user;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkMailConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.user.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }

    private void checkMailConstraints() {
        final var mail = this.user.getMail();
        if (mail == null) {
            this.validationHandler().append(new Error("'mail' should not be null"));
            return;
        }

        if (mail.isBlank()) {
            this.validationHandler().append(new Error("'mail' should not be empty"));
            return;
        }

        if (isNotMailValid(mail)) {
            this.validationHandler().append(new Error("'mail' not valid"));
        }
    }

    private boolean isNotMailValid(final String mail) {
        return !Pattern.compile(MAIL_REGEX, Pattern.CASE_INSENSITIVE).matcher(mail).matches();
    }
}
