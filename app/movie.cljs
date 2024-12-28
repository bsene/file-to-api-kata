(ns app.movie)
(defn list-movies [srv] (srv))

(defn get-movie [id srv]
    (let [found (filter (fn [m] (= (get m :id) id)) (srv))]
        (print id)
        (if (empty? found)
            (throw (js/Error. "Movie was not found."))
            (first found))))
