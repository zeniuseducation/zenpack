(ns zenpack.test
  (:require [zenpack.core :refer :all]
            [compojure.core :refer :all]))

(defonce server (atom nil))

(defroutes my-site
  (GET "/" req
       (render "files/base.html"
               {})) 
  (error404 "Not found men"))


(defn run
  [port]
  (start-site server my-site port))

(defn stop
  []
  (stop-site server))
