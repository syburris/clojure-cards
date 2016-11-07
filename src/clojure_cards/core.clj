(ns clojure-cards.core
  (:gen-class))


(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
        {:suit suit :rank rank})))

(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))

(def test-hand #{{:suit :diamonds :rank 1}
                 {:suit :hearts :rank 1}
                 {:suit :clubs :rank 3}
                 {:suit :spades :rank 3}})



(defn flush? [hand]
  (= 1 (count (set (map :suit hand)))))

(defn four-of-a-kind? [hand]
  (= 1 (count (set (map :rank hand)))))

(defn three-of-a-kind? [hand]
  (= 2 (count (set (map :rank hand)))))

(defn two-pair? [hand]
  (= '(2 2) (vals (frequencies (map :rank hand)))))
  
       

(defn -main [& args]
  (let [deck (create-deck)
        hands (create-hands deck)
        hands (filter two-pair? hands)]
    (println (count hands))))

