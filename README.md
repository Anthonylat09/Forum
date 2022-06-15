# Procédure de lancement du projet

Ce projet a été créé en utilisant les technologies HyperSQL Database, Spring Boot et React.js. Il s'agit de l'implémentation d'un forum de discussion, permettant aux utilisateurs de créer des catégories et des sujets, et d'y envoyer des messages. <br>

Pour lancer le projet vous avez besoin d'avoir les logiciels suivant installés: <br>
### java
$ sudo apt install default-jre
### npm 
$ sudo apt install npm
### maven
Suivez ce tutoriel [maven](https://www.journaldev.com/33588/install-maven-linux-ubuntu)

Ceci fait, vous pouvez soit utiliser le script de démarrage en lançant la commande suivant : <br>

$ ./script_demarrage.sh

Vous pouvez également suivre la procédure ci-dessous:

## Lancement de la lase de données

Pour se faire, il faut avoir au préalable téléchargé le .zip disponible sur le site officiel de hsqldb à l'adresse suivante: 
[hsqldb](https://hsqldb.org/) <br>
Après avoir décompréssé le dossier, naviguer vers le dossier lib puis lancer la commande suivante: <br>
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 forumBDD

La base de données est ainsi lancée

## Lancement de la partie Backend

Pour se faire, il faut ouvrir le dossier [forum](https://gitlab.com/Anthonylat09/forum-de-discussion/-/tree/main/forum) du projet avec un IDE java (Eclipse IDE ou IntelliJ par exemple).

Ensuite, aller dans le build path du projet et rajouter les maven dependancies au Classpath ainsi que JRE-System-Library[JavaSE-11] au Modulepath.

Il suffit ensuite de lancer le fichier ForumApplication.java situé [ici](https://gitlab.com/Anthonylat09/forum-de-discussion/-/tree/main/forum/src/main/java/com/devteam/forum) .

Et la c'est fait pour la partie Back.


## Lancement de la partie Frontend

Pour cette partie, il faut ouvrir le dossier [forumfrontend](https://gitlab.com/Anthonylat09/forum-de-discussion/-/tree/main/forumfrontend) avec un éditeur comme VSCode, puis:

### npm install 
pour installer les dépendances nécessaires
(installer les dépendances qui nécessiteraient une installation à part)

### npm start
pour lancer effectivement la partie Frontend


### Et vous pouvez maintenant tester l'application comme bon vous semble

