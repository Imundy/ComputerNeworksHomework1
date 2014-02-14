Ian Mundy
ian.m.mundy@vanderbilt.edu


The design of my benchmark client is farily straightforward.
It has two methods:
One runs the serial implementation.
One runs the threaded implementation.
These two methods record the time in nanoseconds at the beginning and end
and return that result to main. Main displays this result to the console.

While the results will vary from run to run as far as execution time,
the multithreaded server is faster than the serial server 100% of the
time when I run the program. Not twice as fast, but about 15 to 20%
faster on average.

The multithreaded server is better mostly because it makes sense.
Once the serversocket receives a connection, it should spawn a thread
to do whatever work needs done so it can continue to listen for new 
requests. The abililty to handle the two tasks at the same time allows
the multithreaded server to consistently perform faster when handling
a large number of requests. 