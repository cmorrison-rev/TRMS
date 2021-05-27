# TRMS
The Tuition Reimbursement System, TRMS, allows users to submit reimbursements for courses and training. The submitted reimbursement must be approved by that employee's supervisor, department head, and benefits coordinator. The benefits coordinator then reviews the grade received before finalizing the reimbursement.

## Technologies
- Java - SE1.8
- AWS Java SDK - 1.11.1004
- Apache Log4J - 2.11.2
- Javalin - 3.13.4
- Jackson - 2.10.3
- DataStax - 4.11.0

## Features Implemented
- [X] Add employees with different access levels and restrictions
- [X] Login/Logout for user access, unregistered user access provided in some instances
- [X] Add reimbursement forms with differing urgency for date/time priority
- [X] Upload and retrieve documents to AWS S3 bucket to provide proof of approval
- [X] Auto reimbursement amount adjustment upon form submission
- [ ] Approval heirarchy chain between administrative roles
- [ ] File validation and tethering to submission forms

## Setup
`git clone https://github.com/cmorrison-rev/TRMS.git`   

### Create a keystore (AWS specific) within `src/main/resources`
```bash
curl https://certs.secureserver.net/repository/sf-class2-root.crt -O  
openssl x509 -outform der -in sf-class2-root.crt -out temp_file.der  
keytool -import -alias cassandra -keystore cassandra_truststore.jks -file temp_file.der  
```
### Set environment variables for the Driver.class run configuration
```
AWS_USER - Username for aws keyspaces-specific credentials  
AWS_PASS - Password for aws keyspaces-specific credentials  
TRUSTSTORE_PASS - Password used for the created truststore  
```
## Contributing
Contributions are closed as this is a private company project.

Please feel free to suggest any changes :)
