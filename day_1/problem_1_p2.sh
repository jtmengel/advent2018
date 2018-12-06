#!/bin/bash
# Author: jtMengel
# Date  : 2018-12-05
# About : https://adventofcode.com/2018/day/1#part2
# 				For a given input file, sum the file's values until you reach a sum
#					value which you have reached previously
# 				This would have been so much easier in Python.
total=0
past=(0)
_past=(0) # negative past because you can't have negative indexes
inc=0
final=""

if [ -z "$1" ] || [ ! -f "$1" ] ; then exit 1; fi
# You're required to provide an arg for the file to input, second arg optional
file="$1"
verbose="$2"

function success_action(){
  echo "$1 (found after $inc loops)"
  exit 0
}

# Helper functions (assumes array 'past' exists)
function contains(){
  # Hoverboards don't work on water unless you've got power
  # And bash arrays don't take negative indexes/'subscripts'; give special 
  #  negative array for that and cast the index to positive form
  if [ $1 -lt 0 ]; then
    x="$(echo $1 | sed 's/-//g')"
    if [ ${_past[$x]} ]; then success_action $1 ; else _past[$x]=1 ; fi
  else
    if [ ${past[$1]} ]; then success_action $1 ; else past[$1]=1 ; fi
  fi
}

# Let's actually do some work now
# (and how slow and naive we will work...)
# We're clearly going to ignore the fact that each iteration
while true; 
do
  inc=$(($inc + 1)) 
  while read val;
  do
    total=$(($total $val)) 
    contains $total
  done < $1
  echo "Completed loop $inc - sum concluded at '$total'"
done
