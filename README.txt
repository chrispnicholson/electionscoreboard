1) Open command line/terminal and navigate to this directory.
2) Issue the command: mvn clean package && java -jar target/electionscoreboard-0.0.1-SNAPSHOT.jar.
This should run a Tomcat instance with running REST API Spring Boot application within.
3) Navigate to this directory and find the scores.html file within the resouces/templates directory and open this in a browser.
This is a webpage that refreshes every 5 seconds, while making an AJAX GET to the REST API to get an updated election score.
4) Open another command line/terminal and navigate to this directory.
5) Issue the command mvn exec:java -Dexec.mainClass="uk.co.bbc.electionscoreboard.ScoreboardClient".
This will run the file ingest on each XML file every 5 seconds - and each bit of XML will be POSTed to the REST API.

NOTES
-----
The application works but needs more refactoring, as well as more test coverage for error conditions. I'd usually document this more,
in both Javadoc and Confluence/Wiki.