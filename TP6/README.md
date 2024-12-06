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

*For avoiding the overwealming the codes are in a directories and also the FXMLs and all required stuff.*

***

# Screens of Real scenario.
---
**After click on run, we see this screen shows up, it's the main screen of the project, where we have two choices, 
go to professor operations or department operations.**

![image](https://github.com/user-attachments/assets/f0c115b6-b62e-4305-8cc1-44a634409ad9)


**After the decision of choosing the professors operations the program go from FXML of the main interface into the FXML of professors,
as we can see in the figure, it shows a tableview with pre-defined database (it s the basic example I can give for better understanding !) with the columns and the names etc ... .
Also we see some buttons for the configuration and do some changements.
I will resume the work in this sentence : I will test them all !**

---
## Professors

### AddProfessor.
When we click on addProfessor button this dialog appear and you must insert some information, i will share figure and we pass to the next button.

![image](https://github.com/user-attachments/assets/338af845-13f0-4252-8d6f-ef723eea4619)


### UpdateProfessor.
This button depend on the selector professor, in better explanation, if you want to update some professor just click on it and then click on the button and enter the updates that you love.

![image](https://github.com/user-attachments/assets/94b77faa-01c5-4651-9617-f1073bb2812d)


### DeleteProfessor.
The delete button also use the feature of selector, when you select a professor and click on delete, it's deleted immediately.

*As we see I delete hoda from the table !*

![image](https://github.com/user-attachments/assets/ccdbe325-5b57-4d54-a711-36e3d5173f4b)

### SearchForprofessor.
After click on the button, a dialog shows up with a input name for intering the search target.

![image](https://github.com/user-attachments/assets/9a66de40-6b71-4c37-bcbf-3384875b5e07)


### AffectDepartment
For this functionality, I made my thinking to affect departments after seeing the departments available and assigning by Id, I don't use names cause it's easy.

![image](https://github.com/user-attachments/assets/2b7c98d2-8fad-41fe-a9f3-bcb142e570cd)

### Update.
The last button it's just for update tableview (It's automatically , but in the case where you see something fixed you can click on it).

![image](https://github.com/user-attachments/assets/8ac4ca39-2f22-404e-acab-cf7c45725c26)

---
## Departements

In the first place, we see this table view by default show all departements those are available.

![image](https://github.com/user-attachments/assets/98e0b5ba-dcf5-410d-9648-02f834bc7293)

### adddepartements
As we can see after clicking on the button add he shows this dialog to enter some department.

![image](https://github.com/user-attachments/assets/e5467303-b27c-4ac7-81eb-ba1f3a8d0e3e)


### Updatedepartement
In this case also we depend on the selector feature to change the name, This is the figure that shows after click on the button.

![image](https://github.com/user-attachments/assets/0c277933-c9f6-43ec-bf5c-2952baf8aa7b)


### Deletedepartement
This is also depending on the selector to delete a department, we see on the figure Math departement are deleted : 

![image](https://github.com/user-attachments/assets/c17c795d-755b-406b-aecf-4d224fa3c338)

### ShowProfessors
This is the last button where we want to see professors for a specific department, in the scenario we click on IT and then click on showprofessors and we got those professors : 

![image](https://github.com/user-attachments/assets/20e22ed2-7538-4d8e-b940-863d3696b52a)


***



