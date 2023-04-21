package com.example.examen.domain.validators;


import com.example.examen.domain.Entity;

/**
 * @param <Id>
 * @param <E> o enitate de tipul E
 */
public interface Validator<Id, E extends Entity<Id>> {
    void validate(E Entity) throws ValidationException;

}