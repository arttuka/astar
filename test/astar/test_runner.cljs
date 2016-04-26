(ns astar.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [astar.core-test]))

(doo-tests 'astar.core-test)
