echo "Conversion .dia..."
( cd images
for d in *; do
    ( cd $d
    dia -t eps *.dia | grep -vi "missing"
    for i in *.eps; do epstopdf $i 2>&1 | grep -viE "(^!!!|^epstopdf)"; done)
done )

echo "Compilation 1/3 ..."
pdflatex -file-line-error *.tex
echo "Compilation 2/3 ..."
pdflatex -file-line-error *.tex > /dev/null
echo "Compilation 3/3 ..."
pdflatex -file-line-error *.tex > /dev/null
echo "Compilation 3/3 terminée"

#rm -f *.aux
#rm -f *.cb
#rm -f *.cb2
#rm -f *.log
#rm -f *.out
#rm -f *.toc
#rm -f *~
#rm -f *.backup
#rm -f images/*.eps
