package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Optional;

public class DepController {


    @FXML
    private TableView<Departement> departmentTable;
    @FXML
    private TableColumn<Departement, String> nomColumn;
    @FXML
    private Button addDepartmentButton;
    @FXML
    private Button updateDepartmentButton;
    @FXML
    private Button deleteDepartmentButton;
    @FXML
    private Button viewProfessorsButton;

    @FXML
    private TableView<Professor> professorTableView;
    @FXML
    private TableColumn<Professor, String> professorNameColumn = new TableColumn<>("Name");
    @FXML
    private Button closeProfessorTableViewButton;


    private final ImplMetier metier = new ImplMetier(); // Assumes ImplMetier is set up to handle database operations.

    @FXML
    public void initialize() {
        // Set up the column to display department names
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Load initial data into the table
        loadDepartments();

        // Set up the button actions
        addDepartmentButton.setOnAction(event -> addDepartment());
        updateDepartmentButton.setOnAction(event -> updateDepartment());
        deleteDepartmentButton.setOnAction(event -> deleteDepartment());
        viewProfessorsButton.setOnAction(event -> viewProfessors());
    }

    private void loadDepartments() {
        // Load all departments from the database and populate the TableView
        departmentTable.setItems(FXCollections.observableArrayList(metier.getDepartments()));
    }

    private void addDepartment() {
        // Show a dialog to get the new department name
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter Département");
        dialog.setHeaderText("Ajouter un nouveau département");
        dialog.setContentText("Nom du Département:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(departmentName -> {
            if (!departmentName.trim().isEmpty()) {
                // Add the department using ImplMetier
                Departement newDepartment = new Departement(departmentName);
                metier.addDepartment(newDepartment);

                // Reload the department table to reflect the new addition
                loadDepartments();

                // Show a success alert
                showAlert(AlertType.INFORMATION, "Succès", "Le département a été ajouté avec succès.");
            } else {
                showAlert(AlertType.WARNING, "Erreur", "Le nom du département ne peut pas être vide.");
            }
        });
    }

    private void updateDepartment() {
        Departement selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            TextInputDialog dialog = new TextInputDialog(selectedDepartment.getName());
            dialog.setTitle("Modifier Département");
            dialog.setHeaderText("Modifier le nom du département");
            dialog.setContentText("Nouveau nom du Département:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newDepartmentName -> {
                if (!newDepartmentName.trim().isEmpty()) {
                    selectedDepartment.setName(newDepartmentName);
                    metier.updateDepartment(selectedDepartment);
                    loadDepartments();
                    showAlert(AlertType.INFORMATION, "Succès", "Le département a été modifié avec succès.");
                } else {
                    showAlert(AlertType.WARNING, "Erreur", "Le nom du département ne peut pas être vide.");
                }
            });
        } else {
            showAlert(AlertType.WARNING, "Erreur", "Veuillez sélectionner un département à modifier.");
        }
    }

    private void deleteDepartment() {
        Departement selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            // Confirm deletion
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de Suppression");
            confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer ce département ?");
            confirmationAlert.setContentText("Supprimer le département " + selectedDepartment.getName());

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                metier.deleteDepartment(selectedDepartment.getName());
                loadDepartments();
                showAlert(AlertType.INFORMATION, "Succès", "Le département a été supprimé avec succès.");
            }
        } else {
            showAlert(AlertType.WARNING, "Erreur", "Veuillez sélectionner un département à supprimer.");
        }
    }


    private void viewProfessors() {
        Departement selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();

        if (selectedDepartment != null) {
            List<Professor> professors = metier.getProfessorsByDepartment(selectedDepartment.getName());
            professorTableView.setVisible(true);
            closeProfessorTableViewButton.setVisible(true);
            professorTableView.setItems(FXCollections.observableArrayList(professors));
            professorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            System.out.println(professorTableView.getItems());
        } else {
            showAlert(AlertType.WARNING, "Error", "Please select a department first.");
        }

        closeProfessorTableViewButton.setOnAction(event -> {
            professorTableView.setVisible(false);
            closeProfessorTableViewButton.setVisible(false);
        });
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
