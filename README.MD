The main goal of the project is to create a website with java and test it with penetrasion tests. 

The project is written with java, using Tomcat server and mysql as database.  
The project uses sha-1 and salt for saving encrypted data into the database. 
More over you can look in to the next fiels: presentation,DREAD model, Vulnerability for more details and explanations.


# How to set up the project

*Note: follow this steps only if you choose to use the vagrant development box: https://github.com/nati-elmaliach/vagrant-java-development-server, if not the states may differ. 

After running `vagrant up` you have 2 folder in Desktop: 
- Tomcat
- Communication

# Steps

*Tomcat configuration
- open eclipse -> Window -> show view -> servers
- click on the console `No servers are...` -> choose Apache -> Tomcat v7.0 and click next
- click Browse -> Desktop/Tomcat/apache-tomcat-7.0.103 -> click ok and finish
- Right click on the Tomcat v7.0(on the console) -> properties -> Switch Location -> Apply && ok  
- Double click again on the Tomcat v7.0 -> choose `Use Tomcat installation` -> save && close the window

*Project configuration
- click on file -> import -> General -> Existing Projects... -> next
- click Browse -> Desktop/Communication -> ok && finish
- Right click on the project -> Run as -> Run on server -> choose Tomcat && finish
- validate you can navigate between pages


