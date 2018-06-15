#!/bin/bash
# Rong Tao 2018.1.16

clear

filename=("*.class" "*.pyc" "*.h.gch" "*.o" "*~" "*.out")
for name in ${filename[@]}; do
    #echo $name
    find  .  -name  $name  -type  f  -print  -exec  rm  -rf  {} \;
done

if [ $# -eq 0 ];then
    echo $*
fi

