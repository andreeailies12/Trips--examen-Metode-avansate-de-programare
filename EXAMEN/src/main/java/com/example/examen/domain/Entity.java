package com.example.examen.domain;


import java.io.Serializable;

public class Entity<ID> implements Serializable {

    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;

    /**
     * Constructor cu parametri pentru superclasa Entity
     *
     */
//    public Entity(ID id) {
//        this.id = id;
//    }

    /**
     * Getter pentru email-ul entitatii
     *
     * @return email
     */
    public ID getId() {
        return id;
    }

    /**
     * Setter pentru email-ul entitatii
     *
     * @param id de tipul generic ID
     */
    public void setId(ID id) {
        this.id = id;
    }


}