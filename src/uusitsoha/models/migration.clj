(comment (ns uusitsoha.models.migration
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/tsoha"))

(defn migrated? []
  (-> (sql/query spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='questions'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands spec
                        (sql/create-table-ddl
                         :questions
                         [[:id :varchar "PRIMARY KEY"]
                         [:body :varchar "NOT NULL"]
                         [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))



    ;(sql/insert! "postgresql://localhost:5432/tsoha"
    ;                    :questions {:body "Hello World"})
)
