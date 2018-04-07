# JAVA
### data structure
  - HashMap / ConcurrentHashMap
  - String / StringBuffer / StringBuilder
  - ArrayList(动态数组) / Array / LinkedList(双向链表)
  - equals / hashCode
  - Boxing / Unboxing
  - synchronize / volatile / AtomicInteger / thread pool / countDownLatch / cyclicBarrier
### desgin pattern
  - singleton
### spring
  - ?
### nio
  - ?
### jvm
  - ?
    
# MYSQL
###  index
  - cluster index (data in index, innodb pk default)
  - covering index(如果where条件的列和返回的数据在一个索引中，那么不需要回查表，就叫覆盖索引)
  - combine index (left prefix) using IN for absent column
### isolation
  - read committed  - sqlServer / Oracle
  - read repeatable - mysql
### MVCC

# HADOOP
### yarn
  - resourceManager
  - applicationMaster
  - nodeManager (manage container)
  - container ( computing resource)
### hdfs
  - block
### mapreduce
  - map
  - shuffle
    - partition
    - spill
    - sort
    - comine
    - merge
  - reduce
    

# HIVE
### metadata store
### external / internal table
### static partition / dynamic partition
### tunning (data skew)
  - map left join (distributed cache)

# HBASE
### rowKey
### region / region Server
### column family
### HFile

# SPARK
### shuffle
### boardcast
### optimization
  - *[Spark性能优化之道——解决Spark数据倾斜（Data Skew）的N种姿势](http://www.jasongj.com/spark/skew/)*

# KAFKA
### topic
### partition
  - leader ( r/w)
  - replica (backup only)
### logSegment
### consumer / consumer group (partition_num == consumer_num)
### broker
  - leader
  - follower
### ack
  - 0 no wait
  - 1  wait for leader
  - -1 wait for all

# STORM
### topology
  - spout -> bolt -> bolt...
### spout
### bolt
# REDIS
### data structure
  - string
  - list
  - set
  - hash
  - zset
### cache strategy
  - noeviction
  - allkeys-lru
  - volatile-lru
  - allkeys-random
  - volatile-random
  - volatile-ttl
### persistence
  - rdb (dump, completed backup, fast startup, slow backup)
  - aof (binlog)
    - appendfsync always
    - appendfsync everysec
    - appendfsync no (fastest, write into buffer w/o disk)
### distributed mutex(using watch CAS)
### atomic operation
### 缓存穿透、缓存击穿与雪崩效应

# ZOOKEEPER
### *[zab](https://www.cnblogs.com/jian-xiao/p/5821675.html)*
### leader(master) / follower(slave) / observer(standby)
### master election (znode lock)
### znode

# NOSQL(MONGODB\CASSANDRA)

# *FLINK

# *ES

# *FLUME

# RECOMMENDATION
### etl
### feature engineering
### collaborative filtering
  - user-based
  - item-based
  - ALS
### fp-growth
### Precision & Recall
  - AUC / ROC
  - ranking
