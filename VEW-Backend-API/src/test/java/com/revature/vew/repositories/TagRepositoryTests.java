package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TagRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void testFindAll() {
        Tag tagOne = new Tag("Java");
        entityManager.persist(tagOne);
        Tag tagTwo = new Tag("Capco");
        entityManager.persist(tagTwo);

        List<Tag> allTags = tagRepository.findAll();

        assertThat(allTags.size()).isEqualTo(2);
    }
}
