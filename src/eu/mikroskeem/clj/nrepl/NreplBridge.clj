(ns eu.mikroskeem.clj.nrepl.NreplBridge
  (:use [nrepl.server :refer [start-server stop-server]])
  (:gen-class
    :state state
    :init init
    :methods [[bootServer [String int] void]
              [isRunning [] boolean]
              [stopServer [] void]]))

(defn -init
  "Initializes the NreplBridge wrapper class"
  [] 
  [[] (atom {})])

(defn -isRunning [this]
  "Returns whether nREPL server is running or not"
  (and
    (contains? @(.state this) :server)
    (some? (@(.state this) :server))))

(defn -bootServer
  "Boots up the nREPL server on TCP host & port"
  [this host port]
  (if (-isRunning this)
    (throw (IllegalStateException. "nREPL server is already running"))
    (swap! (.state this) into {:server (start-server :bind host :port port)})))

(defn -stopServer
  "Shuts down the nREPL server"
  [this]
  (if (-isRunning this)
    (do
      (stop-server (@(.state this) :server))
      (swap! (.state this) into {:server nil}))
    (throw (IllegalStateException. "nREPL server is not running"))))

(defn -main [& args]
  (println "nrepl-bridge is a library, not application")
  (java.lang.System/exit 1))
