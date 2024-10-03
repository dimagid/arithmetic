# dimagid/arithmetic

## Overview
dimagid/arithmetic is a Clojure library that provides a collection of mathematical functions for performing arithmetic operations and analyzing natural numbers.

## Features
- **Check if a number is prime**: This function checks if a given number is prime by verifying that it is divisible only by 1 and itself.
- **Find prime factors of a number**: This function returns the prime factors of a given number.
- **Generate prime numbers up to a given limit**: This function generates a sequence of prime numbers up to a given limit.
- **Calculate arithmetic properties**: This function calculates various arithmetic properties of a given number, such as the sum of divisors and the perfection of the number.

## Usage
This library can be used in any Clojure project that requires arithmetic operations. Simply import the desired functions and use them directly in your code.

### deps.edn dependency information:
```clojure
io.github.dimagid/arithmetic {:git/tag "v0.0.1" :git/sha "03b14a4"}
```

### Leiningen dependency information:
TODO:

## Examples

### Check if a number is prime
```clojure
(prime? 5) => true
(prime? 9) => false
```

### Find prime factors of a number
```clojure
(prime-factors 12) => [2 2 3]
(prime-factors 51) => [3 17]
```

### Generate the first n prime numbers
```clojure
(primes 10) => [2 3 5 7 11 13 17 19 23 29]
```

### Calculate arithmetic properties
```clojure
(number-summary 16) => {:n 16, :spellout-number "sixteen", :scale "tens", :is-even-or-odd? :even, :prime? false, :perfect-square? true, :perfect-cube? false, :divisor-sum 31, :divisor-type :deficient, :divisors (1 2 4 8 16), :prime-factors [2 2 2 2]}
```
