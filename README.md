# Coke Assignment

> Coco Cola Assignment

### Java programs
   - programs are kept inside `src/main` folder


### Run Test Cases
   - to run the programs find test cases `src/test` folder

---

## #3 My Sql transaction query

### Create index:
- index will allow the database to quickly locate the most recent records and retrieve them efficiently\
  `create index create_date_index on transaction_table (create_date)`


### Select Query:
- perform a SELECT query on the most recent records without impacting performance, you can create an index on the create_date column in descending order
- you can limit the number of records returned by the query to avoid retrieving too much data at once\
  `select distinct name from transaction_table order by create_date desc limit 1000`

---

## #4 My Sql transaction clean up?

### Delete data:
- delete data in batches; to monitor deletes in production
- delete query using where clause\
  `delete from transaction_table where create_date < date_sub(now(), interval 1 month)`

### Create new indexes:
- create new indexes on the column which commonly used in select queries\
  `create index create_date_index on transaction_table (create_date)`

---

## #5 You have a java process running on a Linux EC2 in production that is running out of memory daily in production even though there are 16 GB allocated to the JVM.

### Identify memory leaks:
- using tools like `jProfiler` & `jvisualvm` we can check the memory usage and garbage collectors efficiency

### JVM options:
- configure memory sizes for heap, stack, etc.
- garbage collector logs
- Xmx - maximum heap size
- Xms - initial heap size
- XX:MaxPermSize - maximum permanent generation size


### Possible code optimizations:
- modify the code to fix a memory leak if necessary

### Scaling horizontally:
- scaling the application horizontally by adding more instances

---
## #7 You are running a web application on Jetty and Apache on a Linux operating system, and you want to make sure no one can exploit any unknown vulnerability in the site you created to gain root access to the Linux Operating System.

### Non-root user:
- non-root user accounts with the minimal access to run the application
- create users for each user who can access the application
- no sudo users

### File & Directory permissions:
- `chmod` command to define file & directory permissions as read, write, executables
- `chown` change the owner
- `ssh-keygen` to generate key pairs

### Authentication:
- SSH key authentication


### Launch Script
- dedicated user for launching the scripts
- use of environment variables & relative paths
- logging 

---
## #8 My Sql truncate vs delete.

### Truncate:
- removes all data from the table
- auto-increment id is reset
- usage: quick & efficient, does not trigger anything, no-logging

### Delete:
- removes specific row from the table using `where clause`
- logs delete event & fire trigger if configured


### Example:
- create a temporary table _temp_products_ with the same structure as _products_ table.
- use TRUNCATE to remove all the data from the _temp_products_ table.
- use SELECT INTO to insert the updated products data from the _products_updates_ table into the _temp_products_ table.
- use TRUNCATE to remove all the data from the _products_ table.
- use INSERT INTO to insert the data from the _temp_products_ table into the _products_ table.
- drop the _temp_products_ table.

together in this way, we can efficiently and reliably integrate data into your target table


### Relation to #4 Problem:
- safe delete with the use of SELECT INTO
- deleting rows based on create_date column
- trigger the configured triggers if required

---

## #9 You have multi thread java application where the threads appear to dead lock in production.

### Analysis:
- using tools like `VisualVM` by identifying the thread states & locks from thread dumps
- look for root cause of the deadlock
- command\
  `jstack <pid>` for finding the stack trace of a thread
  `jvisualvm` to open the visual vm for threads info


### Fix Code:
- fix the code for multi thread environment
- use concurrency
- efficient resource locking / unlocking

---