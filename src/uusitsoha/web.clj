(ns uusitsoha.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.params :refer [wrap-params]]
            ;[ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [uusitsoha.controllers.questionc :as questions]
            [uusitsoha.migrations.m_migrate :as db])
  (:gen-class))

(defroutes routes
  questions/routes
  (route/resources "/")
  (route/not-found "Not Found"))

;ei mitään hajua mitä nää tekee, mutta toimii :ccc
(def application
  (-> (handler/api routes)
    ;(wrap-reload)
    (wrap-keyword-params)
    (wrap-json-params {})
    (wrap-params {})
    (wrap-json-response {:pretty true})))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (db/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))
