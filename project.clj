(defproject zenedu/zenpack "0.1.11"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [lib-noir "0.9.5"]
                 [http-kit "2.1.19"]
                 [selmer "0.7.7"]
                 [expectations "2.0.13"]
                 [zenedu/testquest "0.1.0"]
                 [ring "1.3.2"]]
  :url "https://github.com/zeniuseducation/zenpack"
  :plugins [[codox "0.8.10"]
            [lein-autoexpect "1.4.2"]]
  :scm {:name "git"
        :url "https://github.com/zeniuseducation/zenpack"}
  :signing {:gpg-key "3F84379F"}
  :description "Starter pack for creating basic clojure web app"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :pom-addition [:developers [:developer
                              [:name "Zenius Education"]
                              [:url "http://github.com/zeniuseducation"]
                              [:timezone "+7"]]]
  :repositories [["releases" {:url "http://clojars.org/repo"
                              :creds :gpg}]])

