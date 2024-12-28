(ns app.director)
(defn list-directors [srv] (srv))

(defn get-director [id srv]
    (let [found (filter (fn [m] (= (get m :id) id)) (srv))]
        (print id)
        (if (empty? found)
            (throw (js/Error. "Director was not found."))
            (first found))))
