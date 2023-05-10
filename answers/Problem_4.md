# #4 My Sql transaction clean up?

### Delete data:
- delete data in batches; to monitor deletes in production
- delete query using where clause\
`delete from transaction_table where create_date < date_sub(now(), interval 1 month)`

### Create new indexes:
- create new indexes on the column which commonly used in select queries\
`create index create_date_index on transaction_table (create_date)`