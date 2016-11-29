(ns uusitsoha.models.question
  (:use ring.util.response)
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/tsoha"))

(defn uuid []
  (str (java.util.UUID/randomUUID)))

;kaikkien kysymyksien haku
(defn all []
  (response
    (into []
      (sql/query
        spec
        ["SELECT * FROM questions ORDER BY id ASC LIMIT 128"]))))

;palauta yksi kysymys
(defn show [id]
  (response
    (into []
      (sql/query
        spec
        ["SELECT * FROM questions WHERE id = ?" id]))))

;taulussa olevien kysymysten määrä
(defn count-query []
   (into []
     (sql/query
       spec
       ["SELECT COUNT(*) FROM questions"])))

(defn qcount []
  (-> (count-query) first :count))

;uuden kysymyksen luonti
(defn create-new [req]
    (let [name (str (:name req))
          id (uuid)]
       (sql/execute!
         spec
         ["INSERT INTO questions (id, body) VALUES (?, ?)" id name])
         id))

;kysymyksen poistaminen
(defn delete [id]
  (response
    (sql/execute!
      spec
      ["DELETE FROM questions WHERE id = ?" id])))

(defn update-id [id req]
  (sql/execute!))

;repliä varten
(comment
  (require 'uusitsoha.models.question)(in-ns 'uusitsoha.models.question)
)
