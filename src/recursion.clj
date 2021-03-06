(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
  (and (not (empty? coll))
       (empty? (rest coll))))

(defn my-last [coll]
  (cond (empty? coll) nil
        (singleton? coll) (first coll)
        :else (my-last (rest coll))))

(defn max-element [a-seq]
  (cond (empty? a-seq) nil
        (singleton? a-seq) (first a-seq)
        :else (max (first a-seq)
                   (max-element (rest a-seq)))))

(defn seq-max [seq-1 seq-2]
  (if (> (count seq-1)
         (count seq-2))
    seq-1
    seq-2))

(defn longest-sequence [a-seq]
  (cond (empty? a-seq) nil
        (singleton? a-seq) (first a-seq)
        :else (seq-max (first a-seq)
                       (longest-sequence (rest a-seq)))))

(defn my-filter [pred? a-seq]
  (cond (empty? a-seq) a-seq
        (pred? (first a-seq)) (cons (first a-seq)
                                    (my-filter pred? (rest a-seq)))
        :else (my-filter pred? (rest a-seq))))

(defn sequence-contains? [elem a-seq]
  (cond (empty? a-seq) false
        (= elem (first a-seq)) true
        :else (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (cond (empty? a-seq) a-seq
        (pred? (first a-seq)) (cons (first a-seq)
                                    (my-take-while pred? (rest a-seq)))
        :else '()))

(defn my-drop-while [pred? a-seq]
  (cond (empty? a-seq) a-seq
        (pred? (first a-seq)) (my-drop-while pred? (rest a-seq))
        :else a-seq))

(defn seq= [a-seq b-seq]
  (cond (empty? a-seq) (empty? b-seq)
        (empty? b-seq) (empty? a-seq)
        (and (= (first a-seq)
                (first b-seq))) (seq= (rest a-seq)
                                      (rest b-seq))
                :else false))

(defn my-map [f seq-1 seq-2]
  (if (or (empty? seq-1)
          (empty? seq-2))
    '()
    (cons (f (first seq-1)
             (first seq-2))
          (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (cond (= n 0) 0
        (= k 0) 1
        :else (* n (power n (dec k)))))

(defn fib [n]
  (if (<= n 1) n
      (+ (fib (dec n))
         (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (<= how-many-times 0) '()
      (cons what-to-repeat
             (my-repeat (dec how-many-times)
                        what-to-repeat))))

(defn my-range [up-to]
  (if (<= up-to 0) '()
      (cons (dec up-to)
            (my-range (dec up-to)))))

(defn tails [a-seq]
  (if (empty? a-seq)
    (cons a-seq nil)
    (cons a-seq
          (tails (rest a-seq)))))

(defn inits [a-seq]
  (reverse (map reverse
                (tails (reverse a-seq)))))

(defn rotations [a-seq]
  (if (empty? a-seq)
    (cons '() nil)
    (rest (my-map concat
                  (tails a-seq)
                  (inits a-seq)))))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
    freqs
    (let [key (first a-seq)
          val (or (get freqs key) 0)]
      (my-frequencies-helper (assoc freqs key (inc val))
                             (rest a-seq)))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies [a-map]
  (if (empty? a-map) '()
    (let [[value count] (first a-map)]
      (concat (my-repeat count value)
              (un-frequencies (rest a-map))))))

(defn my-take [n coll]
  (if (or (empty? coll)
          (<= n 0))
    '()
    (cons (first coll)
          (my-take (dec n) (rest coll)))))

(defn my-drop [n coll]
  (if (or (empty? coll)
          (<= n 0))
    coll
    (my-drop (dec n) (rest coll))))

(defn halve [a-seq]
  (let [half-length (if (odd? (count a-seq))
                      (/ (dec (count a-seq)) 2)
                      (/ (count a-seq) 2))]
    (vec (conj (cons (my-drop half-length a-seq)
                     nil)
               (my-take half-length a-seq)))))

(defn seq-merge [a-seq b-seq]
  (let [first-a (first a-seq)
        first-b (first b-seq)]
    (cond (empty? a-seq) b-seq
          (empty? b-seq) a-seq
          (<= first-a first-b) (cons first-a
                                     (seq-merge (rest a-seq) b-seq))
          :else (cons first-b
                      (seq-merge a-seq (rest b-seq))))))

(defn merge-sort [a-seq]
  (let [[first-half second-half] (halve a-seq)]
    (cond (empty? a-seq) a-seq
          (singleton? a-seq) a-seq
          :else (seq-merge (merge-sort first-half)
                           (merge-sort second-half)))))

(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

