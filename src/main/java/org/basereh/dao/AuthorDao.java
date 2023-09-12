package org.basereh.dao;

import lombok.RequiredArgsConstructor;
import org.basereh.domain.Author;

import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class AuthorDao implements ObjectDao<Author> {
    private final Statement statement;

    @Override
    public List<Author> getAll() {
        return Arrays.asList(
                Author.builder().firstname("ali").lastname("akbari").build(),
                Author.builder().firstname("ahmad").lastname("alavi").build()
        );
    }
}
