(ns astar.core
  (:require [clojure.data.priority-map :refer [priority-map-keyfn]]))

(defn ^:private generate-route [node came-from]
  (loop [route '()
         node node]
    (if node
      (recur (cons node route) (came-from node))
      (rest route))))

(defn route 
  "Finds the shortest route from start to goal in a graph.
  Graph is a function (eg. a map) from nodes to a collection of adjacent nodes.
  Dist is a function from two nodes to the distance (as a number) from the first node to the second.
  H is a function from a node to the heuristic distance from that node to the goal. It should never overestimate the distance.
  Start and goal are two nodes.
  Returns a list of nodes on the route, excluding the start node and including the goal node. If a route can't be found, returns nil."
  [graph dist h start goal]
  (loop [visited {}
         queue (priority-map-keyfn first start [0 0 nil])]
    (if (empty? queue)
      nil
      (let [[current [_ current-score previous]] (peek queue)
            visited (assoc visited current previous)
            queue (reduce (fn [queue node]
                            (let [score (+ current-score (dist current node))] 
                              (if (and (not (contains? visited node))
                                       (or (not (contains? queue node)) 
                                           (< score (get-in queue [node 1]))))
                                (assoc queue node [(+ score (h node)) score current])
                                queue)))
                          (pop queue)
                          (graph current))]
        (if (= current goal)
          (generate-route goal visited)
          (recur visited queue))))))