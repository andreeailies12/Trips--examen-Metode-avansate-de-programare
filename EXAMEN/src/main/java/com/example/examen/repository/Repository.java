package com.example.examen.repository;

import com.example.examen.domain.Entity;
import com.example.examen.domain.validators.ValidationException;
import java.util.List;

/**
 * Operatiile CRUD com.example.laborator4mapdepredat.repository ale interfetei
 *
 * @param <Id> - tipul E trebuie sa aiba un atribut de tipul mail
 * @param <E>  -  tipul entitatilor salvate in com.example.laborator4mapdepredat.repository
 */

public interface Repository<Id, E extends Entity<Id>> {

    /**
     * Functie ce returneaza entitatea cu id-ul dat ca si parametrul
     *
     * @param mail -id-ul entitatii care urmeaza a fi returnata
     *             id nu trebuie sa fie null
     * @return entitatea cu id-ul specificat
     * sau null - daca nu exista entitatea cu id-ul specificat
     * @throws IllegalArgumentException daca id-ul este null
     */
    E findOne(Id mail);

    Iterable<E> findbyName(String nume, String prenume);

    int findId(String nume, String prenume, String grupa, Double media);

    /**
     * Functie ce returneaza toate entitatile existente in repository
     *
     * @return toate entitatile
     */
    Iterable<E> findAll();

    /**
     * Functie de adaugare a unei entitati in repository
     *
     * @param entity entity trebuie sa nu fie null
     * @return null- daca entitatea data este salvata
     * altfel returneaza entitatea, caci id-ul acela exista deja
     * @throws ValidationException      daca entitatea nu este valida
     * @throws IllegalArgumentException daca entitatea data este nula    *
     */
    E save(E entity);


    /**
     * Sterge entitatea cu id-ul specificat
     *
     * @param mail mail nu trebuie sa fie null
     * @return entitatea stearsa sau null daca nu exista entitatea cu id-ul dat
     * @throws IllegalArgumentException daca id-ul dat este null
     */
    E delete(Id mail);

    /**
     * Modifica entitatea
     *
     * @param entity entity nu trebuie sa fie null
     * @return null - daca entitatea a fost modificata
     * altfel returneaza entitatea specificata  - (daca id-ul dat nu exista).
     * @throws IllegalArgumentException daca entitatea data este nula
     * @throws ValidationException      daca entitatea nu este valida
     */
    E update(E entity);

}




