(ns adapters.driving.server
    (:require ["fastify$default" :as fastify]
        [applied-science.js-interop :as j]
        [app.movie :as movie]
        [app.director :as director]
        [adapters.driven.movie-service :as ms]
        [adapters.driven.director-service :as ds]
        ))

(def server (fastify (j/lit {:logger true})))

(defn handler-movies [req res]
    (let [movies (movie/list-movies ms/get-from-file)]
        (.send res (clj->js movies))))

(defn handler-wun-movie [req res]
    (let [
        id (int (j/get req.params :id))
        movie (movie/get-movie id ms/get-from-file)]
    (.send res (clj->js movie))))

(defn handler-directors [req res]
    (let [directors (director/list-directors ds/get-from-file)]
        (.send res (clj->js directors))))

(defn handler-wun-director [req res]
    (let [
        id (int (j/get req.params :id))
        movie (director/get-director id ds/get-from-file)]
    (.send res (clj->js movie))))

(.route server #js{:method "GET" :url "/" :handler (fn [req res] (.send res "Hello World"))})
(.route server #js{:method "GET" :url "/movies" :handler handler-movies})
(.route server #js{:method "GET" :url "/movies/:id" :handler handler-wun-movie})
(.route server #js{:method "GET" :url "/directors" :handler handler-directors})
(.route server #js{:method "GET" :url "/directors/:id" :handler handler-wun-director})

(defn -main []
    (.listen server #js{:port 3000} (fn [err address] (if err (println "Error starting server") (println "Server started on port 3000")))))
