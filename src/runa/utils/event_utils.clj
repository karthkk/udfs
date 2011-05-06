(ns runa.utils.event-utils
  (:require [clojure.string :as sr]))

(defn all-timestamps [s]
  (sort
   (map
    #(first (sr/split % #":"))
    (sr/split
     (sr/replace
      (sr/replace 
       (sr/replace  s "[" "") "\"" "") "]" "") #","))))