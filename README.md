# Bicomat
Bicomat an e-banking solution, a school project developed by a team of students to validate the "Design project" teaching unit.

# Description
Bicomat is a virtual agency whose purpose is to enable bank customers to carry out banking transactions online via a website. It is a Java Enterprise application with an AngularJS front-end.

# Dependencies
- JDK 7 Update 11
- GlassFish Server 3.1.2.2
- MySQL5
- Apache maven 3.1

# Installation
```
$ git clone https://github.com/kafodome/bicomat-project.git
$ cd bicomat-project
$ mvn package
```
Before packaging, one may need to change MySQL Server configurations to reflect the one inteded to be used. The configuration file is located under ./src/main/setup/glassfish-resources.xml
```
<jdbc-connection-pool ...>
    <property name="URL" value="jdbc:mysql://localhost:3306/bicomat?zeroDateTimeBehavior=convertToNull"/>
    <property name="User" value="root"/>
    <property name="Password" value=""/>
</jdbc-connection-pool>
```
in glassfish-resources.xml:
- replace localhost:3306 with your MySQL Sever ip address and port
- provide User and Pasword if any.
Deploy the generated .war file located in ./target directory to your favorite application server. The application has been tested with Glassfish server 3.1 but may work as well on any java server application that has implemented Java EE 6.

# Execution
Once the application is successfully deployed, the application should be accessibles under the following url: http://localhost:8080/bicomat-project

#Author
@kafodome
