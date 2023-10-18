I wrote my solution in Java to ensure that it could be compiled to run on different operating systems. I tested my solution on both macOS and Windows.

Java isn’t the language I’m most fluent in, so I leaned on Google to help with some of the specific libraries and built-in class methods.

I first built the functionality to run a process, to make sure that my code would run and perform as expected. This requirement of the assignment was ideal for trying first, since it only had a single step; Keeping this program non-destructive required that the tasks for dealing with files have multiple steps, such as creating or deleting a dummy file. Running a process also did not depend on outside factors, as was the case with making an outgoing network request.

I built a simple logging class to output logging data to a YAML file as the program runs.

The main method runs another method called ‘simpleGenerateEdrEndpointActivity’, which just goes through the stated requirements. But the program could be expanded to accept arguments and run each task separately. There are also two commented-out methods for additional functions - creating/deleting a file without modifying it, and backing up a file’s contents so that it can be deleted and then recreated to avoid being destructive.



The project can be run with the following steps:

`javac Main.java` to compile the application

`java Main` to run it


Additionally, the project can be packaged into a .jar file with the following:

`jar cfm EDRActivityGenerator.jar manifest.txt -C . .`
