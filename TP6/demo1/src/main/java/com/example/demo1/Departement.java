package com.example.demo1;

public class Departement {
    private String name;
    private int id;

    public Departement(int i, String n)
    {
        this.id = i;
        this.name = n;
    }

    public Departement(String departmentName) {
        this.name = departmentName;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
