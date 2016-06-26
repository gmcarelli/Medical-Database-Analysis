# Medical Database Analysis Framework

This framework aims at providing a unified way to analysis the performance of relational and non-relational database systems for dealing with medical images.

For now, this framework supports only Java technology and the PostgreSQL, Neo4J, and MongoDB database systems.

### Installation

To install on linux it is required [ant](http://ant.apache.org/), [Java Development Kit (>= 1.7)](http://openjdk.java.net/), [PostgreSQL](https://www.postgresql.org/), [MongoDB](https://www.mongodb.com/), and [Neo4J](https://neo4j.com/).

```
sudo wget https://raw.githubusercontent.com/gmcarelli/medical-database-analysis/master/install.sh
sudo chmod +x ./install.sh
sudo ./install.sh 
```

### Getting Started

To run some tests execute

```
cd /opt/ifsp/medical-images-analysis
#
# Analysis postgresql sql using username "postgres" with password "postgres". The test uses n images to be persisted sequentially with the same connection. 
#
./performance-analysis.sh pgsql postgres postgres -p \ 
		1 "image-1-name" "path/to/image-1" ... n "image-n-name" "path/to/image-n"
```

#### Authors
* Gil Carelli (gilcarelli@gmail.com)
* Lucas Venezian Povoa (lucasvenez@gmail.com)

