(ns nrepl-bridge.tests
  (:use [eu.mikroskeem.clj.nrepl.NreplBridge]
        [clojure.test]))

(defn new-bridge []
  (eu.mikroskeem.clj.nrepl.NreplBridge.))

(defn boot [n]
  (.bootServer n "127.0.0.1" 1337))

(deftest test-start-stop []
  (let [n (new-bridge)]
    ; By default, server shouldn't be running
    (is (not (.isRunning n)))
    ; Boot the server
    (boot n)
    ; Server should be running now
    (is (.isRunning n))
    ; Stop the server
    (.stopServer n)
    ; Server reference should be set to nil now
    (is (not (.isRunning n)))))

(deftest test-double-start []
  (let [n (new-bridge)]
    (boot n)
    (is (.isRunning n))
    (is (thrown-with-msg? IllegalStateException #"nREPL server is already running"
                          (boot n)))))
