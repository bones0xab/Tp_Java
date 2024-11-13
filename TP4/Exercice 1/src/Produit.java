import java.util.ArrayList;
import java.util.Scanner;

public class Produit {
    private int id;
    private String name;
    private double prix;

    public Produit(int i,String n,double p)
    {
        this.id = i;
        this.name = n;
        this.prix = p;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrix() {
        return prix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }



}
