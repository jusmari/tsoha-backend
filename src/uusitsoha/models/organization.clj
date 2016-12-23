(ns uusitsoha.models.organization
  (:use ring.util.response)
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/tsoha"))

(defn uuid []
  (str (java.util.UUID/randomUUID)))

;kaikkien käyttäjien haku
(defn all []
  (response
    (into []
      (sql/query
        spec
        ["SELECT * FROM organizations ORDER BY name ASC LIMIT 128"]))))

;palauta yksi käyttäjä
(defn show [id]
  (response
    (into []
      (sql/query
        spec
        ["SELECT * FROM organizations WHERE id = ?" id]))))

;taulussa olevien käyttäjien määrä
(defn count-query []
   (into []
     (sql/query
       spec
       ["SELECT COUNT(*) FROM organizations"])))

(defn qcount []
  (-> (count-query) first :count))

;uuden käyttäjän luonti
(defn create-new [req]
    (let [name (str (:name req))
          id (uuid)]
       (sql/execute!
         spec
         ["INSERT INTO organizations (id, name) VALUES (?, ?)" id name])
         id))

;kysymyksen poistaminen
(defn delete [id]
  (response
    (sql/execute!
      spec
      ["DELETE FROM organizations WHERE id = ?" id])))

(defn update-id [id req]
  (sql/execute!))

;repliä varten
(comment
  (require 'uusitsoha.models.question)(in-ns 'uusitsoha.models.question)
)
