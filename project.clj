(defproject uusitsoha "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [compojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [cheshire "5.6.3"]
                 [crypto-password "0.2.0"]
                 [bouncer "1.0.0"]
                 ;[org.slf4j/slf4j-log4j12 "1.7.21"] ???????
                 [migratus "0.8.32"]
                 ;[ring/ring-core "1.5.0"]
                 [ring/ring-json "0.4.0"]]
  :plugins [[lein-ring "0.9.7"]
            [migratus-lein "0.4.1"]]
  :ring {:handler uusitsoha.web/application
         :init uusitsoha.migrations.m_migrate/migrate}
  :main ^:skip-aot uusitsoha.web
  :uberjar-name "uusitsoha-0.1.0-SNAPSHOT-standalone.jar"
  :migratus {:store :database
             :migration-dir "uusitsoha/migrations/"
             :db (or (System/getenv "DATABASE_URL")
                           "postgresql://localhost:5432/tsoha")}
  :profiles
  {:dev
    {:dependencies [[ring/ring-mock "0.3.0"]
                        [javax.servlet/servlet-api "2.5"]
                        [org.clojure/java.jdbc "0.6.1"]
                        [org.postgresql/postgresql "9.4-1201-jdbc41"]
                        [ring/ring-jetty-adapter "1.5.0"]
                        [compojure "1.5.1"]]}
                        :uberjar {:aot :all}})
