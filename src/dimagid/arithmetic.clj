;; ---------------------------------------------------------
;; dimagid.arithmetic
;;
;; Description:
;; -----------
;; This library provides a collection of mathematical functions written in Clojure
;; that offer arithmetic operations and properties for natural numbers.
;;
;; Goals:
;; ------
;; * Provide a simple and easy-to-use API for performing arithmetic operations in Clojure.
;; * Offer a variety of functions for analyzing and manipulating natural numbers.
;; * Be a useful tool for developers who need to perform mathematical calculations in their applications.
;;
;; Features:
;; ----------
;; * Check if a number is prime.
;; * Find prime factors of a number.
;; * Generate prime numbers up to a given limit.
;; * Calculate arithmetic properties such as the sum of divisors and the perfection of a number.
;; * Provide a word representation of a number.
;; * Calculate the scale of a number (units, tens, hundreds, etc.).
;; ---------------------------------------------------------


(ns dimagid.arithmetic
  (:require
   [clojure.pprint :as pp]
   [clojure.math :as math]
   [clojure.math.combinatorics :as combo]))

(defn prime?
  "Check if the natural number `n` is prime.

  Examples:
  (prime? 5) ;; => true
  (prime? 9) ;; => false
  "
  [n]
  (.isProbablePrime (biginteger n) 9))

(defn prime-factors
  "Returns the prime factors of a given number `n`.

  Examples:
  (prime-factors 12) ;; => [2 2 3]
  (prime-factors 51) ;; => [3 17]
  "
  [n]
  (loop [x n
         factors []]
    (if (< x 2)
      factors
      (if (prime? x)
        (conj factors x)
        (let [p (first (filter #(zero? (mod x %)) (cons 2 (range 3 (inc x) 2))))]
          (recur (/ x p) (conj factors p)))))))

(defn primes
  "Returns a vector of the first `n` prime numbers.

  Example:
  (primes 10) => [2 3 5 7 11 13 17 19 23 29]"
  [n]
  (case n
    1 [2]
    (let [x (* n 1.5)
          limit (int (Math/ceil (* x (Math/log x))))
          sieve (boolean-array limit true)
          primes (atom [])
          count-primes (atom 0)]
      (loop [i 2]
        (when (< @count-primes n)
          (when (aget sieve i)
            (swap! primes conj i)
            (swap! count-primes inc)
            (loop [j (* i i)]
              (when (< j limit)
                (aset sieve j false)
                (recur (+ j i)))))
          (if (= i 2)
            (recur 3)
            (recur (+ i 2)))))
      @primes)))

(defn primes-up-to-n
  "Returns a vector of prime numbers ≤ `n`, in ascending order.

  Example:
  (primes-up-to-n 20) ;; => [2 3 5 7 11 13 17 19]
  "
  [n]
  {:pre [(pos-int? n)]}
  (into [] (take-while #(<= % n)) (primes n)))

(defn nth-prime
  "Given a positive integer `n`, returns the nth prime number.

  Examples:
  (nth-prime 1) ;; => 2
  (nth-prime 6) ;; => 13
  (nth-prime 1000000) ;; => 15485863
  "
  [n]
  {:pre [(pos-int? n)]}
  (if (= 1 n) 2 (peek (primes n))))

(def ^:private scales ["unit" "tens" "hundreds" "thousands" "ten thousands" "hundred thousands" "millions" "ten millions" "hundred millions" "billions" "ten billions" "hundred billions" "trillions"])

(def ^:private zero-arithmetic-properties
  {:is-even-or-odd? :even,
   :perfect-square? false,
   :spellout-number "zero",
   :scale "zero",
   :divisor-type nil,
   :n 0,
   :divisor-sum 0,
   :divisors [],
   :prime-factors [],
   :perfect-cube? false,
   :prime? false})

(defn get-scale-name
  "Returns the scale of a number `n` as a string.

  Examples:
  (get-scale-name 10)  ;; => \"tens\"
  (get-scale-name 100) ;; => \"hundreds\"
  "
  [n]
  (if (zero? n)
    "zero"
    (get scales (int (math/log10 n)) "beyond trillions")))

(defn is-perfect-square-or-cube?
  "Returns true if the given number `n` is a perfect square or cube, false otherwise.
  Uses the given function `f` to calculate the square or cube root.

  Examples:
  (is-perfect-square-or-cube? 16 math/sqrt) ;; => true
  (is-perfect-square-or-cube? 27 math/cbrt) ;; => true
  (is-perfect-square-or-cube? 64 math/sqrt) ;; => true
  (is-perfect-square-or-cube? 64 math/cbrt) ;; => true
  (is-perfect-square-or-cube? 9 math/cbrt)  ;; => false
  "
  [n f]
  (let [r (f n)
        perf? (and (zero? (mod r 1)) (= n (long (apply * (repeat (if (= f math/sqrt) 2 3) r)))))]
    perf?))

(defn proper-divisors
  "Returns a sorted sequence of proper divisors from a vector of `prime-factors`.

  Example:
  (proper-divisors [2 3])  ;; => (1 2 3 6)
  "
  [prime-factors]
  (->> prime-factors
       (combo/subsets)
       (map #(apply * %))
       (sort)))

(defn number-summary
  "Computes a summary of the arithmetic properties of a natural number `n`, as a data structure.

  Example:

  {:n 16,
   :spellout-number \"sixteen\",
   :scale \"tens\",
   :is-even-or-odd? :even,
   :prime? false
   :perfect-square? true,
   :perfect-cube? false,
   :divisor-sum 31,
   :divisor-type :deficient,
   :divisors (1 2 4 8 16),
   :prime-factors [2 2 2 2],
"
  [n]
  {:pre [(or (zero? n) (pos-int? n))]}
  (if (zero? n) zero-arithmetic-properties
      (let [ret-prime-factors (prime-factors n)
            divisors (proper-divisors ret-prime-factors)
            divisor-sum (apply + divisors)
            divisor-sum-half (/ divisor-sum 2)]
        {:n n
         :spellout-number (pp/cl-format nil "~R" n)
         :scale (get-scale-name n)
         :divisors divisors
         :is-even-or-odd? (if (even? n) :even :odd)
         :divisor-sum divisor-sum
         :divisor-type (cond
                         (= n divisor-sum-half) :perfect
                         (< n divisor-sum-half) :abundant
                         :else :deficient)
         :prime-factors ret-prime-factors
         :prime? (= ret-prime-factors (vector n))
         :perfect-square? (is-perfect-square-or-cube? n math/sqrt)
         :perfect-cube? (is-perfect-square-or-cube? n math/cbrt)})))
