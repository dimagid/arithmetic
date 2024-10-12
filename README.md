# dimagid/arithmetic

## Overview
dimagid/arithmetic is a Clojure library that provides a collection of mathematical functions for performing arithmetic operations and analyzing natural numbers. Provide functions like `number-summary` that computes a summary of the arithmetic properties of a natural number, as a data structure.

## Goals of dimagid/arithmetic

- Provide a simple and easy-to-use API for performing arithmetic operations in Clojure.
- Offer a variety of functions for analyzing and manipulating natural numbers.

## Add as a dependency

Add this library as a dependency to your Clojure project to access its functions.

### deps.edn dependency information:
```clojure
io.github.dimagid/arithmetic {:git/tag "v0.0.5" :git/sha "d806b52"}
```

### Leiningen dependency information:
```clojure
:dependencies [[io.github.dimagid/arithmetic "v0.0.5" :git/url "https://github.com/dimagid/arithmetic.git" :git/sha "d896b52"]]
```

## Usage

### To load in REPL:
```clojure
(require '[dimagid.arithmetic :as a])
```

### To load in namespaces:
```clojure
(ns my-namespace
  (:require [dimagid.arithmetic :as a]))
```

## Simple examples

### Check if a number is prime
```clojure
(a/prime? 5) ;; => true

(a/prime? 9) ;; => false
```

### Find prime factors of a number
```clojure
(a/prime-factors 12) ;; => [2 2 3]

(a/prime-factors 51) ;; => [3 17]
```

### Generate the first n prime numbers
```clojure
(a/primes 10) ;; => [2 3 5 7 11 13 17 19 23 29]
```

### Find the nth prime numbers
```clojure
  (nth-prime 1) ;; => 2
  (nth-prime 6) ;; => 13
  (nth-prime 1000000) ;; => 15485863
```

### Calculate arithmetic properties
```clojure
(a/number-summary 16)
=> {:n 16,
    :spellout-number "sixteen",
    :scale "tens",
    :is-even-or-odd? :even,
    :prime? false,
    :perfect-square? true,
    :perfect-cube? false,
    :divisor-sum 31,
    :divisor-type :deficient,
    :divisors (1 2 4 8 16),
    :prime-factors [2 2 2 2]}
```
