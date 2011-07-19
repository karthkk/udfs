(ns runa.udfs.history-info
    (:import [org.apache.hadoop.hive.ql.exec UDF])
    (:import [org.apache.hadoop.io MapWritable Text])
    (:import [java.util Map HashMap])
    (:import [org.apache.hadoop.hive.serde2.objectinspector MapObjectInspector])
    (:gen-class
     :name runa.udfs.HistoryInfo
     :extends org.apache.hadoop.hive.ql.exec.UDF
     :methods [[evaluate [java.lang.Object, java.lang.String] org.apache.hadoop.io.Text ]]))

(defn timestamp [s]
  (Long. (-> s
		(.replace  "visits-" "")
		(.replace "purchase-" "")
		(.substring 37 50))))

(defn valid? [s]
  (or
   (.contains s "runa-total-dollars")
   (.contains s "__duration")))

(defn before? [ts s]
  (< (timestamp s)
     ts))

(defn count-str [s mp]
  (count (filter #(.contains (key %) s) mp)))

(defn get-value-for-type [type mpe]
  (if (.contains (key mpe) type)
    (Double. (val mpe))
    0))

(defn sum-str [s mp]
  (reduce +
	  (map (partial get-value-for-type s) mp)))

(defn #^Text -evaluate 
  "Extract product info from a map"
  [this #^Object s #^String session-id]
  (when s
    (Text. (try
	     (let [d (into {} s)
		   ts (timestamp session-id)
		   v (select-keys
		      d
		      (filter (partial before? ts)
			      (filter valid? (keys d))))]
	       (str (count-str "visit" v) ","
		    (count-str "purchase" v) ","
		    (sum-str "visit" v) ","
		    (sum-str "purchase" v)))
	     (catch Exception e# (str
				  (.getMessage e#)
				  " --------- "
				  (.getStackTrace e#)))))))