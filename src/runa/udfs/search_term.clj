(ns runa.udfs.search-term
    (:import [org.apache.hadoop.hive.ql.exec UDF])
    (:import [org.apache.hadoop.io Text])
    (:import [java.text SimpleDateFormat])
    (:import [java.util Date])
    (:gen-class
     :name runa.udfs.SearchTerm
     :extends org.apache.hadoop.hive.ql.exec.UDF
     :methods [[evaluate [org.apache.hadoop.io.Text] org.apache.hadoop.io.Text]]))