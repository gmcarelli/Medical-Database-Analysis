#
# Getting medical-database-analysis application from GitHub
#
cd /opt
if [ ! -d "ifsp" ]; then # if folder ifsp does not exits
   mkdir ifsp	
fi
cd ifsp
git clone https://github.com/gmcarelli/medical-database-analysis.git
#
# Compiling source code
#
cd medical-database-analysis
ant
cd ..