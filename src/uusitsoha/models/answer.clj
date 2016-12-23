(ns uusitsoha.models.membership
  (:use ring.util.response)
  (:require [clojure.java.jdbc :as sql]))

(defn uuid []
  (str (java.util.UUID/randomUUID)))

(defn create-new [req]
    (let [user_id (str (:user_id req))
          org_id (str (:org_id req))
          id (uuid)]
       (sql/execute!
         spec
         ["INSERT INTO users (id, user_id, organization_id) VALUES (?, ?, ?)" id user_id org_id])))

(defn delete-by-user_id [user_id]
  (sql/execute!
    spec
    ["DELETE FROM membership WHERE id = ?" user_id]))

(defn delete-by-id [id]
    (sql/execute!
      spec
      ["DELETE FROM membership WHERE id = ?" id]))
