package com.example.examan;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CabinetController implements Initializable {
    private ICabinetMetier cabinetMetier;

    // Patient Fields
    @FXML private TextField searchPatientField;
    @FXML private TextField patientId;
    @FXML private TextField patientNom;
    @FXML private TextField patientPrenom;
    @FXML private TextField patientCin;
    @FXML private TextField patientTel;
    @FXML private TextField patientEmail;
    @FXML private DatePicker patientDateNaissance;
    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, Integer> patientIdCol;
    @FXML private TableColumn<Patient, String> patientNomCol;
    @FXML private TableColumn<Patient, String> patientPrenomCol;
    @FXML private TableColumn<Patient, String> patientCinCol;
    @FXML private TableColumn<Patient, String> patientTelCol;
    @FXML private TableColumn<Patient, String> patientEmailCol;
    @FXML private TableColumn<Patient, Date> patientDateCol;

    // Medecin Fields
    @FXML private TextField medecinId;
    @FXML private TextField medecinNom;
    @FXML private TextField medecinPrenom;
    @FXML private TextField medecinEmail;
    @FXML private TextField medecinTel;
    @FXML private TableView<Medecin> medecinTable;
    @FXML private TableColumn<Medecin, Integer> medecinIdCol;
    @FXML private TableColumn<Medecin, String> medecinNomCol;
    @FXML private TableColumn<Medecin, String> medecinPrenomCol;
    @FXML private TableColumn<Medecin, String> medecinEmailCol;
    @FXML private TableColumn<Medecin, String> medecinTelCol;

    // Consultation Fields
    @FXML private TextField consultationId;
    @FXML private DatePicker consultationDate;
    @FXML private ComboBox<Patient> patientCombo;
    @FXML private ComboBox<Medecin> medecinCombo;
    @FXML private TableView<Consultation> consultationTable;
    @FXML private TableColumn<Consultation, Integer> consultationIdCol;
    @FXML private TableColumn<Consultation, Date> consultationDateCol;
    @FXML private TableColumn<Consultation, String> consultationPatientCol;
    @FXML private TableColumn<Consultation, String> consultationMedecinCol;


    public List<Consultation> getConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        String query = "SELECT * FROM consultation"; // Query to fetch all consultations

        try (Connection conn = SingletonConnexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Assuming you have a Consultation constructor that takes the necessary fields
                // You may need to fetch associated patient and medecin data here as well
                Consultation consultation = new Consultation(
                        rs.getInt("idConsultation"),
                        rs.getDate("dateConsultation"),
                        new Patient(rs.getInt("idPatient")), // You might want to adjust this logic
                        new Medecin(rs.getInt("idMedecin"))  // Likewise, adjust for Medecin
                );
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    public List<Medecin> getMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        String query = "SELECT * FROM medecin"; // Query to fetch all medecins

        try (Connection conn = SingletonConnexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Assuming you have a Medecin constructor that takes the necessary fields
                Medecin medecin = new Medecin(
                        rs.getInt("idMedecin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("tel")
                );
                medecins.add(medecin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient"; // Query to fetch all patients

        try (Connection conn = SingletonConnexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Assuming you have a Patient constructor that takes the necessary fields
                Patient patient = new Patient(
                        rs.getInt("idPatient"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("cin"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getDate("dateNaissance")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cabinetMetier = new ICabinetMetier();
        initializeTables();
        loadData();
    }

    private void initializeTables() {
        // Initialize Patient columns
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("idPatient"));
        patientNomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        patientPrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        patientCinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        patientTelCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        patientEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        patientDateCol.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));

        // Initialize Medecin columns
        medecinIdCol.setCellValueFactory(new PropertyValueFactory<>("idMedecin"));
        medecinNomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        medecinPrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        medecinEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        medecinTelCol.setCellValueFactory(new PropertyValueFactory<>("tel"));

        // Initialize Consultation columns
        consultationIdCol.setCellValueFactory(new PropertyValueFactory<>("idConsultation"));
        consultationDateCol.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        consultationPatientCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPatient().getIdPatient() + ""));
        consultationMedecinCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedecin().getIdMedecin() + ""));
    }

    private void loadData() {
        // Load data into tables
        patientTable.getItems().setAll(getPatients());
        medecinTable.getItems().setAll(getMedecins());
        consultationTable.getItems().setAll(getConsultations());

        // Load data into ComboBoxes for Consultations
        patientCombo.getItems().setAll(getPatients());
        medecinCombo.getItems().setAll(getMedecins());
    }

    // Patient Methods
    public List<Patient> rechercherPatients(String keyword) {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient WHERE nom LIKE ? OR prenom LIKE ? OR email LIKE ? OR cin LIKE ?";

        // Prepare the keyword for searching (adding wildcards to search any part of the string)
        String searchKeyword = "%" + keyword + "%";

        System.out.println(searchKeyword);
        try (Connection conn = SingletonConnexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the search keyword for each parameter
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);
            pstmt.setString(3, searchKeyword);
            pstmt.setString(4, searchKeyword);

            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Create Patient objects from the result set
                    Patient patient = new Patient(
                            rs.getInt("idPatient"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("cin"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getDate("dateNaissance")
                    );
                    patients.add(patient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    @FXML
    private void searchPatients() {
        // Get the keyword entered by the user in the search field
        String keyword = searchPatientField.getText();

        // Call the method from cabinetMetier to search for patients
        List<Patient> results = rechercherPatients(keyword);

        System.out.println(results);
        // Update the table with the search results
        patientTable.getItems().setAll(results);
    }



    @FXML
    private void addPatient() {
        try {
            // Create Patient object
            Patient patient = new Patient(
                    Integer.parseInt(patientId.getText()),
                    patientNom.getText(),
                    patientPrenom.getText(),
                    patientCin.getText(),
                    patientTel.getText(),
                    patientEmail.getText(),
                    java.sql.Date.valueOf(patientDateNaissance.getValue())
            );

            // Get database connection
            Connection conn = SingletonConnexionDB.getConnection();

            if (conn != null) {
                // SQL query to insert new patient
                String query = "INSERT INTO patient (idPatient, nom, prenom, cin, telephone, email, dateNaissance) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, patient.getIdPatient());
                    pstmt.setString(2, patient.getNom());
                    pstmt.setString(3, patient.getPrenom());
                    pstmt.setString(4, patient.getCin());
                    pstmt.setString(5, patient.getTelephone());
                    pstmt.setString(6, patient.getEmail());
                    pstmt.setDate(7, (java.sql.Date) patient.getDateNaissance());

                    // Execute the query
                    int result = pstmt.executeUpdate();

                    if (result > 0) {
                        // If database insertion is successful, add to the local list
                        cabinetMetier.ajouterPatient(patient);
                        loadData();
                        clearPatientForm();
                        showInfo("Patient added successfully");
                    } else {
                        showError("Error adding the patient to the database");
                    }
                }
                // Close the connection
                conn.close();
            } else {
                showError("Unable to connect to the database");
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        } catch (Exception e) {
            showError("Error adding patient: " + e.getMessage());
        }
    }

    @FXML
    private void deletePatient() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try (Connection conn = SingletonConnexionDB.getConnection()) {
                if (conn != null) {
                    // SQL query to delete patient
                    String query = "DELETE FROM patient WHERE idPatient = ?";

                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setInt(1, selected.getIdPatient()); // Set the selected patient's ID

                        int result = pstmt.executeUpdate();

                        if (result > 0) {
                            // Patient successfully deleted
                            cabinetMetier.supprimerPatient(selected);
                            loadData();
                        } else {
                            showError("Error deleting the patient from the database");
                        }
                    }
                } else {
                    showError("Unable to connect to the database");
                }
            } catch (SQLException e) {
                showError("Database error: " + e.getMessage());
            } catch (Exception e) {
                showError("Error deleting patient: " + e.getMessage());
            }
        } else {
            showError("No patient selected");
        }
    }


    @FXML
    private void clearPatientForm() {
        patientId.clear();
        patientNom.clear();
        patientPrenom.clear();
        patientCin.clear();
        patientTel.clear();
        patientEmail.clear();
        patientDateNaissance.setValue(null);
    }

    // Medecin Methods
    @FXML
    private void addMedecin() {
        try {
            // Create Medecin object
            Medecin medecin = new Medecin(
                    Integer.parseInt(medecinId.getText()),
                    medecinNom.getText(),
                    medecinPrenom.getText(),
                    medecinEmail.getText(),
                    medecinTel.getText()
            );

            // Get database connection
            Connection conn = SingletonConnexionDB.getConnection();

            if (conn != null) {
                // SQL query to insert new medecin
                String query = "INSERT INTO medecin (idMedecin, nom, prenom, email, tel) VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, medecin.getIdMedecin());
                    pstmt.setString(2, medecin.getNom());
                    pstmt.setString(3, medecin.getPrenom());
                    pstmt.setString(4, medecin.getEmail());
                    pstmt.setString(5, medecin.getTel());

                    // Execute the query
                    int result = pstmt.executeUpdate();

                    if (result > 0) {
                        // If database insertion is successful, add to the local list
                        cabinetMetier.ajouterMedecin(medecin);
                        loadData();
                        clearMedecinForm();
                        showInfo("Médecin ajouté avec succès");
                    } else {
                        showError("Erreur lors de l'ajout du médecin dans la base de données");
                    }
                }
                // Close the connection since your SingletonConnexionDB always returns a new one
                conn.close();
            } else {
                showError("Impossible de se connecter à la base de données");
            }
        } catch (SQLException e) {
            showError("Erreur de base de données: " + e.getMessage());
        } catch (Exception e) {
            showError("Erreur lors de l'ajout du médecin: " + e.getMessage());
        }
    }

    @FXML
    private void deleteMedecin() {
        Medecin selected = medecinTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                // Show confirmation dialog
                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDialog.setTitle("Confirmation de suppression");
                confirmDialog.setHeaderText("Supprimer le médecin");
                confirmDialog.setContentText("Êtes-vous sûr de vouloir supprimer le médecin: " +
                        selected.getNom() + " " + selected.getPrenom() + "?");

                Optional<ButtonType> result = confirmDialog.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Get database connection
                    Connection conn = SingletonConnexionDB.getConnection();

                    if (conn != null) {
                        // SQL query to delete medecin
                        String query = "DELETE FROM medecin WHERE idMedecin = ?";

                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            pstmt.setInt(1, selected.getIdMedecin());

                            // Execute the query
                            int deleteResult = pstmt.executeUpdate();

                            if (deleteResult > 0) {
                                // If database deletion is successful, remove from the local list
                                cabinetMetier.supprimerMedecin(selected);

                                // Refresh the table
                                loadData();

                                // Clear the form
                                clearMedecinForm();

                                // Show success message
                                showInfo("Médecin supprimé avec succès");
                            } else {
                                showError("Erreur: Médecin non trouvé dans la base de données");
                            }
                        }
                        // Close the connection
                        conn.close();
                    } else {
                        showError("Impossible de se connecter à la base de données");
                    }
                }
            } catch (SQLException e) {
                showError("Erreur de base de données: " + e.getMessage());
            } catch (Exception e) {
                showError("Erreur lors de la suppression du médecin: " + e.getMessage());
            }
        } else {
            showError("Veuillez sélectionner un médecin à supprimer");
        }
    }
    @FXML
    private void clearMedecinForm() {
        medecinId.clear();
        medecinNom.clear();
        medecinPrenom.clear();
        medecinEmail.clear();
        medecinTel.clear();
    }

    @FXML
    private void addConsultation() {
        try {
            // Get values from the UI components
            Consultation consultation = new Consultation(
                    Integer.parseInt(consultationId.getText()),
                    java.sql.Date.valueOf(consultationDate.getValue()),
                    patientCombo.getValue(),
                    medecinCombo.getValue()
            );

            // SQL query to insert a new consultation
            String query = "INSERT INTO consultation (idConsultation, dateConsultation, idPatient, idMedecin) VALUES (?, ?, ?, ?)";

            try (Connection conn = SingletonConnexionDB.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Set values for the prepared statement
                pstmt.setInt(1, consultation.getIdConsultation());
                pstmt.setDate(2,(java.sql.Date) consultation.getDateConsultation());
                pstmt.setInt(3, consultation.getPatient().getIdPatient()); // Assuming Patient object has getIdPatient method
                pstmt.setInt(4, consultation.getMedecin().getIdMedecin()); // Assuming Medecin object has getIdMedecin method

                // Execute the query
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    // On success, reload data and clear the form
                    showInfo("Consultation added successfully");
                    loadData();
                    clearConsultationForm();
                } else {
                    showError("Error adding consultation to the database");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Error adding consultation: " + e.getMessage());
            }
        } catch (Exception e) {
            showError("Error adding consultation: " + e.getMessage());
        }
    }

    @FXML
    private void updateConsultation() {
        Consultation selected = consultationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                // Remove the selected consultation from the patient and doctor
                selected.getPatient().getConsultations().remove(selected);
                selected.getMedecin().getConsultations().remove(selected);

                // Create a new consultation with updated values
                selected = new Consultation(
                        Integer.parseInt(consultationId.getText()),
                        java.sql.Date.valueOf(consultationDate.getValue()),
                        patientCombo.getValue(),
                        medecinCombo.getValue()
                );

                // SQL query to update an existing consultation
                String query = "UPDATE consultation SET dateConsultation = ?, idPatient = ?, idMedecin = ? WHERE idConsultation = ?";

                try (Connection conn = SingletonConnexionDB.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    // Set values for the prepared statement
                    pstmt.setDate(1, java.sql.Date.valueOf(consultationDate.getValue()));
                    pstmt.setInt(2, patientCombo.getValue().getIdPatient()); // Assuming patientCombo is a ComboBox of Patient
                    pstmt.setInt(3, medecinCombo.getValue().getIdMedecin()); // Assuming medecinCombo is a ComboBox of Medecin
                    pstmt.setInt(4, selected.getIdConsultation()); // ID of the selected consultation

                    // Execute the update query
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        showInfo("Consultation updated successfully");
                        loadData();
                        clearConsultationForm();
                    } else {
                        showError("Error updating consultation in the database");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    showError("Error updating consultation: " + e.getMessage());
                }
            } catch (Exception e) {
                showError("Error updating consultation: " + e.getMessage());
            }
        } else {
            showError("Please select a consultation to update");
        }
    }

    @FXML
    private void deleteConsultation() {
        Consultation selected = consultationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // SQL query to delete a consultation
            String query = "DELETE FROM consultation WHERE idConsultation = ?";

            try (Connection conn = SingletonConnexionDB.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Set the ID of the consultation to be deleted
                pstmt.setInt(1, selected.getIdConsultation()); // ID of the selected consultation

                // Execute the delete query
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    showInfo("Consultation deleted successfully");
                    loadData();
                } else {
                    showError("Error deleting consultation from the database");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Error deleting consultation: " + e.getMessage());
            }
        } else {
            showError("Please select a consultation to delete");
        }
    }

    @FXML
    private void clearConsultationForm() {
        consultationId.clear();
        consultationDate.setValue(null);
        patientCombo.setValue(null);
        medecinCombo.setValue(null);
    }

    // Table Selection Handlers
    @FXML
    private void handlePatientTableClick() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            patientId.setText(String.valueOf(selected.getIdPatient()));
            patientNom.setText(selected.getNom());
            patientPrenom.setText(selected.getPrenom());
            patientCin.setText(selected.getCin());
            patientTel.setText(selected.getTelephone());
            patientEmail.setText(selected.getEmail());
            patientDateNaissance.setValue(((java.sql.Date) selected.getDateNaissance()).toLocalDate());
        }
    }

    @FXML
    private void handleMedecinTableClick() {
        Medecin selected = medecinTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            medecinId.setText(String.valueOf(selected.getIdMedecin()));
            medecinNom.setText(selected.getNom());
            medecinPrenom.setText(selected.getPrenom());
            medecinEmail.setText(selected.getEmail());
            medecinTel.setText(selected.getTel());
        }
    }

    @FXML
    private void handleConsultationTableClick() {
        Consultation selected = consultationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            consultationId.setText(String.valueOf(selected.getIdConsultation()));
            consultationDate.setValue(((java.sql.Date) selected.getDateConsultation()).toLocalDate());
            patientCombo.setValue(selected.getPatient());
            medecinCombo.setValue(selected.getMedecin());
        }
    }

    // Utility Methods
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Initialize table selection listeners
    private void initializeTableListeners() {
        patientTable.setOnMouseClicked(event -> handlePatientTableClick());
        medecinTable.setOnMouseClicked(event -> handleMedecinTableClick());
        consultationTable.setOnMouseClicked(event -> handleConsultationTableClick());

        // Set up ComboBox displays
        patientCombo.setCellFactory(param -> new ListCell<Patient>() {
            @Override
            protected void updateItem(Patient item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNom() + " " + item.getPrenom());
                }
            }
        });

        medecinCombo.setCellFactory(param -> new ListCell<Medecin>() {
            @Override
            protected void updateItem(Medecin item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNom() + " " + item.getPrenom());
                }
            }
        });
    }
}