#!/bin/bash

find . -iname '*.class' -exec rm -f {} \;
find . -iname '*~' -exec rm -f {} \;
