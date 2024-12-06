package com.example.demo1;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ImplMetier implements IMetier {
    private Connection connection = SingletonConnexionDB.getConnection();

    // Professor Management
    @Override
    public List<Professor> getProfessors() {
        List<Professor> professors = new ArrayList<>();
        try (Connection conn = SingletonConnexionDB.getConnection()) {
            String query = "SELECT * FROM professors";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                professors.add(new Professor(
                         // This will automatically retrieve the ID
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Cin"),
                        rs.getString("Adresse"),
                        rs.getString("Telephone"),
                        rs.getString("Email"),
                        rs.getDate("Date_recrutement"), // Assuming it's a Date field
                        rs.getString("Departement")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professors;
    }


    @Override
    public List<Professor> searchProfessors(String keyword) {
        List<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM professors WHERE Nom LIKE ? OR Prenom LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    professors.add(new Professor(
                            rs.getString("Nom"),
                            rs.getString("Prenom"),
                            rs.getString("Cin"),
                            rs.getString("Adresse"),
                            rs.getString("Telephone"),
                            rs.getString("Email"),
                            rs.getDate("Date_recrutement"),
                            rs.getString("Departement")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professors;
    }

    @Override
    public void addProfessor(Professor prof) {
        String query = "INSERT INTO professors (Nom, Prenom, Cin, Adresse, Telephone, Email, Date_recrutement, Departement) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(prof.getDate_recrutement().toString());
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, prof.getName());
            ps.setString(2, prof.getPrename());
            ps.setString(3, prof.getCin());
            ps.setString(4, prof.getAdresse());
            ps.setString(5, prof.getTelephone());
            ps.setString(6, prof.getEmail());
            ps.setDate(7, (Date) prof.getDate_recrutement());
            ps.setString(8, prof.getDep());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void updateProfessor(Professor prof) {
        String query = "UPDATE professors SET Nom = ?, Prenom = ?, Cin = ?, Adresse = ?, Telephone = ?, Email = ?, Date_recrutement = ?, Departement = ? WHERE Cin = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, prof.getName());
            ps.setString(2, prof.getPrename());
            ps.setString(3, prof.getCin());
            ps.setString(4, prof.getAdresse());
            ps.setString(5, prof.getTelephone());
            ps.setString(6, prof.getEmail());
            ps.setDate(7, (Date) prof.getDate_recrutement());
            ps.setString(8, prof.getDep());
            ps.setString(9, prof.getCin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignProfessorToDepartment(int professorId, int departmentId) {
        String query = "UPDATE professors SET Departement = (SELECT Nom FROM departement WHERE id = ?) WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, departmentId);
            ps.setInt(2, professorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Department Management
    @Override
    public List<Departement> getDepartments() {
        List<Departement> departments = new ArrayList<>();
        String query = "SELECT Iddep, nom FROM departement";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Departement department = new Departement(rs.getInt("Iddep"),rs.getString("nom"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public void addDepartment(Departement dep) {
        String query = "INSERT INTO departement (Nom) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, dep.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartment(String name) {
        try (Connection conn = SingletonConnexionDB.getConnection()) {
            String query = "DELETE FROM departement WHERE nom = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);// Use the `Name` to delete the specific Departement
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProf(String name) {
        try (Connection conn = SingletonConnexionDB.getConnection()) {
            String query = "DELETE FROM professors WHERE Cin = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name); // Use the `id` to delete the specific professor
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateDepartment(Departement dep) {
        String query = "UPDATE departement SET nom = ? WHERE Iddep = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, dep.getName());
            ps.setInt(2, dep.getId());
            // Assuming id is an integer
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Professor> getProfessorsByDepartment(String name) {
        List<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM professors WHERE Departement = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);


            Date dateRecrutement = null;

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {


                    professors.add(new Professor(
                            rs.getString("Nom"),
                            rs.getString("Prenom"),
                            rs.getString("Cin"),
                            rs.getString("Adresse"),
                            rs.getString("Telephone"),
                            rs.getString("Email"),
                            rs.getDate("Date_recrutement"),
                            rs.getString("Departement")
                    ));
                }
            }
        } catch (SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
    }
        return professors;
}
}

