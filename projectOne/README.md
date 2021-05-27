# TRMS
Tuition Reimbursement Management System RESTful API. Employees can submit a reimbursemnt request and that request will be passed along and approved by the appropriate positions.

## Technologies
- Java 8
- Javalin
- AWS Keyspaces
- Datastax
- AWS S3
- Spring

## Getting Started
git clone 
Create a truststore for keyspaces
run mvn package within the repo directory
Set environment variables
AWS_USER - Username for aws keyspaces-specific credentials
AWS_PASS - Password for aws keyspaces-specific credentials
TRUSTSTORE_PASS - Password used for the created truststore
copy target/trms.jar and your truststore to the same dir
