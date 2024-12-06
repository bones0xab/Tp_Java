package com.example.demo1;

import java.util.List;

public interface IMetier {
    // Professor Management
    List<Professor> getProfessors();
    List<Professor> searchProfessors(String keyword);
    void addProfessor(Professor prof);
    void deleteProf(String name);
    void updateProfessor(Professor prof);
    void assignProfessorToDepartment(int professorId, int departmentId);

    // Department Management
    List<Departement> getDepartments();
    void addDepartment(Departement dep);
    void deleteDepartment(String name);
    void updateDepartment(Departement dep);
    List<Professor> getProfessorsByDepartment(String name);
}
