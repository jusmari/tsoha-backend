(ns uusitsoha.controllers.user_controller
  (:use ring.util.response)
  (:require [compojure.core :refer [defroutes context GET POST DELETE]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [uusitsoha.models.user :as model]
            [uusitsoha.models.membership :as ms_model]))

;(b/valid? valid? [req]
;  [:name :password] v/required)

(defn redirect-id [id]
  (str "/users/" id))

;(defn try-create [req]
;  (if (valid? req)
;    (create req)
;    "Error"))

(defn add-organization [params]
  )

(defn correct-password? [username password]
  (= (model/get-password username) password))


(defn unique-username? [name]
  (empty? (model/check-username)))

(defn create [params]
  (if (unique-username? (:name params))
    (let [new-object-id (model/create-new params)]
      (ring/redirect (redirect-id new-object-id)))
    (response "Username already used")))


(defn all []
  (model/all))

(defn delete [id]
  (model/delete id)
  (ring/redirect "/"))

(defn show [id]
  (model/show id))

(defn update-with-id [id req]
  (model/update-id id req)
  (ring/redirect (redirect-id id)))

;reitit
(defroutes routes
  (context "/users" []
    (GET  "/" [] (all))
    (POST "/" request (create (:params request)))
    (DELETE "/:id" [id] (delete id))
    (GET "/:id" [id] (show id))
    (POST "/:id/update/" [id request] (update-with-id id request))))
