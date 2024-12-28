(ns adapters.driven.director-service
    (:require ["fs" :as fs]))
(defn get-from-file []
    (let [data (js/JSON.parse (fs/readFileSync "data.json"))]
        (js->clj data.directors :keywordize-keys true)))
