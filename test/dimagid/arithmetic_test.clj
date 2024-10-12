;; ---------------------------------------------------------
;; dimagid.arithmetic.-test
;;
;; - `deftest` - test a specific function
;; - `testing` logically group assertions within a function test
;; - `is` assertion:  expected value then function call
;; ---------------------------------------------------------


(ns dimagid.arithmetic-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [dimagid.arithmetic :as arithmetic]))

(deftest first-prime
  (testing "the first prime is 2"
    (is (= 2 (arithmetic/nth-prime 1)))))

(deftest second-prime
  (testing "the second prime is 3"
    (is (= 3 (arithmetic/nth-prime 2)))))

(deftest sixth-prime
  (testing "the sixth prime is 13"
    (is (= 13 (arithmetic/nth-prime 6)))))

(deftest ten-thousand-first-prime
  (testing "the ten thousand and first prime is 104743"
    (is (= 104743 (arithmetic/nth-prime 10001)))))

(deftest zeroth-prime
  (testing "there is no zeroth prime"
    (is (thrown? AssertionError (arithmetic/nth-prime 0)))))

(deftest negativeth-prime
  (testing "there is no negativeth prime"
    (is (thrown? AssertionError (arithmetic/nth-prime -2)))))

(deftest floatth-prime
  (testing "there is no floatth prime"
    (is (thrown? AssertionError (arithmetic/nth-prime 3.3)))))

(deftest one
  (is (= [] (arithmetic/prime-factors 1))))

(deftest two
  (is (= [2] (arithmetic/prime-factors 2))))

(deftest three
  (is (= [3] (arithmetic/prime-factors 3))))

(deftest four
  (is (= [2, 2] (arithmetic/prime-factors 4))))

(deftest six
  (is (= [2, 3] (arithmetic/prime-factors 6))))

(deftest eight
  (is (= [2, 2, 2] (arithmetic/prime-factors 8))))

(deftest nine
  (is (= [3, 3] (arithmetic/prime-factors 9))))

(deftest twenty-seven
  (is (= [3, 3, 3] (arithmetic/prime-factors 27))))

(deftest six-hundred-twenty-five
  (is (= [5, 5, 5, 5] (arithmetic/prime-factors 625))))

(deftest a-large-number
  (is (= [5, 17, 23, 461] (arithmetic/prime-factors 901255))))

(deftest a-huge-number
  (is (= [11, 9539, 894119] (arithmetic/prime-factors 93819012551))))

(deftest first-n-primes
  (testing "the first n primes"
    (is (= [2 3 5 7] (arithmetic/primes-up-to-n 10)))
    (is (= [2 3 5 7] (arithmetic/primes-up-to-n 7)))
    (is (= [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199 211 223 227 229 233 239 241 251 257 263 269 271 277 281 283 293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409 419 421 431 433 439 443 449 457 461 463 467 479 487 491 499 503 509 521 523 541 547 557 563 569 571 577 587 593 599 601 607 613 617 619 631 641 643 647 653 659 661 673 677 683 691 701 709 719 727 733 739 743 751 757 761 769 773 787 797 809 811 821 823 827 829 839 853 857 859 863 877 881 883 887 907 911 919 929 937 941 947 953 967 971 977 983 991 997] (arithmetic/primes-up-to-n 1000)))))

(deftest edge-cases
  (testing "edge cases"
    (is (= [] (arithmetic/primes-up-to-n 1)))
    (is (= [2] (arithmetic/primes-up-to-n 2)))))

(deftest invalid-inputs
  (testing "invalid inputs"
    (is (thrown? AssertionError (arithmetic/primes-up-to-n -1)))
    (is (thrown? AssertionError (arithmetic/primes-up-to-n -100)))
    (is (thrown? AssertionError (arithmetic/primes-up-to-n 1.5)))
    (is (thrown? AssertionError (arithmetic/primes-up-to-n "4")))))

