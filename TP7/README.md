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

***
## ▶️ Let's Code

### Class showProperities.
---

![image](https://github.com/user-attachments/assets/b908f276-1cf7-4bdc-975e-f5c161421558)

I Will describe these three methods in the figure above,
the code are in the folders of this repo, i describe just the logic of the methods for better understanding.

*Main* :
In this method where I run all the programs, I made a scanner for the input user to enter the name of a file, as well i made a `File` input to create a new file in the root of the directory. I write if-else statements to implement the logic of permissions and differentiate between the directory and the files. 

