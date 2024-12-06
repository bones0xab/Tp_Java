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
