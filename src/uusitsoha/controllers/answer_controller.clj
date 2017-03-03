(ns uusitsoha.controllers.answer_controller
  (:use ring.util.response)
  (:require [compojure.core :refer [defroutes context GET POST DELETE]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [uusitsoha.models.answer :as model]))

(defn redirect-id [id]
  (str "/answers/" id))

(defn create [req]
  (let [new-object-id (model/create-new req)]
    (ring/redirect (redirect-id new-object-id))))

(defn all []
  (model/all))

(defn delete [id]
  (model/delete-by-id id)
  (ring/redirect "/answers/"))

(defn show [id]
  (model/show id))

(defn update-with-id [id req]
  (model/update-id id req)
  (ring/redirect (redirect-id id)))

;routes
(defroutes routes
  (context "/answers" []
    (GET  "/" [] (all))
    (POST "/" request (create (:params request)))
    (DELETE "/:id" [id] (delete id))
    (GET "/:id" [id] (show id))
    (POST "/update/:id" [id request] (update-with-id id request))))
