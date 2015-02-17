;;  Copyright 2010 Revelytix, Inc.
;;
;;  Licensed under the Apache License, Version 2.0 (the "License");
;;  you may not use this file except in compliance with the License.
;;  You may obtain a copy of the License at
;;
;;      http://www.apache.org/licenses/LICENSE-2.0
;;
;;  Unless required by applicable law or agreed to in writing, software
;;  distributed under the License is distributed on an "AS IS" BASIS,
;;  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;;  See the License for the specific language governing permissions and
;;  limitations under the License.

(ns leiningen.test-antlr
  (:use [leiningen.antlr :only (antlr)]
        [leiningen.clean :only (delete-file-recursively)]
        [clojure.test])
  (:import [java.io File]))

(def antlr-src-dir "antlr")
(def antlr-out-dir "antlr-out")

(defn antlr-project [d]
  {:antlr-src-dir (str antlr-src-dir \/ d)
   :antlr-dest-dir (str antlr-out-dir \/ d)})

(when (.exists (File. antlr-out-dir)) (delete-file-recursively antlr-out-dir false))

(defn out-file [f] (File. antlr-out-dir f))
(defn out-file-exists [f] (.exists (out-file f)))

(deftest test-antlr-compile
  (let [result (with-out-str (antlr (antlr-project "test")))]
    (is (true? (.startsWith result "Compiling ANTLR grammars"))))
  (are [x] (true? (out-file-exists x))
       "test/SimpleCalc.tokens"
       "test/SimpleCalcLexer.java"
       "test/SimpleCalcParser.java"
       "test/paren/ParenCalc.tokens"
       "test/paren/ParenCalcLexer.java"
       "test/paren/ParenCalcParser.java")
  (comment
    "ANTLR3 used timestamps on generated source files to avoid re-processing grammars
     that had not changed. ANTLR4 appears to re-process the grammar each time."
    (let [timestamp (.lastModified (out-file "test/SimpleCalcLexer.java"))]
      (Thread/sleep 2000)
      (antlr (antlr-project "test"))
      (is (= timestamp (.lastModified (out-file "test/SimpleCalcLexer.java")))))))

(deftest test-antlr-invalid
  (is (thrown? RuntimeException (antlr (antlr-project "test-invalid"))))
  (is (false? (out-file-exists "test-invalid/InvalidCalcLexer.java"))))

(deftest test-suffix
  (antlr (antlr-project "test-suffix"))
  (are [x] (true? (out-file-exists x))
    "test-suffix/SimpleCalc.tokens"
    "test-suffix/SimpleCalcLexer.java"
    "test-suffix/SimpleCalcParser.java"))
