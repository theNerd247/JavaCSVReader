JAVA CSV READER Library
FOR: Java based FRC robots. 
REQUIRED APIs: Standard FRC Java Lib (as of 2012) - not Standard API library.
DESCRIPTION: 
	This is a basic code that can be used to allow the robot to read CSV files (Character Seperated Values), and write them. This will allow for the program to contain an upper level of programming in which custom config files can be written to allow quick changes to robot properties and data. 

NOTES ON CSV STYLING:

At the begining of each document the user must include the file header data. This data is used to determine how the parser is to read the file. The format of the header is as follows: <DSV><Header_Delimiter><DSV><Line_Delimiter><DSV><Data_Delimiter><DSV> Where <DSV> represents a single any single character used to seperate the file header data. 

Headers: Headers are sets of related data in this CSV file. They are denoted by <Header_Delimiter> (see above) and directly followed by the data for the new header. Each Header denoting line MUST be formatted as follows: <Header_Delimiter>(JPDT)DescripterData,(JPDT)...etc...<Header_Delimiter> . For example, a new header is created by: #(String)Name,(int)Age,(double)Height Where # is the <Header_Delimiter> and String, int, double are the (JPDT) or (java Primitive Data type). (A Java Primitive data type are all data types that come wit h java. This program allows String to act as a JPDT). 

Data: Data within a given header is formated as follows: <Line_Delimiter>Data1,Data2,Data3,...etc . For example: ;Bob,12,4.5 Where ";" is the <Line_Delimiter> and Bob,12,4.5 are the data for the given header

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

NOTICE: each data set is seperated by a new line character (not shown). This includes any header data. 
