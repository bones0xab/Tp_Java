import java.util.Objects;

public class Etudiant {
    private String Name;

    public Etudiant(String n)
    {
        this.Name = n;
    }

    public String getName()
    {
        return this.Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(Name, etudiant.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Name);
    }
}
