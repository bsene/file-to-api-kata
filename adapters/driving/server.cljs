(ns adapters.driving.server
    (:require ["fastify$default" :as fastify]
        [applied-science.js-interop :as j]
        [app.movie :as movie]
        [adapters.driven.movie-service :as ms]))

(def server (fastify (j/lit {:logger true})))

(defn handler-movies [req res]
    (let [movies (movie/list-movies ms/get-from-file)]
        (.send res movies)))

(.route server #js{:method "GET" :url "/" :handler (fn [req res] (.send res "Hello World"))})
(.route server #js{:method "GET" :url "/movies" :handler handler-movies})

(defn -main []
(.listen server #js{:port 3000} (fn [err address] (if err (println "Error starting server") (println "Server started on port 3000")))))
