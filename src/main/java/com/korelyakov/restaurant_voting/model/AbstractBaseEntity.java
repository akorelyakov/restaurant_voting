package com.korelyakov.restaurant_voting.model;

public class AbstractBaseEntity {

    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    // doesn't work for hibernate lazy proxy
    public int id() {
        //Assert.notNull(id, "Entity must have id");
        return id;
    }

    boolean isNew() {
        return getId() == null;
    }
}
