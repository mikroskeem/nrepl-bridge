(defproject nrepl-bridge "0.0.1-SNAPSHOT"
  :description "Launch nREPL from Java"
  :url "https://github.com/mikroskeem/nrepl-bridge"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl "0.6.0"]]
  :plugins [[lein-cloverage "1.0.13"]
            [lein-shell "0.5.0"]
            [lein-ancient "0.6.15"]
            [lein-changelog "0.3.2"]]
  :aot [eu.mikroskeem.clj.nrepl.NreplBridge]
  :deploy-repositories [["releases" "https://repo.wut.ee/repository/vapeout-repo"]
                        ["snapshots" "https://repo.wut.ee/repository/vapeout-repo"]]
  :aliases {"update-readme-version" ["shell" "sed" "-i" "s/\\\\[nrepl-bridge \"[0-9.]*\"\\\\]/[nrepl-bridge \"${:version}\"]/" "README.md"]}
  :release-tasks [["shell" "git" "diff" "--exit-code"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["changelog" "release"]
                  ["update-readme-version"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["deploy"]
                  ["vcs" "push"]])
