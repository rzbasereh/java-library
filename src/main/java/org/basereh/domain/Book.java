package org.basereh.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {
    public String name;
    public Publisher publisher;
    public  Author author;
}
