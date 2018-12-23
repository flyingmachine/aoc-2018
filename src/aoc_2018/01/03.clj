(ns aoc-2018.01.03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (->> (io/resource "input/03.txt")
                (io/reader)
                (line-seq)
                (map (fn [s]
                       (map #(Integer. %)
                            (str/split (str/replace s #".*?@ " "") #"(,|: |x)"))))))

(defn claims
  []
  (->> (reduce (fn [inches [x y w h]]
                 (reduce (fn [inches claim]
                           (update inches claim (fnil inc 0)))
                         inches
                         (for [x-claim (range x (+ x w))
                               y-claim (range y (+ y h))]
                           [x-claim y-claim])))
               {}
               input)
       vals
       (filter #(> % 1))
       (count)))
