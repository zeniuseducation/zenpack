(ns zenpack.core
  (:require [clojure.java.io :as io]
            [compojure.core :refer :all]
            [compojure.route :refer [not-found resources]]
            [org.httpkit.server :as web-server]
            [org.httpkit.client :as http]
            [clojure.string :as cs]
            [selmer.parser :as selmer]
            [ring.middleware.defaults :refer :all]))

(def ^:private init-files
  [{:target "project.clj"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/project.edn"
    :description "Project skeleton"}
   {:target "resources/public/includes/foundation.css"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/foundation.css"
    :description "Foundation for site decoration"}
   {:target "resources/public/includes/normalize.css"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/normalize.css"
    :description "Site decoration standard"}
   {:target "resources/public/includes/react.js"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/react.js"
    :description "React-js for front-end web development"}
   {:target "resources/public/includes/canvas.js"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/canvas.js"
    :description "OCanvas for animation and games development"}
   {:target "resources/public/includes/main-style.css"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/main-style.css"
    :description "Basic zenius decoration colors"}
   {:target "resources/templates/base.html"
    :source "https://raw.githubusercontent.com/zeniuseducation/zenpack/master/resources/files/base.html"
    :description "Basic html template"}])

(defn init-site
  [app-name]
  (do (println "Initialising web site, this could take a few minutes")
      (doseq [{:keys [target source description]} init-files]
        (do (io/make-parents target)
            (->> (-> (str (:body @(http/get source)))
                     (cs/replace #"project-template" app-name))
                 (spit target))
            (println "Installing " description)))
      "Your website is ready for development"))

(def render selmer/render-file)
(def static-files resources)
(def error-404 not-found)

(defn start
  [server app-routes port]
  (do (->> {:port port}
           (->> site-defaults
                (wrap-defaults app-routes)
                (web-server/run-server))
           (reset! server))
      (println "Web site runs on port " port)))

(defn stop
  [server]
  (do (@server :timeout 200)
      (reset! server nil)
      (println "Web site has been successfully shut-down")))










