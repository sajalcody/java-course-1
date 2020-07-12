#!/bin/bash

echo " ----------- Cleaning class files ------------"
find . | grep -v git | grep .class | xargs rm
# rm "**/*.class" -> Not Working
echo " ----------- Cleaning Bluej files ------------"
# rm **/*.ctxt
find . | grep -v git | grep .ctxt | xargs rm