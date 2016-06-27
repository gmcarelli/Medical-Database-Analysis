# Medical Database Analysis Framework

This framework aims at providing a unified way to analysis the performance of relational and non-relational database systems for dealing with medical images.

For now, this framework supports only Java technology and the PostgreSQL, Neo4J, and MongoDB database systems.

### Installation

To install on linux it is required [ant](http://ant.apache.org/), [openjdk-7-jdk](http://openjdk.java.net/), git.

```
apt-get install ant, got, openjdk-7-jdk
wget https://raw.githubusercontent.com/gmcarelli/medical-database-analysis/master/install.sh
chmod +x ./install.sh
./install.sh 
```

### Getting Started

To run some tests execute (it is required database management systems installed)

```
cd /opt/ifsp/medical-images-analysis
#
# Analysing PostgreSQL Database using the username "postgres" with password "postgres". 
# The test uses n images to be persisted sequentially with the same connection. 
#
./performance-analysis.sh pgsql postgres postgres -p \ 
		1 "image-1-name" "path/to/image-1" ... n "image-n-name" "path/to/image-n"
```

#### Authors
* Gil Carelli (gilcarelli@gmail.com)
* Lucas Venezian Povoa (lucasvenez@gmail.com)

#### License

[GPLv3](https://www.gnu.org/licenses/gpl-3.0.html)

This code was not widely tested. Thus, unpredictable behaver is a possibility. The usage of this is only user's responsibility. Pay attention when running this code, once insert, delete, update operations could occur in your database management system without previous warning.