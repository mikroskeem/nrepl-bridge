(ns eu.mikroskeem.clj.nrepl.NreplBridge
  (:use [nrepl.server :refer [start-server stop-server]])
  (:gen-class
    :state state
    :init init
    :methods [[bootServer [String int] void]
              [isRunning [] boolean]
              [stopServer [] void]]))

(defn -init []
  [[] (atom {})])

(defn -bootServer [this host port]
  (swap! (.state this) into {:server (start-server :bind host :port port)}))

(defn -stopServer [this]
  (if (contains? @(.state this) :server)
    (do 
      (stop-server (@(.state this) :server))
      (swap! (.state this) into {}))))

(defn -isRunning [this] (contains? @(.state this) :server))

(defn -main [& args]
  (println "nrepl-bridge is a library, not application")
  (java.lang.System/exit 1))
