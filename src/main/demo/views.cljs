(ns demo.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cljs-bean.core :refer (bean ->clj ->js)]
            [better-cond.core :as b]))

(defn app-root []
  [:h3 "hi world"])


;; var board = Chessboard('myBoard', 'start')

