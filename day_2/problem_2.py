#!/usr/bin/python3
# Author: jtMengel
# Date  : 2018-12-06
# About : https://adventofcode.com/2018/day/2
#         For a given input file, we're going to make a cheap checksum per the
#         contents of the input lines ("box IDs"), by the manner of counting 
#         the number of "box IDs" that have at least 1 number repeating exactly
#         twice (call that 'int_2'), those that have at least 1 number repeated
#         thrice in-line (call that 'int_3') and taking the product of those 
#         numbers ('int_2 * int_3').
#         Second objective, using the same input, we are going to search for 2
#         box IDs which are different only by a single character. The expected
#         output will be the remaining, matching characters (still ordered).
# Usage : Provide this script with an argument for the file to read as input
import sys

int_2=0
int_3=0
with open(sys.argv[1], 'r') as my_file:
  for cnt, line in enumerate(my_file):
    (box_id, done_2, done_3)=(line.rstrip(), False, False)
    for c in set(box_id):
      f=box_id.count(c) # Frequency
      if not done_2 and f==2: done_2=True
      if not done_3 and f==3: done_3=True
      if done_2 and done_3: break
    int_2+=done_2
    int_3+=done_3
  
print( ":Checksum:\n%s\n" % (int_3*int_2) )

with open(sys.argv[1], 'r') as my_file:
  lines=my_file.readlines()
  lines.sort()
  lines_len=len(lines)
  for i, line in enumerate(lines):
    if (i+1) > lines_len: break
    box_id=list( line.rstrip() )
    box_id_next=list( lines[i+1].rstrip() )
    delta=0
    matches=[]
    for j, id_ch in enumerate(box_id):
      id_next_ch=box_id_next[j]
      if id_ch is not id_next_ch: delta+=1
      else: matches.append(id_ch)

      if delta > 1: break
    if delta < 2: 
      print( ":Matching box ids:\n%s\n" % ( "".join(matches) ) )
      break

