(ns app.movie-spec
    (:require
        [cljs.test :as t :refer [async deftest is testing]]
        [app.movie :as movie]
        [adapters.driven.movie-service :as movie-service]))

(deftest no-movie-found
    (testing "get 0 movie"
        (is (== nil (movie/list-movies (movie-service/fake-srv nil))))))

(deftest get-wun-movie
    (testing "get 1 movie"
        (let [expected (list {:title "The Godfather"})]
            (is (== expected (movie/list-movies (movie-service/fake-srv expected)))))))

(deftest get-two-movies
    (testing "get 2 movies"
        (let [expected (list {:title "Inception"} {:title "The Godfather"})]
            (is (== expected (movie/list-movies (movie-service/fake-srv expected)))))))

(deftest get-three-movies
    (testing "get 3 movies"
        (let [expected (list {:title "Inception"} {:title "The Godfather"} {:title "Matrix"})]
            (is (== expected (movie/list-movies (movie-service/fake-srv expected)))))))

(deftest prevent-from-getting-movie-when-was-not-found-in-collection
    (testing "prevent from getting movie when was not found in collection"
        (let [expected {:id 1 :title "Inception"}]
            (is (thrown-with-msg? js/Error #"Movie was not found." (movie/get-movie 10 (movie-service/fake-srv (list {:id 1 :title "Inception"} {:id 3 :title "The Godfather"} {:id 2 :title "Matrix"}))))))))

(deftest get-first-movie-of-the-list
    (testing "get movie #1"
        (let [expected {:id 1 :title "Inception"}]
            (is (== expected (movie/get-movie 1 (movie-service/fake-srv (list {:id 1 :title "Inception"} {:id 3 :title "The Godfather"} {:id 2 :title "Matrix"}))))))))

(deftest get-second-movie-of-the-list
    (testing "get movie #2"
        (let [expected {:id 2 :title "The Godfather"}]
            (is (== expected (movie/get-movie 2 (movie-service/fake-srv (list {:id 1 :title "Inception"} {:id 2 :title "The Godfather"} {:id 3 :title "Matrix"}))))))))
;; print name of each test
(defmethod t/report [:cljs.test/default :begin-test-var] [m]
    (println "===" (-> m :var meta :name))
    (println))

;; run this function with: nbb -m example/run-tests
(defn run-tests []
    (t/run-tests 'app.movie-spec))
