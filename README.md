# Medical Database Analysis Framework

This framework aims at providing a unified way to analysis performance of relational and non-relational database systems for dealing with medical images.

For now, this framework supports only Java technology and the PostgreSQL, Neo4J, and MongoDB database systems.

### Installation

To install on linux it is required [ant](http://ant.apache.org/), [openjdk-7-jdk](http://openjdk.java.net/), git.

```
apt-get install ant git openjdk-7-jdk
wget https://raw.githubusercontent.com/gmcarelli/medical-database-analysis/master/install.sh
chmod +x ./install.sh
./install.sh 
```

### Getting Started

To run some tests execute (it is required database management systems installed)

```
cd /opt/ifsp/
#
# Analysing PostgreSQL Database using the username "postgres" with password "postgres". 
# The test uses n images to be persisted sequentially with the same connection. 
#
./performance-analysis.sh pgsql postgres postgres -p \ 
		1 "image-1-name" "path/to/image-1" ... n "image-n-name" "path/to/image-n"
```

#### Authors
* Gil Carelli (gilcarelli at gmail dot com)
* Lucas Venezian Povoa (lucasvenez at gmail dot com)

#### License

[GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)

This code was not widely tested. Thus, unpredictable behaver is a possibility. Its usage is user's responsibility only. Pay attention when running this code, since insert, delete, update operations could occur in your database management system without previous warning.
