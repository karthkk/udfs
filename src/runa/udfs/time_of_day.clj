(ns runa.udfs.time-of-day
    (:import [org.apache.hadoop.hive.ql.exec UDF])
    (:import [org.apache.hadoop.io Text])
    (:import [java.text SimpleDateFormat])
    (:import [java.util Date])
    (:gen-class
     :name runa.udfs.HourOfDay
     :extends org.apache.hadoop.hive.ql.exec.UDF
     :methods [[evaluate [org.apache.hadoop.io.Text] org.apache.hadoop.io.Text]]))


(defn #^Text -evaluate 
  "Hour of the day from timestamp"
  [this #^Text s]
  (when s
    (Text. (try (str (.getHours (Date. (Long. (.toString s)))))
		(catch Exception e# "")))))