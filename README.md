# Medical Database Analysis Framework

This framework aims at providing a set of resource to analysis the persistence of relational and non-relational database systems for dealing with medical images.

For now, this framework support only Java technology and the database systems postgreSQL, Neo4J and MongoDB.

### Install

To install on linux it is required ant, jdk1.7, postgresql-server, mongodb-server, and neo4j-server installed.

```
wget https://raw.githubusercontent.com/gmcarelli/medical-database-analysis/master/install.sh
sudo ./install.sh 
```

### Getting Started

To run some tests execute

```
cd /opt/ifsp/medical-images-analysis
./performance-analysis.sh pgsql postgres postgres
```

#### Authors
* Gil Carelli (gilcarelli@gmail.com)
* Lucas Venezian Povoa (lucasvenez@gmail.com)

