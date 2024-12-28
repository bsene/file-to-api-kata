(ns app.movie-spec
    (:require
        [cljs.test :as t :refer [async deftest is testing]]
        [app.movie :as movie]
        [adapters.driven.movie-service :as movie-service]))

(deftest no-movie-found

    (testing "get 0 movie"
        (is (== nil (movie/list-movies (movie-service/fake-srv nil)))))
    )
(deftest get-wun-movie
    (testing "get 1 movie"
        (let [expected (list {:title "The Godfather"})]

            (is (== expected (movie/list-movies (movie-service/fake-srv expected))))))
    )

(deftest get-two-movies
    (testing "get 2 movies"
        (let [expected (list {:title "Inception"} {:title "The Godfather"})]

            (is (== expected (movie/list-movies (movie-service/fake-srv expected))))))
    )

(deftest get-three-movies
    (testing "get 3 movies"
        (let [expected (list {:title "Inception"} {:title "The Godfather"} {:title "Matrix"})]

            (is (== expected (movie/list-movies (movie-service/fake-srv expected))))))
    )

;; print name of each test
(defmethod t/report [:cljs.test/default :begin-test-var] [m]
    (println "===" (-> m :var meta :name))
    (println))

;; run this function with: nbb -m example/run-tests
(defn run-tests []
    (t/run-tests 'app.movie-spec))
