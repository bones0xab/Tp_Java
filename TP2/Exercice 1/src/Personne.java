public class Personne {
    private String nom, prenom, email;
    private long age;
    private String tel;
    public Personne(String nom,String prenom,String email,long age, String tel)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.age = age;
    }

    public void afficher()
    {
        System.out.println("Name : " + this.nom);
        System.out.println("Prenom : " + this.prenom);
        System.out.println("Email : " + this.email);
        System.out.println("Tel : " + this.tel);
        System.out.println("Age : " + this.age);

    }
}
