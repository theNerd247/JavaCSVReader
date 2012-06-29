|=================================================================================|
| JAVA CSV READER Library                                                         |
| FOR: Java based FRC robots.                                                     |
| LICENSE: BSD 2-Clause.                                                          |
| REQUIRED APIs: Standard FRC Java Lib (as of 2012) - not Standard API library.   |
|=================================================================================|


DESCRIPTION: 
	JavaCSVReader is a lightweight library to allow FRC robots to read and write
	any kind of data for the robot. This library uses a custom created Character
	Seperated Values File. See below for details on the formatting of this file.

NOTES ON CHARACTER SERPARATED VALUES (CSV) FILE:
	Each CSV File is formatted to contain metadata within itself. This allows
	for one file to contain what many files would normally contain. This does
	not mean that the file size would be any less, but programers would only
	need to deal with one file for all their data instead of many files which
	saves room on the cRIO and makes FTPing scripts much smaller.

	The data in a CSV file is grouped into what are called headers. Each header
	begins with a special character called the "header delimiter" and ends with
	the last data set or an end of file. The term "header" refers to the
	metadata its dataset. 

	The metadata for a header contains the column names for that dataset (as if
	it were a spreadsheet), and MUST be the FIRST line in a given data set. A
	new line character (in ASCII it is "\n") ends the metadata for a data set. 

	Data for a dataset is found in the next line after the metadata. Each piece
	of data is seperated by the "data delimitter" character. The set of data is
	denoted by a new line and the "line delimitter" character. 
	
	THE MOST IMPORTANT FORMATTING for the file is the very first line of the
	file. This line is the METADATA for the entire file. It denotes the
	different delimitters for the file. The firs line for each file must go as
	follows:

			<DELIM> HEADER_DELIM <DELIM> LINE_DELIM <DELIM> DATA_DELIM <DELIM>
	It doesn't matter which character <DELIM> is as long as it's constant. 

EXAMPLE CSV FILE:

	|#|;|,| 
	#(String)Name,(int)Age,(double)height 
	;Bob,12,3,2 
	;Larry,7,8.6
	#(String)Device,(int)port 
	;Jag,3 
	;Relay,1 
	;Solenoid,2
	#(String)Property,(String)Name,(double)value
	;DistancePerCount,LeftEncoder,3.2333234 
	;MaxSpeed,MaxSpeed,3

	Note the new lines that seperate each unique data piece and the delimitter
	characters. In this case the header delimitter is '#' , the line delimiter
	is ';'  , and the data delimitter character is ','. 

NOTES ON CSV FILE PARSING AND DATA TYPES: 
	A strength of this file format is end user flexiblity. Programmers can save
	any data type that can be converted into a string. Each piece of data is
	written to the file as a string - in the encoding type of the programmer's
	choice. 

	The parser of a file begins by extracting and reading the first line of the
	file. It uses the <DELIM> character to parse for the header,line,and data
	delimitters. Once the delimitters are determined the parser goes through the
	file line by line; testing the first character in each line. If the
	character is a header delimitter then the parser saves the working header
	creates a new header container and sets it to be the working header. If it's
	a line delimitter it parses through the line and adds the data to the working
	header (using the data delimitter). The parser ignores lines that do not
	begin with any of the three given delmitters. This allows for users to
	format the CSV file using new lines. Once the parser reaches the end of the
	file it stops reading the file and parsing is complete.
