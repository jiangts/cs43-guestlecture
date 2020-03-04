(ns demo.views
  (:require [reagent.core :as r]
            [cljs-bean.core :refer (bean ->clj ->js)]
            ["antd" :as antd]
            ["showdown" :as showdown]
            [demo.life :as life]
            [better-cond.core :as b]))

;; chess

(defn char-range [start end]
  (map js/String.fromCharCode (range (.charCodeAt start) (inc (.charCodeAt end)))))

(def squares
  (for [i (char-range "a" "h")
        j (range 1 9)]
    (keyword (str i j))))


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



;; slides

(defn markdown [text]
  (let [converter (showdown/Converter.)]
    [:span {"dangerouslySetInnerHTML"
            #js {:__html (.makeHtml converter text)}}]))

(defn slide [{:keys [title md]} & children]
  (into
    [:> antd/Card {:title title}
     (when md [markdown md])]
    children))


(defn app-root []
  #_[board-inner {:style {:width 400}}]
  [:div {:style {:padding "1em"}}
   [slide {:title "Hello World"
           :md "- this is some markdown text"}]
   [slide {:title "game of life"}
    [life/view]]])

