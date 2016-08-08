# Gumtree coding challenge

Solution by Or Taieb <or.taieb@gmail.com>

## The task

Your task is to develop a small java application. We need you to build your application in your own GitHub repository.  Please do not fork our repository to create your project.  Once you are done, send us a link to your repository.

Please allow yourself at least 1 hour of uninterrupted time for this task, but feel free to spend as much time on the task as you like and make the solution and the code as perfect as you like.

## The application

Your application needs to read the attached AddressBook file and answer the following questions:

1. How many males are in the address book?
2. Who is the oldest person in the address book?
3. How many days older is Bill than Paul?

## The Solution

I decided to use an immutable objects approach.
The AddressBook changes while accepting more and more records. In addition, in order to save as much as possible 
future iteration over the address book collection it holds the data with more than single order. In addition to the 
look up by name ( used to find entry by name, e.g. calculate the days between two people ) it manage a counters of
the genders in GenderCounter as well as store in multimap by date ( to maintain the oldest query easily ) 

*com.taieb.addressbook.Solution* holds the complete formatted answer for the task, while each aspect is tested and covered
by relevant test class.




 
