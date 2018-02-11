A csv parser supporting formulas and referencing in the form

=A1+B1+8+A2

Maximum rows supported: 26.  Rows indexed with letters A-Z, columns indexed with numbers 0-...

mxParser used as the expression interpreter: http://mathparser.org/

usage: java -jar spreadsheet.jar -i input.csv -o output.csv

build with maven (mvn clean package) to produce spreadsheet.jar executable jar.
Main class is com.dbs.spreadsheet.SpreadSheetRunner

Example

Input:

```
2,4,1,=A0+A1*A2
=A3*(A0+1),=B2,0,=A0+1
```

should produce the following output file:
```
2.00000,4.00000,1.00000,6.00000
18.00000,0.00000,0.00000,3.00000
```
