(ns adapters.driving.console
  (:require
           [app.movie :as movie]
           [adapters.driven.movie-service :as movie-service]
            ))


(def resp (movie/list-movies movie-service/get-from-file))


(defn -main []
  (js/console.log resp))
