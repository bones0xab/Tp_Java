<h1 align="center">Exam</h1>

***

# [ Exercise 1](#ex1)


# [ Exerise 2](#ex2)

***

# <a id="ex1"></a>Exercise 1:
---

`Overview`: 
```bash

In this task we will A medical office conducts consultations under 
the supervision of medical staff, who interpret results to aid in 
diagnosing and monitoring diseases. 
A Java application will be developed to manage these consultations based 
on specific information.
```


**Tasks :**
- Create Class Patient with attributes
- Create Class Medecin with attributes
- Create Class Consultation with attributes
- Build a Class All operations called CabinetMedical where I mention all the
Operations given in the exercise.

- All this classes are built with their getters and comments for a better understanding.


# Class Diagram.
---
This is the class diagram where I made the relationship between the Classes,
I was inspired by the real world to build it!

![image](https://github.com/user-attachments/assets/46bf26db-6e46-49ec-8814-41fcffe85839)

**Figure : Class Diagram of Exercise 1**


### Let's code !.
---

## Class Patient.
---


***

# <a id="ex2"></a>Exercise 2 :
---
`Overview : `
In this exercise we want to buil a basic javaFX like the same concept of the exercise one.


## MCD
---
![image](https://github.com/user-attachments/assets/46bf26db-6e46-49ec-8814-41fcffe85839)

## MLD.
---
```sql
CREATE DATABASE IF NOT EXISTS ronaldo;

USE ronaldo;

CREATE TABLE Patient (
    id_patient INT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    cin VARCHAR(255) UNIQUE NOT NULL,
    telephone VARCHAR(255),
    email VARCHAR(255),
    date_naissance DATE
);

CREATE TABLE Medecin (
    id_medecin INT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    tel VARCHAR(255)
);

CREATE TABLE Consultation (
    id_consultation INT PRIMARY KEY,
    date_consultation DATE NOT NULL,
    id_patient INT,
    id_medecin INT,
    FOREIGN KEY (id_patient) REFERENCES Patient(id_patient) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_medecin) REFERENCES Medecin(id_medecin) ON DELETE SET NULL ON UPDATE CASCADE
);
```
**After the creation of tables and database I want to share a simple image to be sure I create the DB**

![image](https://github.com/user-attachments/assets/02973827-012c-4ca9-83f1-508afab6bc42)


## Console Test.
---

This is a simple console of the application before we dive into the core application using javaFX and JDBC and DB.

![image](https://github.com/user-attachments/assets/198658d3-b549-4ec4-92d5-ec2d6e271292)

```java

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

```




# Apllication.
---
As you can see (I don't see the tests it's just random names), this is my empty javaFX now, I will builda real scenario
let's insert names to test the ability!

## Add Patient
---

![image](https://github.com/user-attachments/assets/4208d948-45ee-4f9e-b5c9-841cc70d46e4)

After I enter some names,  you can see that they are add successfully to the DB, and it shows a successful message.

![image](https://github.com/user-attachments/assets/3ce48169-01b4-4b43-8d2f-c92e327cbedd)

## Delete Patient
---
You can see the delete option after selecting a patient you can see when i click on delete he s gone! .
![image](https://github.com/user-attachments/assets/3f3cc67f-4af4-4579-b083-8d619135ed5c)


## Add medecin.
---
By default aminou dosen't exist but when i added you see it in the interface.

![image](https://github.com/user-attachments/assets/5c7ba1ca-0ce7-461a-a274-435f12976f54)

## Delete medecin.
---
And the same like patien in here.

![image](https://github.com/user-attachments/assets/3d15e436-1a1c-4f70-a2d2-b7993c03faf8)

They are gone. 

![image](https://github.com/user-attachments/assets/a7079309-e9a6-4723-9cc6-fe39527f6a45)


## Add consultation
---
In the first , I don't have any consultation, I will add one.

![image](https://github.com/user-attachments/assets/c828f3b5-0b8f-4ec2-a6de-8804a9d96bdf)

For me I add with Id of patient and medecin and here is the out put . 

![image](https://github.com/user-attachments/assets/49558a4b-2e72-48ac-a0fa-999f15e7f059)


## Search
---
I added the search functionality I added to see the patient by name, email, or CIN.

![image](https://github.com/user-attachments/assets/45d84aa1-05b0-4845-974f-a6fb20da16b6)