(deftest test-number-summary
  (testing "Prime Number"
    (let [n 7
          result (arithmetic/number-summary n)]
      (is (= (:n result) n))
      (is (= (:spellout-number result) "seven"))
      (is (= (:scale result) "unit"))
      (is (= (:divisors result) [1 7]))
      (is (= (:is-even-or-odd? result) :odd))
      (is (= (:divisor-sum result) 8))
      (is (= (:divisor-type result) :deficient))
      (is (= (:prime-factors result) [7]))
      (is (:prime? result))
      (is (not (:perfect-square? result)))
      (is (not (:perfect-cube? result)))))

  (testing "Perfect Number"
    (let [n 6
          result (arithmetic/number-summary n)]
      (is (= (:n result) n))
      (is (= (:spellout-number result) "six"))
      (is (= (:scale result) "unit"))
      (is (= (:divisors result) [1 2 3 6]))
      (is (= (:is-even-or-odd? result) :even))
      (is (= (:divisor-sum result) 12))
      (is (= (:divisor-type result) :perfect))
      (is (= (:prime-factors result) [2 3]))
      (is (not (:prime? result)))
      (is (not (:perfect-square? result)))
      (is (not (:perfect-cube? result)))))

  (testing "Abundant Number"
    (let [n 12
          result (arithmetic/number-summary n)]
      (is (= (:n result) n))
      (is (= (:spellout-number result) "twelve"))
      (is (= (:scale result) "tens"))
      (is (= (:divisors result) [1 2 3 4 6 12]))
      (is (= (:is-even-or-odd? result) :even))
      (is (= (:divisor-sum result) 28))
      (is (= (:divisor-type result) :abundant))
      (is (= (:prime-factors result) [2 2 3]))
      (is (not (:prime? result)))
      (is (not (:perfect-square? result)))
      (is (not (:perfect-cube? result)))))

  (testing "Perfect Square"
    (let [n 16
          result (arithmetic/number-summary n)]
      (is (= (:n result) n))
      (is (= (:spellout-number result) "sixteen"))
      (is (= (:scale result) "tens"))
      (is (= (:divisors result) [1 2 4 8 16]))
      (is (= (:is-even-or-odd? result) :even))
      (is (= (:divisor-sum result) 31))
      (is (= (:divisor-type result) :deficient))
      (is (= (:prime-factors result) [2 2 2 2]))
      (is (not (:prime? result)))
      (is (:perfect-square? result))
      (is (not (:perfect-cube? result)))))

  (testing "Perfect Cube"
    (let [n 27
          result (arithmetic/number-summary n)]
      (is (= (:n result) n))
      (is (= (:spellout-number result) "twenty-seven"))
      (is (= (:scale result) "tens"))
      (is (= (:divisors result) [1 3 9 27]))
      (is (= (:is-even-or-odd? result) :odd))
      (is (= (:divisor-sum result) 40))
      (is (= (:divisor-type result) :deficient))
      (is (= (:prime-factors result) [3 3 3]))
      (is (not (:prime? result)))
      (is (not (:perfect-square? result)))
      (is (:perfect-cube? result))))

  (testing "Number Summary with Zero"
    (let [n 0
          result (arithmetic/number-summary n)]
      (is (= (:n result) n))
      (is (= (:spellout-number result) "zero"))
      (is (= (:scale result) "zero"))
      (is (= (:divisors result) []))
      (is (= (:is-even-or-odd? result) :even))
      (is (= (:divisor-sum result) 0))
      (is (= (:divisor-type result) nil))
      (is (= (:prime-factors result) []))
      (is (not (:prime? result)))
      (is (not (:perfect-square? result)))
      (is (not (:perfect-cube? result))))))

(deftest times-table-test
  (testing "times-table prints the correct multiplication table"
    (let [output (with-out-str (arithmetic/times-table 5))]
      (is (re-find #".*5 x 1 = 5.*" output))
      (is (re-find #".*5 x 2 = 10.*" output))
      (is (re-find #".*5 x 3 = 15.*" output))
      (is (re-find #".*5 x 4 = 20.*" output))
      (is (re-find #".*5 x 5 = 25.*" output))
      (is (re-find #".*5 x 6 = 30.*" output))
      (is (re-find #".*5 x 7 = 35.*" output))
      (is (re-find #".*5 x 8 = 40.*" output))
      (is (re-find #".*5 x 9 = 45.*" output))
      (is (re-find #".*5 x 10 = 50.*" output)))))

(deftest times-table-test-zero
  (testing "times-table prints the correct multiplication table for zero"
    (let [output (with-out-str (arithmetic/times-table 0))]
      (is (re-find #".*0 x 1 = 0.*" output))
      (is (re-find #".*0 x 2 = 0.*" output))
      (is (re-find #".*0 x 3 = 0.*" output))
      (is (re-find #".*0 x 4 = 0.*" output))
      (is (re-find #".*0 x 5 = 0.*" output))
      (is (re-find #".*0 x 6 = 0.*" output))
      (is (re-find #".*0 x 7 = 0.*" output))
      (is (re-find #".*0 x 8 = 0.*" output))
      (is (re-find #".*0 x 9 = 0.*" output))
      (is (re-find #".*0 x 10 = 0.*" output)))))

(deftest times-table-test-negative
  (testing "times-table prints the correct multiplication table for a negative number"
    (let [output (with-out-str (arithmetic/times-table -5))]
      (is (re-find #".*-5 x 1 = -5.*" output))
      (is (re-find #".*-5 x 2 = -10.*" output))
      (is (re-find #".*-5 x 3 = -15.*" output))
      (is (re-find #".*-5 x 4 = -20.*" output))
      (is (re-find #".*-5 x 5 = -25.*" output))
      (is (re-find #".*-5 x 6 = -30.*" output))
      (is (re-find #".*-5 x 7 = -35.*" output))
      (is (re-find #".*-5 x 8 = -40.*" output))
      (is (re-find #".*-5 x 9 = -45.*" output))
      (is (re-find #".*-5 x 10 = -50.*" output)))))

(deftest test-times-table
  (testing "n must be an integer"
    (is (thrown? AssertionError (arithmetic/times-table 3.3)))))
