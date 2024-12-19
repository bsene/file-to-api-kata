(import test)
(load "app/movie.scm")
(load "adapters/driven/movie_service.scm")

(test "get 0 movie"
    '() (list-movies (fake-srv '())))

(test "get 1 movie"
    '((title "The Godfather")) (list-movies (fake-srv '((title "The Godfather")))))

(test "get 2 movies"
    '((title "Inception") (title "The Godfather")) (list-movies (fake-srv '((title "Inception") (title "The Godfather")))))

(test "get 3 movies"
    '((title "Inception") (title "The Godfather") (title "Matrix")) (list-movies (fake-srv '((title "Inception") (title "The Godfather") (title "Matrix")))))

(test-exit)
