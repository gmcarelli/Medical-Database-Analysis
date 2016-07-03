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
operation=$5
imagesDirectory=$6

#
# Setting database admin command
#
databaseadmin=""

case "$1" in
pgsql)
   databaseadmin="/etc/init.d/postgresql"
   ;;
mongo)
   databaseadmin="/etc/init.d/mongdb"
   ;;
neo4j)
   databaseadmin="/etc/init.d/neo4j"
   ;;
*)
   echo "Usage deploy.sh <database-system> <username> <password> <number-of-tests>"
   exit 1
esac
#
#
#
i=1
images=""
if [[ -d $imagesDirectory ]]; then
   for file in $imagesDirectory/*; do
      if [[ -f $file ]]; then
         if 
         images="$images $i $(basename $file) $file"
         i=$((i+1))
      fi
   done	
fi

echo $images
exit 0
#
# Creating file name
#
file="$database-times.csv"
#
# Executing tests 
#
for i in `seq 1 $numberOfTests`; do
   # 
   # Clearning memory and swap before execute tests
   # Script indicated at http://www.tecmint.com/clear-ram-memory-cache-buffer-and-swap-space-on-linux/
   #
   echo 3 > /proc/sys/vm/drop_caches && swapoff -a && swapon -a && printf '\n%s\n' 'Ram-cache and Swap Cleared'
   #
   # Restating database server
   #
   $databaseadmin stop
   $databaseadmin start
   #
   # Running application 
   #
   t=`time java -jar mda.jar $database MEDICALIMAGE $username $password $operation $images'
   echo "$database;$numberOfTests;$operation;$numberOfImages;$t"
done
