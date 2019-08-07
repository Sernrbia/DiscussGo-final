package com.example.discussgo.addon;

public class Validator {

    public static void isEmpty(String field, String nameOfField) throws IllegalArgumentException {
        if (field == null || field.isEmpty())
            throw new IllegalArgumentException("Field " + nameOfField + " can't be blank.");
    }

}
