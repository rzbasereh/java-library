package org.basereh.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {
    private Integer id;
    private String name;
    private Publisher publisher;
    private  Author author;

    @Override
    public String toString() {
        return name + "(pub: " + publisher.toString() + " )(author: " + author.toString() + " )";
    }
}
