package org.basereh.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Author {
    private String firstname;
    private String lastname;

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
