#!/bin/sh

if [ -d "images" ]; then
  echo "Conversion .dia..."
  (cd images
     dia -t eps *.dia | grep -vi "missing"
     for i in *.eps; do epstopdf $i 2>&1 | grep -viE "(^!!!|^epstopdf)"; done)
fi

echo "Compilation 1 ..." &&
pdflatex -file-line-error *.tex &&
echo "Compilation 2 ..." &&
pdflatex -file-line-error *.tex > /dev/null &&
echo "Compilation 3 ..." &&
pdflatex -file-line-error *.tex > /dev/null

#rm -f *.aux
#rm -f *.cb
#rm -f *.cb2
#rm -f *.log
#rm -f *.out
#rm -f *.toc
#rm -f *~
#rm -f *.backup
#rm -f images/*.eps

