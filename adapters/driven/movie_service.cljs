(ns adapters.driven.movie-service
    (:require ["fs" :as fs]
        ))

(defn fake-srv [movies] (fn [] movies))

(defn get-from-file []
    (let [data (js/JSON.parse (fs/readFileSync "data.json"))]
        (js->clj data.movies :keywordize-keys true)))
