# TP 6
---

### MCD
---
![image.png](assets/MCD.png)


### MLD

## Modèle Logique de Données (MLD)

### Table: Professeur
| **Nom de Colonne**    | **Type**    | **Contraintes**                                  |
|------------------------|-------------|-------------------------------------------------|
| `id_prof`             | `INT`       | PRIMARY KEY                                     |
| `nom`                 | `VARCHAR`   | NOT NULL                                        |
| `prenom`              | `VARCHAR`   | NOT NULL                                        |
| `cin`                 | `VARCHAR`   | NOT NULL, UNIQUE                                |
| `adresse`             | `VARCHAR`   |                                                 |
| `telephone`           | `VARCHAR`   |                                                 |
| `email`               | `VARCHAR`   | NOT NULL, UNIQUE                                |
| `date_recrutement`    | `DATE`      |                                                 |
| `id_depart`           | `INT`       | FOREIGN KEY REFERENCES `Departement(id_depart)` |

---

### Table: Departement
| **Nom de Colonne**    | **Type**    | **Contraintes**                                  |
|------------------------|-------------|-------------------------------------------------|
| `id_depart`           | `INT`       | PRIMARY KEY                                     |
| `nom`                 | `VARCHAR`   | NOT NULL                                        |

---

### Relations ☺️:
- **1:N Relation**:
  - Une instance de `Departement` peut être associée à plusieurs instances de `Professeur`.
  - Implémentée via la clé étrangère `id_depart` dans la table `Professeur` qui fait référence à `id_depart` dans la table `Departement`.


***

### Goal 🥅:
---
In this Practical activity we want to make an application for manipulate some professors and departments using javaFX,
First of all, the application must be runned by javaFX and scene Builder , finally Mysql for handling the data !

### Functionalities ▶️:
---

As we see in the MLD and MCD, the application must include features like addProfessor addDepartment and the other requested functionalities.

**Professor requirement 👨‍🏫:**
```bash
-> addProfessor
-> deleteProfessor
-> updateProfessor
-> affectDeptoProfessor
-> searchProfessor
-> showProfessors
```

**Departement requirement 🏬:**
```bash
-> addDepartment
-> deleteDepartement
-> affectDepartement
-> updateDepartement
-> ShowprofessorbyDepartment
```

***

## Databases Screens 🖼️:
---
After some time I create a basic database for an example to show how the things work first, as well to stick to the requirements of the Practical Work !

*This is the pictures or the screens of the DB locally.*

### Professors Data👨‍🏫:
--
![image.png](assets/DBprof.png)


### Department Date🏬:
--
![image.png](assets/DBdep.png)


***
# Code ☣️:
---

## Professor Codes 👨‍🏫:

### Professor Class. 

```java
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


```

### Professor Controller.

