# TP 6
---

### MCD
---
![image.png](assets/image.png)


### MLD

Professeur Table
Column	Type	Constraints
id_prof	INT	PRIMARY KEY
nom	VARCHAR	NOT NULL
prenom	VARCHAR	NOT NULL
cin	VARCHAR	NOT NULL, UNIQUE
adresse	VARCHAR	
telephone	VARCHAR	
email	VARCHAR	NOT NULL, UNIQUE
date_recrutement	DATE	
id_depart	INT	FOREIGN KEY -> Departement.id_depart
Departement Table
Column	Type	Constraints
id_depart	INT	PRIMARY KEY
nom	VARCHAR	NOT NULL
