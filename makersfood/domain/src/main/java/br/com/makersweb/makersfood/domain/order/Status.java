package br.com.makersweb.makersfood.domain.order;

import java.util.Arrays;
import java.util.List;

/**
 * @author aaristides
 */
public enum Status {

    CREATED("CREATED"),
    CONFIRMED("CONFIRMED", CREATED),
    DELIVERED("DELIVERED", CONFIRMED),
    CANCELED("CANCELED", CREATED);

    private String description;
    private List<Status> previousStatuses;

    Status(String description, Status... previousStatuses) {
        this.description = description;
        this.previousStatuses = Arrays.asList(previousStatuses);
    }

    public String getDescription() {
        return description;
    }

    public boolean cannotChangeTo(final Status newStatus) {
        return !newStatus.previousStatuses.contains(this);
    }

    public boolean canChangeTo(final Status newStatus) {
        return !cannotChangeTo(newStatus);
    }
}
