(ns zenpack.core
  (:require [clojure.java.io :as io]
            [compojure.core :refer :all]
            [compojure.route :refer [not-found resources]]
            [org.httpkit.server :as web-server]
            [org.httpkit.client :as http]
            [clojure.string :as cs]
            [ring.middleware.defaults :refer :all]))

(def ^:private init-files
  [{:target "project.clj"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/files/project.edn"
    :description "Project skeleton"}
   {:target "resources/public/includes/foundation.css"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/files/foundation.css"
    :description "Foundation for site decoration"}
   {:target "resources/public/includes/normalize.css"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/files/normalize.css"
    :description "Site decoration standard"}
   {:target "resources/public/includes/react.js"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/files/react.js"
    :description "React-js for front-end web development"}
   {:target "resources/public/includes/canvas.js"
    :source "http://ocanvas.org/source/ocanvas-2.7.3.min.js"
    :description "OCanvas for animation and games development"}
   {:target "resources/templates/base.html"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/files/base.html"
    :description "Basic html template"}])

(defn boot-site
  [app-name]
  (doseq [{:keys [target source]} init-files]
    (do (make-parents target)
        (->> (-> (:body @(http/get source))
                 (cs/replace #"project-name" app-name))
             (spit target))
        (println "Installing " source))))

(def render selmer.parser/render-file)
(def static-files resources)
(def error-404 not-found)

(defn start
  [server app-routes port]
  (do (->> {:port port}
           (->> site-defaults
                (wrap-defaults app-routes)
                (web-server/run-server))
           (reset! server))
      (println "Web site runs from port " port)))

(defn stop
  [server]
  (do (@server :timeout 200)
      (reset! server nil)
      (println "Web site has been successfully shut-down")))










