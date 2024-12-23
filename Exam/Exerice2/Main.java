package com.example.examan;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Create the medical office
            ICabinetMetier cabinet = new ICabinetMetier();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Create and add patients
            Patient patient1 = new Patient(1, "Dupont", "Jean", "AB123456",
                    "0612345678", "jean.dupont@email.com",
                    dateFormat.parse("15/05/1980"));
            Patient patient2 = new Patient(2, "Martin", "Marie", "CD789012",
                    "0687654321", "marie.martin@email.com",
                    dateFormat.parse("22/03/1990"));

            cabinet.ajouterPatient(patient1);
            cabinet.ajouterPatient(patient2);

            // Create and add doctors
            Medecin medecin1 = new Medecin(1, "Bernard", "Pierre",
                    "pierre.bernard@cabinet.com", "0723456789");
            Medecin medecin2 = new Medecin(2, "Petit", "Sophie",
                    "sophie.petit@cabinet.com", "0734567890");

            cabinet.ajouterMedecin(medecin1);
            cabinet.ajouterMedecin(medecin2);

            // Create consultations
            Consultation consultation1 = new Consultation(1,
                    dateFormat.parse("20/12/2024"),
                    patient1, medecin1);
            Consultation consultation2 = new Consultation(2,
                    dateFormat.parse("21/12/2024"),
                    patient2, medecin1);
            Consultation consultation3 = new Consultation(3,
                    dateFormat.parse("22/12/2024"),
                    patient1, medecin2);

            cabinet.ajouterConsultation(consultation1);
            cabinet.ajouterConsultation(consultation2);
            cabinet.ajouterConsultation(consultation3);

            // Demonstrate various operations
            System.out.println("\n=== Liste des Patients ===");
            for (Patient p : cabinet.getPatients()) {
                System.out.println("Patient: " + p.getNom() + " " + p.getPrenom());
            }

            System.out.println("\n=== Recherche de patients (mot clé: 'Dup') ===");
            List<Patient> resultats = cabinet.rechercherPatients("Dup");
            for (Patient p : resultats) {
                System.out.println("Trouvé: " + p.getNom() + " " + p.getPrenom());
            }

            System.out.println("\n=== Consultations du Patient 1 ===");
            for (Consultation c : patient1.getConsultations()) {
                System.out.println("Date: " + dateFormat.format(c.getDateConsultation()) +
                        " - Médecin: " + c.getMedecin().getNom());
            }

            System.out.println("\n=== Consultations du Médecin 1 ===");
            for (Consultation c : medecin1.getConsultations()) {
                System.out.println("Date: " + dateFormat.format(c.getDateConsultation()) +
                        " - Patient: " + c.getPatient().getNom());
            }

            // Demonstrate deletion
            System.out.println("\n=== Suppression d'une consultation ===");
            cabinet.supprimerConsultation(consultation1);
            System.out.println("Nombre de consultations restantes: " +
                    cabinet.getConsultations().size());

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}