  (ns runa.udfs.day-of-week
    (:import [org.apache.hadoop.hive.ql.exec UDF])
    (:import [org.apache.hadoop.io Text])
    (:import [java.text SimpleDateFormat])
    (:gen-class
     :name runa.udfs.DayOfWeek
     :extends org.apache.hadoop.hive.ql.exec.UDF
     :methods [[evaluate [org.apache.hadoop.io.Text] org.apache.hadoop.io.Text]]))

(def week-days {0 "Sunday"
		1 "Monday"
		2 "Tuesday"
		3 "Wednesday"
		4 "Thursday"
		5 "Friday"
		6 "Saturday"})

(defn #^Text -evaluate 
  "Weekday for day"
  [this #^Text s]
  (when s
    (Text. (try (week-days
		 (.getDay
		  (.parse
		   (SimpleDateFormat. "yyyy-MM-dd")
		   (.toString s))))
		(catch Exception e# "")))))


(defn -main [& args]
  (println "Day Of Week UDFS"))
