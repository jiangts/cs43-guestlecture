(ns demo.views
  (:require [reagent.core :as r]
            [cljs-bean.core :refer (bean ->clj ->js)]
            [better-cond.core :as b]))


(defn board-inner [props]
  (let [state (r/atom nil)
        id (str (gensym "board"))]

    (r/create-class
      {:reagent-render (fn [props]
                         [:div (merge props {:id id})])

       :component-did-mount (fn [c]
                              (let [board (js/Chessboard. id "start")]))
       :component-did-update (fn [c]
                               )
       :display-name "board-inner"})))


(defn app-root []
  [board-inner {:style {:width 400}}])

