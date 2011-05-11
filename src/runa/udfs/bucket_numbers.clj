(ns runa.udfs.bucket-numbers
    (:import [org.apache.hadoop.hive.ql.exec UDF])
    (:import [org.apache.hadoop.io Text])
    (:import [java.text SimpleDateFormat])
    (:import [java.util Date])
    (:require [clojure.string :as sr])
    (:gen-class
     :name runa.udfs.Bucket
     :extends org.apache.hadoop.hive.ql.exec.UDF
     :methods [[evaluate [org.apache.hadoop.io.Text org.apache.hadoop.io.Text] org.apache.hadoop.io.Text]]))

(defn get-bucket [buckets, value]
  (apply max
	 (filter #(>= (Float. value)  %)
		 (map #(Integer. %)
		      (sr/split buckets #",")))))

(defn #^Text -evaluate 
  "Buckets the content into one of the possible buckets."
  [this #^Text s #^Text bkt]
  (when s
    (Text. (try (str " > " (get-bucket (str bkt) (str s)))
		(catch Exception e# "")))))