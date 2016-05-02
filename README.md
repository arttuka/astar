# astar
Pathfinding using A* algorithm

## Releases and Dependency Information

Latest release is [0.2]

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[astar-search "0.2"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>astar-search</groupId>
  <artifactId>astar-search</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Usage

`astar.core/route` takes the following parameters:

* `graph`, which is a function (eg. a map) from a node to all neighbors of that node.

```clojure
user=> (def graph {:a [:b :c]
                   :b [:a :d :e]
                   :c [:a :d :e]
                   :d [:b :c :f]
                   :e [:b :c :f]
                   :f [:e :d]})
#'user/graph
```

* `dist`, which is a function from two nodes to the distance (as a number) from the first node to the second.

```clojure
user=> (defn dist [from to]
         (let [d {:a {:b 4 :c 2}
                  :b {:a 4 :d 1 :e 1}
                  :c {:a 2 :d 3 :e 5}
                  :d {:b 1 :c 3 :f 3}
                  :e {:b 1 :c 5 :f 2}
                  :f {:d 3 :e 2}}]
           (get-in d [from to])))
#'user/dist
```

* `h`, which is a function from a node to the heuristic distance from that node to the goal. It should never overestimate the distance. If it does, the algorithm isn't guaranteed to find the optimal solution.

```clojure
user=> (def h {:a 7
               :b 3
               :c 6
               :d 3
               :e 2
               :f 0})
#'user/h
```

* `start`, which is the starting node for the search
* `goal`, which is the goal node for the search.

It returns a list of nodes that form the shortest path from the start node (exclusive) to the goal node (inclusive).

```clojure
user=> (require 'astar.core)
nil
user=> (astar.core/route graph dist h :a :f)
(:b :e :f)
```

If a route doesn't exist, it returns `nil`.

