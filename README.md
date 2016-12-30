# Modified Willie
This is the Server of [theassistant.io](https://theassistant.io), created with [Willie](https://glitter.jemoeders.website/biodiscus/Willie) in mind. 


## How to run it
There are multiple ways to run it, this are two that I regularly use

1) Maven (See: [How to run with Maven](#how-to-run-with-maven)

2) Eclipse (See: [How to edit](#how-to-edit)

## How to edit
1) Make sure you have the JRE 1.7 or higher, or download it from: [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

2) Make sure you have a MySQL database running, if not follow: [MySQL :: Getting Started with MySQL](http://dev.mysql.com/doc/mysql-getting-started/en/)

    * The project uses Hibernate, so as long as hibernate supports your Database it should be fine.
    
    * The configuration for hibernate is in the file: [MvcConfiguration.java](https://glitter.jemoeders.website/bartimeus/ModWillie/src/master/src/nl/itopia/modwillie/core/init/MvcConfiguration.java)
    
        * HIBERNATE_DIALECT = The library that communicates with the Database
        
        * DATABASE_URL = The url to connect to: ```jdbc:<DATABASE>://<DATABASE_HOST>:<PORT>/<TABLE>```
        
        * DATABASE_USERNAME = The username that can be used to connect to the database
        
        * DATABASE_PASSWORD = The password that can be used to connect to the database
        
3) Change HIBERNATE_HBM2DDL_AUTO  in [MvcConfiguration.java](https://glitter.jemoeders.website/bartimeus/ModWillie/src/master/src/nl/itopia/modwillie/core/init/MvcConfiguration.java) to \'create\' for the first time, after running the application for the first time change it back to: \'update\'

4) Clone the project to a desired location, 
```git clone git@glitter.jemoeders.website:biodiscus/Willie.git```

5) Download Eclipse Java EE ([Link](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2))

6) Follow step 1 at: [Get started with a Hello World app on Liberty using WebSphere Developer Tools for Eclipse](https://developer.ibm.com/wasdev/docs/developing-applications-wdt-liberty-profile/)

7) Open Eclipse
    1) File > Import > Maven > Existing Maven Projects
    2) Press the \'Next\' button
    3) Select the directory ModWillie got cloned to
    4) Press the \'Finish\' button
    
8) Right mouse on the project: \'ModWillie\'

9) Run As > Run on Server

10) Select the option: \'Manually define a new server\'

11) Server type: IBM > WebSphere Application Server Liberty

12) The server's host name: \'localhost\'

13) The server's name: \'localhost\'

    * The checkbox: \'Always use this server when running this project\' is optional and is up to you if you want that behaviour.
    
14) Press the \'Next\' button

15) Name: \'localhost\'

16) \'How do you want to create the runtime environment?\', select the option: \'Install from an archive or a repository\'

17) Press the \'Next\' button

18) \'Enter a destination path: \', select a path where the server will be downloaded to

19) Select the option: \'Download and install a new runtime environment from ibm.com:\'

20) Select the item: \'WAS Liberty with Java EE 7 Full Platform\' 

21) After Eclipse is done with downloading the license, press the \'Next\' button

22) After the download sites have been accessed, press the \'Install\' button on \'Java EE 7 Full Platform Bundle\'

23) Press the \'Next\' button

24) \"Read the license\" and accept it

25) Press the \'Next\' button

26) Leave the settings and press the \'Finish\' button

27) The server will be downloaded

28) Choose a \'Keystore password\' and unselect the option: \'Add basic user registry\'
    1) Press the \'Set...\' button
    2) Enter a password
    3) Press the \'OK\' button
    4) Press the \'OK\' button
    
29) ModWillie will than be available at: ```http://localhost:9080/ModWillie/```

## How to run with Maven
1) Make sure you have the JRE 1.7 or higher, or download it from: [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

2) Make sure you have Maven2 installed, or follow: [Installing Apache Maven](http://maven.apache.org/install.html)

3) Make sure you have a MySQL database running, if not follow: [MySQL :: Getting Started with MySQL](http://dev.mysql.com/doc/mysql-getting-started/en/)

    * The project uses Hibernate, so as long as hibernate supports your Database it should be fine.
    
    * The configuration for hibernate is in the file: [MvcConfiguration.java](https://glitter.jemoeders.website/bartimeus/ModWillie/src/master/src/nl/itopia/modwillie/core/init/MvcConfiguration.java)
    
        * HIBERNATE_DIALECT = The library that communicates with the Database
        
        * DATABASE_URL = The url to connect to: ```jdbc:<DATABASE>://<DATABASE_HOST>:<PORT>/<TABLE>```
        
        * DATABASE_USERNAME = The username that can be used to connect to the database
        
        * DATABASE_PASSWORD = The password that can be used to connect to the database
        
4) Change HIBERNATE_HBM2DDL_AUTO  in [MvcConfiguration.java](https://glitter.jemoeders.website/bartimeus/ModWillie/src/master/src/nl/itopia/modwillie/core/init/MvcConfiguration.java) to \'create\' for the first time, after running the application for the first time change it back to: \'update\'

5) Clone the project to a desired location, 
```git clone git@glitter.jemoeders.website:biodiscus/Willie.git```

6) Download [WAS Liberty](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-non-eclipse-environments/)

7) Execute the command: ```mvn clean install```

8) With the created WAR file, follow: [Deploying applications in Liberty](http://www.ibm.com/support/knowledgecenter/SS7K4U_liberty/com.ibm.websphere.wlp.zseries.doc/ae/twlp_dep.html)