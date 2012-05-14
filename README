JAVA CSV READER Library
FOR: Java based FRC robots. 
REQUIRED APIs: Java 1.3.0 or less (comes standard with FRC) 
DESCRIPTION: 
	This is a basic code that can be used to allow the robot to read CSV files (Comma Seperated Values), and write them. This will allow for the program to contain an upper level of programming in which custom config files can be written to allow quick changes to robot properties and data. 

NOTES ON CSV STYLING:

This program uses a CSV like file formatting. Data is contained in Character seperated values - not always a comma. Current developement of this program focuses on letting end users develop custom formatting to their documents. Each file contains a single special character to seperate data (this is called the data delimiter). Thus, a person can create a CSV file that uses the "-" character, to seperate data.

A goal of this program is to implement as much data organization as possible. As the cRIO has limited file space, it is more efficient to only have the user worry about downloading and fetching only one file to and from the cRIO. Thus, the file must have a way to seperate data sets. Thus, data is seperated into "heads". A head for a data is a simple line of data (seperated using the given data delimiter) that contains how a set of data is formatted. This allows the parser to determine how to deal with the data once it is read from the file. An example of a header for storing personal info: #Name,Age,Phone-Number#   From the example, the header delimiter (special character that designates that the given line is a header) is "#" while the data delimiter is ",". 

Finally data sets are seperated by a a line delimiter to designate the end of a data set for a given header. For example:

#Name,Age,Phone#
Bob,12,1234567;
Dan,14,1891011;
...

in the given example, the header delimiter is "#", the data delimiter is "," and the line delimiter is ";". Thus 1 data set for the given header would be "Bob,12,1234567"and it is shown to end by the line delimiter ";".

In order for a file to tell the program which characters that it is going to use as its delimiters it must include a standard MainHeader on the very first line of the file. The current standard for the MainHeader is as follows:

"|"!HeaderDELIM|LineDELIM|DataDELIM!

The "!" character designates that the encased characters are the MainHeader. The char-set ' "|" ' is to designate the main header data delimiter, or how the program is to read the data in the main header. Every CSV file written for this program MUST contain its first line like so. 
