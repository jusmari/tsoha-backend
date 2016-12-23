(ns uusitsoha.controllers.membership_controller
  (:require [uusitsoha.models.membership :as model]))

(defn create [params]
  (model/create params))

(defn find-by-user-id [user-id]
  model/find-by-id [user-id])
