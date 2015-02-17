(defproject lein-antlr "0.3.0"
  :description "Generate source code from ANTLR grammars in Leiningen."
  :dependencies [[org.antlr/antlr4 "4.5"]]
  :profile {:dev {:dependencies [[org.clojure/clojure "1.4.0"]]}}
  :url "http://github.com/alexhall/lein-antlr"
  :eval-in-leiningen true
  :license {:name "Apache Software License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"
			:distribution :repo})