```java
package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class ProfessorController {
    @FXML
    private TableView<Professor> professorTable;
    @FXML
    private TableColumn<Professor, String> nomColumn;
    @FXML
    private TableColumn<Professor, String> prenomColumn;
    @FXML
    private TableColumn<Professor, String> cinColumn;
    @FXML
    private TableColumn<Professor, String> adresseColumn;
    @FXML
    private TableColumn<Professor, String> telephoneColumn;
    @FXML
    private TableColumn<Professor, String> emailColumn;
    @FXML
    private TableColumn<Professor, Date> dateRecrutementColumn;
    @FXML
    private TableColumn<Professor, String> departementColumn;

  // Your TableView
    private Professor selectedProfessor;

    private final IMetier metier = new ImplMetier();
    private ObservableList<Professor> professors = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up table columns

        nomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prename"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateRecrutementColumn.setCellValueFactory(new PropertyValueFactory<>("date_recrutement"));
        departementColumn.setCellValueFactory(new PropertyValueFactory<>("dep"));

        // Load professors into the table
        loadProfessors();
    }

    private void loadProfessors() {
        List<Professor> professorList = metier.getProfessors();
        professors.setAll(professorList);
        professorTable.setItems(professors);
    }
    @FXML
    private void onAddProfessor(ActionEvent event) {
        Dialog<Professor> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un Professeur");
        dialog.setHeaderText("Veuillez remplir les informations du professeur.");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create fields for input
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Nom");
        TextField prenameField = new TextField();
        prenameField.setPromptText("Prénom");
        TextField cinField = new TextField();
        cinField.setPromptText("CIN");
        TextField addressField = new TextField();
        addressField.setPromptText("Adresse");
        TextField telephoneField = new TextField();
        telephoneField.setPromptText("Téléphone");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField dateRecrutementField = new TextField();
        dateRecrutementField.setPromptText("Date (dd-mm-yyyy)");
        TextField departmentField = new TextField();
        departmentField.setPromptText("Département");

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Prénom:"), 0, 1);
        grid.add(prenameField, 1, 1);
        grid.add(new Label("CIN:"), 0, 2);
        grid.add(cinField, 1, 2);
        grid.add(new Label("Adresse:"), 0, 3);
        grid.add(addressField, 1, 3);
        grid.add(new Label("Téléphone:"), 0, 4);
        grid.add(telephoneField, 1, 4);
        grid.add(new Label("Email:"), 0, 5);
        grid.add(emailField, 1, 5);
        grid.add(new Label("Date Recrutement:"), 0, 6);
        grid.add(dateRecrutementField, 1, 6);
        grid.add(new Label("Département:"), 0, 7);
        grid.add(departmentField, 1, 7);

        dialog.getDialogPane().setContent(grid);



        // Convert the result to a Professor object when the Add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    // Parse and format the date field input
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate localDate = LocalDate.parse(dateRecrutementField.getText(), formatter);

                    // Convert LocalDate to java.sql.Date for database compatibility
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

                    return new Professor(
                            nameField.getText(),
                            prenameField.getText(),
                            cinField.getText(),
                            addressField.getText(),
                            telephoneField.getText(),
                            emailField.getText(),
                            sqlDate, // Pass formatted date
                            departmentField.getText()
                    );
                } catch (DateTimeParseException e) {
                    showAlert("Erreur de saisie", "Veuillez entrer une date valide au format dd-MM-yyyy.");
                }
            }
            return null;
        });

        // Show dialog and capture the result
        dialog.showAndWait().ifPresent(professor -> {
            metier.addProfessor(professor); // Add professor to database
            loadProfessors(); // Refresh the table
        });
    }
    @FXML
    private void onProfessorSelected(MouseEvent event) {
        selectedProfessor = professorTable.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void onUpdateProfessor(ActionEvent event) {
        if (selectedProfessor == null) {
            // Show an alert if no professor is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un professeur à modifier.");
            alert.showAndWait();
            return;
        }

        // Create the dialog with editable fields for professor details
        Dialog<Professor> dialog = new Dialog<>();
        dialog.setTitle("Modifier Professeur");

        // Create the buttons
        ButtonType updateButtonType = new ButtonType("Mettre à jour", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create fields for editing professor details
        TextField nameField = new TextField(selectedProfessor.getName());
        TextField prenameField = new TextField(selectedProfessor.getPrename());
        TextField cinField = new TextField(selectedProfessor.getCin());
        TextField adresseField = new TextField(selectedProfessor.getAdresse());
        TextField telephoneField = new TextField(selectedProfessor.getTelephone());
        TextField emailField = new TextField(selectedProfessor.getEmail());
        TextField dateRecrutementField = new TextField();
        TextField depField = new TextField(selectedProfessor.getDep());

        if (selectedProfessor.getDate_recrutement() != null) {
            dateRecrutementField.setText(selectedProfessor.getDate_recrutement().toString());
        }

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                new Label("Nom:"), nameField,
                new Label("Prénom:"), prenameField,
                new Label("CIN:"), cinField,
                new Label("Adresse:"), adresseField,
                new Label("Téléphone:"), telephoneField,
                new Label("Email:"), emailField,
                new Label("Date de recrutement:"), dateRecrutementField,
                new Label("Département:"), depField
        );

        dialog.getDialogPane().setContent(vbox);

        // Set the result when the dialog is confirmed
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                // Create a new Professor object with updated values
                selectedProfessor.setName(nameField.getText());
                selectedProfessor.setPrename(prenameField.getText());
                selectedProfessor.setCin(cinField.getText());
                selectedProfessor.setAdresse(adresseField.getText());
                selectedProfessor.setTelephone(telephoneField.getText());
                selectedProfessor.setEmail(emailField.getText());
                try {
                    // Convert the input date string to java.sql.Date
                    String dateInput = dateRecrutementField.getText();
                    if (dateInput != null && !dateInput.isEmpty()) {
                        selectedProfessor.setDate_recrutement(java.sql.Date.valueOf(dateInput));
                    }
                } catch (IllegalArgumentException e) {
                    // Handle invalid date input
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Format de date invalide. Utilisez le format yyyy-MM-dd.");
                    alert.showAndWait();
                    return null;
                }

                selectedProfessor.setDepp(depField.getText());

                // Call the method to update the professor in the database
                metier.updateProfessor(selectedProfessor);

                return selectedProfessor;
            }
            return null;
        });

        // Show the dialog and wait for the result
        dialog.showAndWait();
        loadProfessors();

    }
    @FXML
    private void onDeleteProfessor(ActionEvent event) {
        // Get the selected professor from the table
        Professor selectedProfessor = professorTable.getSelectionModel().getSelectedItem();

        if (selectedProfessor != null) {
            try {
                // Delete the professor from the database using its ID
                metier.deleteProf(selectedProfessor.getCin());

                // Remove the professor from the table
                professorTable.getItems().remove(selectedProfessor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void onSearchProfessor(ActionEvent event) {
        // Create a dialog to get the keyword
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Recherche Professeur");
        dialog.setHeaderText("Rechercher un professeur");
        dialog.setContentText("Entrez le nom ou le prénom :");

        // Show the dialog and capture the result
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(keyword -> {
            // Search professors using the provided keyword
            List<Professor> matchingProfessors = metier.searchProfessors(keyword);

            if (matchingProfessors.isEmpty()) {
                showAlert("Aucun résultat", "Aucun professeur correspondant trouvé.");
            } else {
                // Display the results in the table
                professorTable.getItems().setAll(matchingProfessors);

            }
        });
    }
    @FXML
    private void loadProfessorsButton(ActionEvent event) {
        List<Professor> professorList = metier.getProfessors();
        professors.setAll(professorList);
        professorTable.setItems(professors);
        loadProfessors();
    }

    @FXML
    private void onAssignProfessorToDepartment(ActionEvent event) {
        // Ensure a professor is selected from the table
        Professor selectedProfessor = professorTable.getSelectionModel().getSelectedItem();

        if (selectedProfessor == null) {
            showAlert("Affectation échouée", "Veuillez sélectionner un professeur dans la liste.");
            return;
        }

        // Show dialog to get department ID
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Affecter un Département");
        dialog.setHeaderText("Affecter le Professeur à un Département");
        dialog.setContentText("Entrez l'ID du département :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(departmentIdString -> {
            try {
                int departmentId = Integer.parseInt(departmentIdString);

                // Call the method to update the professor's department
                metier.assignProfessorToDepartment(selectedProfessor.getId(), departmentId);

                showAlert("Succès", "Le professeur a été affecté au département avec succès.");

                // Reload the professor list to reflect changes
                loadProfessors();
            } catch (NumberFormatException e) {
                showAlert("Erreur", "L'ID du département doit être un nombre valide.");
            } catch (Exception e) {
                showAlert("Erreur", "Une erreur s'est produite lors de l'affectation.");
                e.printStackTrace();
            }
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional, removes header
        alert.setContentText(content);

        // Set a custom close button if needed
        ButtonType closeButton = new ButtonType("Fermer", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);

        // Show the alert and wait for user action
        alert.showAndWait();
    }

}

```

