# #3 My Sql transaction query

### Create index:
- index will allow the database to quickly locate the most recent records and retrieve them efficiently\
`create index create_date_index on transaction_table (create_date)`

 
### Select Query:
- perform a SELECT query on the most recent records without impacting performance, you can create an index on the create_date column in descending order 
- you can limit the number of records returned by the query to avoid retrieving too much data at once\
`select distinct name from transaction_table order by create_date desc limit 1000`
