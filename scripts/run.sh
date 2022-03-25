echo "Compiling...";

cd ../;

javac ./src/br/uffs/cc/jarena/*.java ./src/br/uffs/cc/jarena/renders/simple2d/*.java -d bin/;

echo "Running...";

cd ./bin/;
java br.uffs.cc.jarena.Main;