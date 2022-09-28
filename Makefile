.SUFFIXES: .java .class

JC = javac -encoding ISO-8859-1 #-Xlint:unchecked
JDOC = javadoc -encoding ISO-8859-1
JDOC_DIR = javadoc

all:
	$(JC) Mastermind.java
	java Mastermind

frame:

	$(JC) Frame.java
	java Frame

javadoc:
	rm -rf $(JDOC_DIR)
	mkdir -p $(JDOC_DIR)
	$(JDOC) -d $(JDOC_DIR) *.java


clean: 
	$(RM) *.class
