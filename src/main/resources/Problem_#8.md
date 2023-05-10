# #8 My Sql truncate vs delete.

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