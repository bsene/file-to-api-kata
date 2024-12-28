(ns adapters.driving.console
  (:require
           [app.movie :as movie]
           [adapters.driven.movie-service :as movie-service]
            ))


(def resp (movie/get-movie 4 movie-service/get-from-file))


(defn -main []
    (println (movie/list-movies movie-service/get-from-file))
    (println resp))
