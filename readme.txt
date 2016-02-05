SOA based Multi Module Maven Spring MVC Web Application with Camel Integration
Features:

Application has pooling system which contains user (session object) and user's electronical accounts containing swift (bic) and iban number.
With user caching system system can serve multiple users at same time. The system is thread safe and prefered O(1) asymptotic time complexity as count goes to infinity.
Application sends the account data with corresponding Servlet Methods with  Apache Camel Data integration framework with  JMS producer .
The persistence applciation uses HSQL  using hibernate (orm).
App uses ajax as backend to frontend communication system using JSON data.(jackson).
Views render stringified json data from Rest Controllers.
The uniquness of data fields have been used as an advantage using set to gain power for manipulating accounts per user in iterations. 
The data structures has been prefered as a map and the set . map data structure indexes users set data structure O(1) iterates and modifies users.

Application has no testing module testing is skipped.And the  Application has no custom exception handling system. 

