Panama Papers Data Analysis
============================


###Cleaning Data
```
sed 's/[^,]//g' data/Officers.csv | awk '{ print $_ length }'
```


###References:

* [http://nootrino.com/panama-papers/](http://nootrino.com/panama-papers/)
* [https://spark.apache.org/docs/0.9.0/graphx-programming-guide.html](https://spark.apache.org/docs/0.9.0/graphx-programming-guide.html)
* [http://ampcamp.berkeley.edu/big-data-mini-course/graph-analytics-with-graphx.html](http://ampcamp.berkeley.edu/big-data-mini-course/graph-analytics-with-graphx.html)

