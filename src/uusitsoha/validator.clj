(ns uusitsoha.validator)

(defn error [input]
  "Jos havaitaan virhe, palauttaa 'Error', eikä koske tietokantaan"
  (throw (Exception. (str "Validation error with input: " input))))

(defn string-blank? [input]
  "Tarkistaa, onko syötteessä sisältöä"
  (if (clojure.string/blank? input)
    (error input)
    input))
