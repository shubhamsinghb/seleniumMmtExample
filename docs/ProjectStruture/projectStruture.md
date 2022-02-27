# Project Struture

##src

### bo

Package for the Java Objects (Business Objects) for various entities

### driverManager

Package for the browser driver Manager. To add a new driver extenf IBrowserManager.

### enumConstants

Enums and constants needed during the project

### file

This package is for adding the fileutils needed. Like property reader

### pages

This package contains base page for all selenium operation. All the pages need to extend this basepage. <br />
Interface for the various pages on website. These interface are entended to create page for a given url page.

### utils

Package for various utility methods needed for running the project. Currently contails date
util for date manipulation and Gst util to calculate gst.

## test

###dataAggregators
This package is used to aggregate date for data provider from Json file. All user input data is passed as Json file, read using the DataAggregator
and converted to respective BO objects.

###dataProviders
This package is for data provider for running the tests

###listeners
This package is for adding any testNg listeners. <br/>
We are adding test case name to logs in on test start <br/>
Taking sceenshot image on test failure

###pages
This package contains testcases

[Homepage](../../README.md)