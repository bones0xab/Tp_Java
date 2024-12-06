package com.example.demo1;

import java.util.Date;


public class Professor {

    private int id;
    private String name;
    private String prename;
    private String cin;
    private String adresse;
    private String telephone;
    private String email;
    private Date date_recrutement;
    private String depp;

    public Professor(String  n, String prnom, String c , String a , String t , String e , Date d, String de)
    {
        this.name = n;
        this.prename = prnom;
        this.cin = c;
        this.adresse = a;
        this.telephone = t;
        this.email = e;
        this.date_recrutement = d;
        this.depp = de;
    }

    public Professor(int i)
    {
        this.id = i;
    }


    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setDate_recrutement(Date date_recrutement) {
        this.date_recrutement = date_recrutement;
    }

    public void setDepp(String depp) {
        this.depp = depp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public void setTelephone(String telephone) {


    }

    //Getters
    public String getName() {
        return this.name;
    }


    public Date getDate_recrutement() {
        return this.date_recrutement;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getPrename() {
        return prename;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getDep() {
        return depp;
    }

    public String getNomDuProfesseur() {
        return getName();
    }


}

