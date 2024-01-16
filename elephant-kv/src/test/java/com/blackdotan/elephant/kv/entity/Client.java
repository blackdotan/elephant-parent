package com.blackdotan.elephant.kv.entity;

/**
 * Created by ryan on 下午6:03.
 */
public class Client implements java.io.Serializable {
    private Integer id;

    private String name;

    public Client() {
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
