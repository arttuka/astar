(defproject astar-search "0.2"
  :description "Pathfinding using A* algorithm"
  :url "https://github.com/arttuka/astar"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/data.priority-map "0.0.7"]
                 [tailrecursion/cljs-priority-map "1.1.0"]]
  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-doo "0.1.6"]]
  :aliases {"test-cljs" ["doo" "node" "test" "once"]
            "test-all"  ["do" ["test"] ["test-cljs"]]}
  :cljsbuild {:builds
              {:test
               {:source-paths ["src" "test"]
                :compiler {:output-to "target/main.js"
                           :output-dir "target"
                           :main astar.test-runner
                           :optimizations :simple
                           :target :nodejs}}}})
