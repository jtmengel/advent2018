#!/bin/bash
# Author: jtMengel
# Date  : 2018-12-05
# About : https://adventofcode.com/2018/day/1
# 				For a given input file, take the total final sum of the file's values
total=0

while read val;
do
  total=$(($total $val)) 
done < $1

echo $total
