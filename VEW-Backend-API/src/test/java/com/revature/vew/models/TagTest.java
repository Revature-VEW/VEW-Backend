package com.revature.vew.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TagTest {
    @Test
    public void testConstructor() {
        Tag actualTag = new Tag();
        assertEquals("Tag{tagId=0, tag='null'}", actualTag.toString());
        assertNull(actualTag.getTag());
        Set<Question> questions = actualTag.getQuestions();
        assertTrue(questions instanceof java.util.HashSet);
        assertEquals(0, actualTag.getTagId());
        assertEquals(0, questions.size());
    }

    @Test
    public void testConstructor2() {
        assertEquals("Tag", (new Tag("Tag")).getTag());
    }

    @Test
    public void testSetTagId() {
        Tag tag = new Tag();
        tag.setTagId(123);
        assertEquals(123, tag.getTagId());
    }

    @Test
    public void testSetTag() {
        Tag tag = new Tag();
        tag.setTag("Tag");
        assertEquals("Tag", tag.getTag());
    }

    @Test
    public void testSetQuestions() {
        Tag tag = new Tag();
        HashSet<Question> questionSet = new HashSet<Question>();
        tag.setQuestions(questionSet);
        assertSame(questionSet, tag.getQuestions());
    }

    @Test
    public void testToString() {
        assertEquals("Tag{tagId=0, tag='null'}", (new Tag()).toString());
    }
}

