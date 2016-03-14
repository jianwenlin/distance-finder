Instruction to run the project:

The project is uploaded to github.com/jianwenlin

1. you can import the maven project into eclipse with the pom.xml inside the jar. 
	The repo of mavan is based on (maven central repo) https://repo1.maven.org/maven2.
	If you do have issue to build, please check the settings.xml within the jar home directory. 
	If there is a proxy or filewall, then we need to make sure the inside and outside repo are in sync, otherwise go outside firewall
	
2. Run the DistanceFinder from eclipse with arguments: 
	the canal 6 input.txt
	command line run will need to be with a classpath and the package name.
	
3. The input.txt file is in src/main/resources directory.	
4. Unit testing fixtures are in the test/resources directory.
5. You can run any testing file within the classpath directories.
6. The jar can be built with mvn command. (mvn clean install, package...)
7. Compiler and target jdk 1.7 as specified in the pom.xml


Assumption:
1. work comparision not case sensitive
2. punctuation is not removed from the word
3. newline the same as the space delimiter
4. File should not be single line with huge data (lets say >300M), big file is fine as far as it is separated with new line
5. File size <300 M is read one time and parsed line by line in the memory
6. File Size >300 M will be read line by line with scanner. Therefore heap OutOfMemory will be avoided
7. The implementation is a Binary Serach Tree based with range sort. 
8. The total complexity will be 
	a. parsing the data O(n) n is the total length of the document (words)
	b. BST build will be O(m1logm1) here m1 is the smaller keyword list
	c. Search the BST with keyword list 2 between postion - distance and position + distance, that will be O(m2+distance)logm1. Since distance is always small, the cost is O(m2logm1)
	d. Total search complexity is O((m1+m2)logm1 + n ) where m1<m2 and n is the total length of the document.
	e. The space is O(m1+m2).
	

Boundaries and Design of the flow:
1. read words from the input file (by line)
2. keep two ArrayLists of keyword1 and keyword2 with positions
3. Since the ArrayLists are already sorted, it will only take O(m1logm1) to build the BST.
4. Loop through ArrayList2 by exend the range from current keyword2 postion - distance and + distance to seach in the BST. This will take (m2+distance)logm1
5. Another approach will be brute force and for each keyword1  we go through each word within the distance to find all keyword2
6. The file IO performance is another focus point. I designed a input reading strategy. It will automatically switch between large buffer read and single line read based onthe file status. 
7. It will be very each to reconfigure the enum and file size to change the input read strategy based on system boundaries. It is also convenient to add new input read strategy. This is one of the design principle: open to extend but close to change.
8. If the systme is the CPU bounded, then we should reduce the single line read and add more buffer for reading. If the system is memory bounded, then we should reduce the buffer in the input reading process.
9. I build 28 junit testing cases for the seaching services, input validation, BST, input reading services
10. I did put MockIto as dependency.
11. I also add javaee-api as dependency and the @Inject annotation works in test but not in run time. And I removed them later in the integration.
12. Still missing some junit tesing cases
13. Multi threding was not considered due to one command run as requirement. It is also very easy to switch to Spring service class.
