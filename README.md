# ResumeDummyAutomationWebsite
Using a dummy automation website ("https://www.saucedemo.com/") to gain some hands-on experience with Selenium, Cucumber, and Log4j. 
This project implements Page Object Model, but does not use PageFactory for no particular reason. The webdriver is encapsulated in 
its own wrapper class ("UIAction") to keep the webdriver references in one place. The logger also has its own wrapper class which
implements a Logger interface to simulate the flexibility of switching out loggers easily. There are only 5 test scenarios to keep
the project small and easier to read for a potential employer. The scenarios involve grabbing different elements, manipulating them,
and verify they do what they should; like sorting the products on the product catalogue page for example.
