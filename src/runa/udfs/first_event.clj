(ns runa.udfs.first-event
    (:import [org.apache.hadoop.hive.ql.exec UDF])
    (:import [org.apache.hadoop.io Text])
    (:import [java.text SimpleDateFormat])
    (:import [java.util Date])
    (require [clojure.string :as sr])
    (:use runa.utils.event-utils)
    (:gen-class
     :name runa.udfs.StartTimestamp
     :extends org.apache.hadoop.hive.ql.exec.UDF
     :methods [[evaluate [org.apache.hadoop.io.Text] org.apache.hadoop.io.Text]]))



(defn #^Text -evaluate 
  "First Event Timestamp in a set of events"
  [this #^Text s]
  (when s
    (Text. (try
	     (str
	      (first
	       (all-timestamps (.toString s))))
		(catch Exception e# "")))))