package br.com.makersweb.makersfood.domain.permission;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

/**
 * @author aaristides
 */
public class PermissionValidator extends Validator {

    private final Permission permission;

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    public PermissionValidator(final Permission permission, final ValidationHandler handler) {
        super(handler);
        this.permission = permission;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.permission.getName();
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

    private void checkDescriptionConstraints() {
        final var description = this.permission.getDescription();
        if (description == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (description.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        final var length = description.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'description' must be between 3 and 255 characters"));
        }
    }
}
