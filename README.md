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
# Foobar

Foobar is a Python library for dealing with word pluralization.

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage

```java
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
