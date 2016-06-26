#!/bin/bash
#
# It is a script at aming to deploy the medical-database-analysis application 
# and setup the envoriment to run experiment to analysis the persistence and 
# retrievement performance of relational and no relational database systems
# into a Unix-like operating system. How to use:
#
# sudo deploy.sh <database-system> <username> <password> <number-of-tests>
# <database-system> is the database system, which may be defined as: pgsql, mongo, or neo4j
# <username> is the username of the database system
# <password> is the user password of the database system
# <number-of-test> is the number of test to be executed sequentily 
#
# Getting parameters
#
database=$1
username=$2
password=$3
numberOfTests=$4
#
# Setting database admin command
#
databaseadmin=""

case "$1" in
pgsql)
   databaseadmin="/bin/pg_ctl"
   ;;
mongo)
   databaseadmin="/bin/mongod"
   ;;
neo4j)
   databaseadmin="/bin/neo4j"
   ;;
*)
   echo "Usage deploy.sh <database-system> <username> <password> <number-of-tests>"
   exit 1
esac
#
# Executing tests 
#
for i in `seq 1 $n`; do
   # 
   # Clearning memory and swap before execute tests
   # Script indicated at http://www.tecmint.com/clear-ram-memory-cache-buffer-and-swap-space-on-linux/
   #
   echo 3 > /proc/sys/vm/drop_caches && swapoff -a && swapon -a && printf '\n%s\n' 'Ram-cache and Swap Cleared'
   #
   # Restating database server
   #
   `$databaseadmin` stop
   `$databaseadmin` start
   #
   # Running application 
   #
   java -jar medical-database-analasys.jar > "`date +%Y%m%d%H%M%S`-$database-$numberOfTests.csv"
done
