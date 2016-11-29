(ns uusitsoha.controllers.questionc
  ;(:use ring.util.response)
  (:require [compojure.core :refer [defroutes GET POST DELETE]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [uusitsoha.models.question :as model]))

(defn create [req]
  (let [new-object-id (model/create-new req)
        route (str "/" new-object-id)]
    (ring/redirect route)))

(defn all []
  (model/all))

(defn delete [id]
  (model/delete id)
  (ring/redirect "/"))

(defn show [id]
  (model/show id))

(defn update-with-id [id req]
  (model/update-id id req)
  (ring/redirect (str "/" id)))

;question routes
;funktio read-string on ilmeisen epäturvallinen, täytyisi vaihtaa :c
(defroutes routes
  (GET  "/" [] (all))
  (POST "/" request (create (:params request)))
  (DELETE "/:id" [id] (delete id))
  (GET "/:id" [id] (show id))
  (POST "/update/:id" [id request] (update-with-id id request))
  )
