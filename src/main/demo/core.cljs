(ns demo.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cljs-bean.core :refer (bean ->clj ->js)]
            [better-cond.core :as b]
            [demo.views :as views]))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/app-root]
                  (.getElementById js/document "app")))

(defn ^:export init []
  ;; (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
