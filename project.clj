(defproject udfs "1.0.0"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.2.1"]
		 [hive/hive-common "0.5.0"]
		 [hive/hive-exec "0.5.0"]
		 [org.apache.hadoop/hadoop-core "0.20.2-dev"]]
  :main runa.udfs.time-of-day
  :aot [runa.udfs.day-of-week
	runa.udfs.time-of-day
	runa.udfs.first-event
	runa.udfs.last-event
	runa.udfs.product-info
	runa.udfs.history-info
	runa.udfs.bucket-numbers])
