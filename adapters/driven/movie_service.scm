(import (chicken file posix) json)
(define (fake-srv movies) (lambda () movies))

(define (movies-srv )
    (let ((f (open-input-file "data.json")))


        (json-read f)))
