# TRMS
Tuition Reimbursement Management System RESTful API. Employees can submit a reimbursemnt request and that request will be passed along and approved by the appropriate positions.

## Technologies
- Java - SE1.8
- AWS Java SDK - 1.11.1004
- Apache Log4J - 2.11.2
- Javalin - 3.13.4
- Jackson - 2.10.3
- DataStax - 4.11.0

## Getting Started
`git clone  `   
Create a truststore for keyspaces  
run mvn package within the repo directory  
Set environment variables  
```
AWS_USER - Username for aws keyspaces-specific credentials  
AWS_PASS - Password for aws keyspaces-specific credentials  
TRUSTSTORE_PASS - Password used for the created truststore  
```
copy target/trms.jar and your truststore to the same dir  