### Professor FXML.

```java
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.TableColumn?>
<?import javafx.scene.control.TableView?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.HBox?>

<AnchorPane prefHeight="400.0" prefWidth="600.0" xmlns:fx="http://javafx.com/fxml/1" xmlns="http://javafx.com/javafx/23.0.1" fx:controller="com.example.demo1.ProfessorController">

    <!-- Title Label -->
    <Label fx:id="professorLabel" text="Gestion des Professeurs" style="-fx-font-size: 20px; -fx-font-weight: bold;" AnchorPane.leftAnchor="10" AnchorPane.topAnchor="10" />

    <!-- TableView for Professors -->
    <TableView fx:id="professorTable" AnchorPane.leftAnchor="10" AnchorPane.rightAnchor="10" AnchorPane.topAnchor="40"  onMouseClicked="#onProfessorSelected" AnchorPane.bottomAnchor="60">
        <columns>
            <TableColumn fx:id="nomColumn" text="Nom" />
            <TableColumn fx:id="prenomColumn" text="Prénom" />
            <TableColumn fx:id="cinColumn" text="CIN" />
            <TableColumn fx:id="adresseColumn" text="Adresse" />
            <TableColumn fx:id="telephoneColumn" text="Téléphone" />
            <TableColumn fx:id="emailColumn" text="Email" />
            <TableColumn fx:id="dateRecrutementColumn" text="Date Recrutement" />
            <TableColumn fx:id="departementColumn" text="Département" />
        </columns>
    </TableView>

    <!-- Button Section -->
    <HBox spacing="10" AnchorPane.bottomAnchor="10" AnchorPane.leftAnchor="10">
        <Button fx:id="addProfessorButton" text="Ajouter Professeur" onAction="#onAddProfessor" />
        <Button fx:id="updateProfessorButton" text="Modifier Professeur" onAction="#onUpdateProfessor" />
        <Button fx:id="deleteProfessorButton" text="Supprimer Professeur" onAction="#onDeleteProfessor" />
        <Button fx:id="searchProfessorButton" text="Rechercher Professeur" onAction="#onSearchProfessor"/>
        <Button fx:id="assignProfessorButton" text="Affecter à Département" onAction="#onAssignProfessorToDepartment" />
        <Button fx:id="loadProfessorButton" text="Mise a jour" onAction="#loadProfessorsButton" />
    </HBox>

    <!-- Close Button -->
    <Button fx:id="closeProfessorTableViewButton" visible="false" text="Fermer" AnchorPane.bottomAnchor="10" AnchorPane.rightAnchor="10" />

</AnchorPane>

```


