package com.revature.vew.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.vew.models.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@ActiveProfiles("repository") // This sets profile to repository which means the Command Line Runner Bean will be run.
@DataJpaTest
public class TagRepositoryTests {
    private TagRepository tagRepository;

    @Autowired
    public TagRepositoryTests(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Test
    public void testFindAll() {
        List<Tag> allTags = tagRepository.findAll();
        assertThat(allTags.size()).isEqualTo(3);
    }
}
