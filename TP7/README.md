# TP 7: Genericity and inputs outputs
***

# Exercise 1 ☠️:
---
`Overview`: In the first exercise, we want to develop a basic program that will give all the information related to the files and directories. The requirements for this exercise are to show the `PATH` and the `type`, as well as the permissions for the target elements.

**This is an example of the output :**

```bash
..\xampp\htdocs\tp1\index.php <FICH> rw-
..\xampp\htdocs\tp1\accueil.htm <FICH> rw-
..\xampp\htdocs\tp1\images < DIR > rw-
```

---

## ▶️ Let's Code
---
### Class showProperities.
---

![image](https://github.com/user-attachments/assets/b908f276-1cf7-4bdc-975e-f5c161421558)

I Will describe these three methods in the figure above,
the code are in the folders of this repo, i describe just the logic of the methods for better understanding.

**Main** :
In this method where I run all the programs, I made a scanner for the input user to enter the name of a file, as well I also made a `File` input to create a new file in the root of the directory. I write if-else statements to implement the logic of permissions and differentiate between the directory and the files, finally, I made a declaration of the function for multiple directories.

**traverseDirectory** :
This method is for handling the case of multiple directories, it's very simple, it iterate through the directories and output each file each directory.

**getPermissions** : 
This method it's so easy, just get the permissions and output it.

---

## Scenario Output.
---
![image](https://github.com/user-attachments/assets/b3063cd6-0645-4fd2-b542-8a8a3b266994)

I made many directories and files inside this directory to see all the cases .



***

# Exercise 2️⃣:
---
`Overview` : In this exercise, we want to make a simple code for managing the contacts of some clients, their names, and numbers.

---
## ▶️Let's code.
---

### DossierContact.
---

![image](https://github.com/user-attachments/assets/82967bcb-0810-4bb9-96fb-2fe9840bc23f)

I will explain the code in the above figure. 

**ajouterContact**
In this method i made the logic for adding a contact to the list of contacts (I created a contacts Map to made Key as name and Value as number), and also make a save for saving the file in the root directory.

**supprimerContact**
This method handles the case where a user wants to delete some contact, i search in the contacts , if i found the name i send the method Delete contact for deleting the file also !

**rechercherContact**
The search is simple, finding the Key in contacts list and show it when it's found !

**changerNumero**
For this method i search for the target send the changes to the saveContactTofile for updating the necessary information.


**loadContactsFromFiles**
This is a crucial method for loading the numbers from the files that are saved or created by users, and finally we add the numbers to the contacts list.

**saveContactToFile**
This is for saving the informations to a file using the Writer functionality.

**deleteContactFile**

This is the last method, where I search for the target file and delete it if it exists.

**Main**
The main method is for handling the cases and I make switch for see the cases.

## Scenario Output.
---
*This is the directory for storing the contacts :* 

![image](https://github.com/user-attachments/assets/46529e5c-4258-4492-a1b0-71b2226344e4)


*This is the Menu*

![image](https://github.com/user-attachments/assets/c9073d44-7cc2-4537-9776-fa96fc08dac9)
![image](https://github.com/user-attachments/assets/addf56db-d86c-40bc-b824-094cf73f32dc)

We see the contact of Mestari has been added successfully to the directory.


*The case of searching the Number of some contact*

![image](https://github.com/user-attachments/assets/a20a7abe-125a-44c5-bcd4-3726707db379)

*The case of deleted contact*

![image](https://github.com/user-attachments/assets/fa923495-7ca1-4922-adc1-b4d8eb0867e0)


*The case where we want to update some contact*

![image](https://github.com/user-attachments/assets/186d17e6-808f-49a9-b4ed-1cef2853168c)

we see the changement.

![image](https://github.com/user-attachments/assets/b8e0fac4-2b15-40c4-bd85-294bded6ac23)


***

# Exercise 3 3️⃣:
---
`Overview`: In this exercise, we want to make a program for uses the generic feature of java, to make the simplicity of coding.




