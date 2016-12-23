(ns uusitsoha.models.membership
  (:use ring.util.response)
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/tsoha"))

(defn uuid []
  (str (java.util.UUID/randomUUID)))

(defn all []
  "etsii kaikki jäsenyydet"
  (response
    (into []
      (sql/query
        spec
        ["SELECT * FROM memberships ORDER BY name ASC LIMIT 128"]))))

(defn create-new [req]
    (let [user_id (:user_id req)
          org_id (:user_id req)
          id (uuid)]
       (sql/execute!
         spec
         ["INSERT INTO users (user_id, org_id) VALUES (?, ?)" user_id org_id])))
