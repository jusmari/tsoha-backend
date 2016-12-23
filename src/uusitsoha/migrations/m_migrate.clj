(ns uusitsoha.migrations.m_migrate
 (:require [migratus.core :as migratus]))

 (def spec (or (System/getenv "DATABASE_URL")
               "postgresql://localhost:5432/tsoha"))

 (def config {:store                :database
              :migration-dir        "uusitsoha/migrations/"
              :init-script          "/init.sql"
              :migration-table-name "schema_migrations"
              :db spec})

(defn migrate []
  (migratus/init config))
