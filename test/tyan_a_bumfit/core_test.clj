(ns tyan-a-bumfit.core-test
  (:require [clojure.test :refer :all]
            [tyan-a-bumfit.core :refer :all]
            [com.gfredericks.seventy-one :refer [seventy-one]]))

(deftest test-71
  (testing "71"
    (is
      (and
        (= seventy-one (eval (xp 20 seventy-one)))
        (= seventy-one (eval (xp 10 seventy-one)))
        (= seventy-one (eval (xp 3  seventy-one)))))))