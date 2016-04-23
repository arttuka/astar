(ns astar.core-test
  (:require #?(:clj  [clojure.test :refer [deftest is]]
               :cljs [cljs.test :refer-macros [deftest is]])
            [astar.core :refer [route]]))

(def graph {:a [:b :c]
            :b [:a :d :e]
            :c [:a :d :e]
            :d [:b :c :f]
            :e [:b :c :f]
            :f [:e :d]})

(defn dist [from to]
  (let [d {:a {:b 4 :c 2}
           :b {:a 4 :d 1 :e 1}
           :c {:a 2 :d 3 :e 5}
           :d {:b 1 :c 3 :f 3}
           :e {:b 1 :c 5 :f 2}
           :f {:d 3 :e 2}}]
    (get-in d [from to])))

(def h {:a 7
        :b 3
        :c 6
        :d 3
        :e 2
        :f 0})

(deftest route-test
  (is (= [:b :e :f]
         (route graph dist h :a :f)))
  (is (nil? (route graph dist h :a :x))))
