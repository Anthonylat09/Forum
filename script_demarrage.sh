#!/bin/bash

npm install --prefix ./forumfrontend


(cd ./hsqldb/lib && java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 forumBDD & cd ./forum && mvn spring-boot:run & cd ./forumfrontend && npm start)

