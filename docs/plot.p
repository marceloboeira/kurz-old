set terminal png
set output "report-10k-requests.png"
set title "10.000 requests"
set size 1,1
set grid y
set xlabel "Requests"
set ylabel "Response Time (ms)"
plot "data/performance_1.dat" using 10 smooth sbezier with lines title "Concurrency 1", \
"data/performance_10.dat" using 10 smooth sbezier with lines title "Concurrency 10", \
"data/performance_50.dat" using 10 smooth sbezier with lines title "Concurrency 50", \
