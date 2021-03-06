(ns uusitsoha.models.user
  (:use ring.util.response)
  (:require [clojure.java.jdbc :as sql]
            [crypto.password.pbkdf2 :as password]))

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
        ["SELECT * FROM users ORDER BY name ASC LIMIT 128"]))))

(defn check-username [username]
    (into []
      (sql/query
        spec
        ["SELECT name FROM users WHERE name = ?" username])))

(defn get-password [username]
  (password/check
    (first
      (into []
        (sql/query
          spec
          ["SELECT password FROM users WHERE id = ? and name = ?" name])))))


;palauta yksi käyttäjä
(defn show [id]
  (response
    (into []
      (sql/query
        spec
        ["SELECT * FROM users WHERE id = ?" id]))))

;taulussa olevien käyttäjien määrä
(defn count-query []
   (into []
     (sql/query
       spec
       ["SELECT COUNT(*) FROM users"])))

(defn qcount []
  (-> (count-query) first :count))

;uuden käyttäjän luonti
(defn create-new [req]
    (let [name (str (:name req))
          pw (password/encrypt (str (:password req)))
          id (uuid)]
       (sql/execute!
         spec
         ["INSERT INTO users (id, name, password) VALUES (?, ?, ?)" id name pw])
         id))

;kysymyksen poistaminen
(defn delete [id]
  (response
    (sql/execute!
      spec
      ["DELETE FROM users WHERE id = ?" id])))

(defn update-id [id req]
  (sql/execute!))

;repliä varten
(comment
  (require 'uusitsoha.models.question)(in-ns 'uusitsoha.models.question)
)
