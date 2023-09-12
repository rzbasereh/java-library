package org.basereh.dao;

import org.basereh.domain.Author;

import java.util.Arrays;
import java.util.List;

public class AuthorDao implements ObjectDao<Author> {
    @Override
    public List<Author> getAll() {
        return Arrays.asList(
                Author.builder().firstname("ali").lastname("akbari").build(),
                Author.builder().firstname("ahmad").lastname("alavi").build()
        );
    }
}
