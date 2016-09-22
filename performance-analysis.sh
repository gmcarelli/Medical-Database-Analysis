#!/bin/bash
#
# It is a script at aiming to deploy the medical-database-analysis application 
# and setup the environment to run experiment to analysis the persistence and 
# retrieval performance of relational and non relational database systems
# into a Unix-like operating system. How to use:
#
# performanceanalysis.sh <database-system> <username> <password> <number-of-tests> <operation>
# <database-system> is the database system, which may be defined as: pgsql, mongo, or neo4j
# <username> is the username of the database system
# <password> is the user password of the database system
# <number-of-test> is the number of test to be executed sequentially 
#
# Getting parameters
#
database=$1
username=$2
password=$3
numberOfTests=$4
operation=$5
imagesDirectoryOrImagesId=$6
time="/usr/bin/time"
#
# Setting database admin command
#
databaseadmin=""
#
#
#
case "$database" in
pgsql)
   databaseadmin="/etc/init.d/postgresql"
   ;;
mongo)
   databaseadmin="/etc/init.d/mongodb"
   ;;
neo4j)
   databaseadmin="/etc/init.d/neo4j"
   ;;
*)
   echo "Usage performance-test.sh <database-system> <username> <password> <number-of-tests> <operation> <imagepathorids>"
   exit 1
esac
#
# Checking operation
#
case "$operation" in
-p)
   ;;
-r)
   ;;
*)
   echo "Operation $operation not supported"
   exit 1
esac
#
#
#
numberOfImages=0
i=1
images=""
if [ $operation = "-p" ]; then
	if [[ -d $imagesDirectoryOrImagesId ]]; then
    	for file in $imagesDirectoryOrImagesId/*; do
        	if [[ -f $file ]]; then
            	images="$images $i $(basename $file) $file"
    			i=$((i+1))
    			numberOfImages=$((numberOfImages+1))
        	fi
    	done	
	fi
fi
#
#
#
if [ $operation = "-r" ]; then
	images=$imagesDirectoryOrImagesId;
	IFS=' ' read -r -a array <<< "$images"
	numberOfImages=${#array[@]}
fi
#
# Creating file name
#
file="database-performance.csv"
#
# Executing tests 
#
for i in `seq 1 $numberOfTests`; do
	#
	#
	#
	echo "Executing test $i of $numberOfTests"
   	# 
   	# Clearing memory and swap before execute tests
   	# Script indicated at http://www.tecmint.com/clear-ram-memory-cache-buffer-and-swap-space-on-linux/
   	#
	echo 3 > /proc/sys/vm/drop_caches && swapoff -a && swapon -a
   	#
   	# Restating database server
   	#
	$databaseadmin stop
	$databaseadmin start
   	#
   	# Running application 
   	#
	$time -o time.txt -f %U,%S java -jar mda.jar $database MEDICALIMAGE $username $password $operation $images
	#
	#
	#	 
	t=`cat time.txt`
	#
	#
	#
	ope=""	
	if [ "$operation" = "-p" ]; then
		ope="persistence"
	else
		ope="retrieve"
	fi
	#
	#
	#
	echo "$database,$numberOfTests,$ope,$numberOfImages,$t" >> $file
done
