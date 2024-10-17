#!/bin/bash

if [ $# -lt 3 ]; then
  echo "$0: Missing parameters"
  echo "Usage:"
  echo "  1 > Log Directory"
  echo "  2 > Source File (absolute path to transfer)"
  echo "  3 > Destination Directory (local path)"
  exit 1
fi

# Debugging output
echo "Log Directory: $1"
echo "Source File: $2"
echo "Destination Directory: $3"

# Transfer File
srcFile="$2"
destDir="$3"

# Use cp command for local transfer with full path
#/bin/cp "$srcFile" "$destDir"
/usr/bin/cp "$2" "$3"
retCD=$?
if [ $retCD -eq 0 ]; then
  echo "File transferred successfully."
else
  echo "File transfer failed with exit code: $retCD"
fi
