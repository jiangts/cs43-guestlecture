(ns demo.life
  (:require [reagent.core :as r]
            [cljs-bean.core :refer (bean ->clj ->js)]
            ["antd" :as antd]
            [clojure.core.async :refer (go-loop timeout)]
            [better-cond.core :as b]))


;; logic
;; compute neighbors
(defn neighbors [[x y]]
  (for [dx [-1 0 1] dy (if (zero? dx) [-1 1] [-1 0 1])]
    [(+ dx x) (+ dy y)]))

;; compute cells available in next generation
(defn step [cells]
  (set (for [[loc n] (frequencies (mapcat neighbors cells))
             :when (or (= n 3) (and (= n 2) (cells loc)))]
         loc)))



;; view
(defn grid [state]
  [:svg {:width 500
         :height 500
         :viewBox "-450 -450 700 700"}
   (for [[x y] @state]
     ^{:key (str x ":" y)}
     [:rect {:width 10
             :height 10
             :x (* x 10)
             :y (* y 10)}])])


(defonce cells (r/atom #{[-1 0] [0 0] [1 0]}))
(defonce play? (r/atom false))

(defn evolve []
  (swap! cells step))

(defn toggle-play []
  (swap! play? not))

(defn interesting-seed []
  (reset! cells #{[-1 0] [-1 1] [0 -1] [0 0] [1 0]}))

;; this code creates the page you see now. woooo magic.
(defn view []
  [:div
   [grid cells]
   [:br]
   [:> antd/Button {:on-click toggle-play} (if @play? "Stop" "Start") " Simulation"]
   [:> antd/Button {:on-click interesting-seed} "Interesting..."]
   [:hr]])

(defonce run
  (go-loop []
    (<! (timeout 40))
    (when @play? (evolve))
    (recur)))
