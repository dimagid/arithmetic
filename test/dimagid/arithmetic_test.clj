;; ---------------------------------------------------------
;; dimagid.arithmetic.-test
;;
;; Example unit tests for dimagid.arithmetic
;;
;; - `deftest` - test a specific function
;; - `testing` logically group assertions within a function test
;; - `is` assertion:  expected value then function call
;; ---------------------------------------------------------


(ns dimagid.arithmetic-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [dimagid.arithmetic :as arithmetic]))


(deftest application-test
  (testing "TODO: Start with a failing test, make it pass, then refactor"

    ;; TODO: fix greet function to pass test
    (is (= "dimagid application developed by the secret engineering team"
           (arithmetic/greet)))

    ;; TODO: fix test by calling greet with {:team-name "Practicalli Engineering"}
    (is (= (arithmetic/greet "Practicalli Engineering")
           "dimagid service developed by the Practicalli Engineering team"))))
