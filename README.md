zenpack
=======

A package of basic clojure libraries for developing basic clojure web app, this is a workaround when
using <a href="http://Nightcode.info" target="_blank">Nightcode</a> when leiningen is unavailable.  
If leiningen is available, you might want to use <a href="https://github.com/zeniuseducation/zenweb-template">Zenweb</a> instead.  

### Usage :

1. Open nightcode, and create a new project (you decide the name)  
2. Choose command line app template and choose clojure as the language  
3. Add this to your :dependencies vector in project.clj (under the [org.clojure/clojure "1.6.0"])  

[![Clojars Project](http://clojars.org/zenedu/zenpack/latest-version.svg)](http://clojars.org/zenedu/zenpack)

4. Save your project.clj  
5. Open src/[project-name]/core.clj file in your project in nightcode  
6. Make sure your core.clj file looks like this  

```clojure 
(ns [your-project-name].core
  (:require [zenpack.core :refer :all]))
```

7. Save your core.clj  
8. Choose Run-in-repl in the REPL below the 'codes' part  
9. Reload to make sure the file is loaded  
10. run (init-site [your-project-name]) in the repl  
11. Stop the repl  
12. Change your core.clj to   

```clojure
(ns [your-project-name].core
  (:require [zenpack.core :refer :all))
  
(defonce server (atom nil))

(defroutes my-site ;;you can choose any name actually
  (GET "/" [req] "Hellow world!")
  (error404 "No find nothing here!"))
  
(defn run
  [port]
  (start server my-site port))
  
(defn stop
  []
  (stop server))

```

13. Save the file  
14. Run-in REPL again  
15. Reload  
16. in REPL enter (run 3000)  
17. Now you can navigate in your browser to localhost:3000 and your site is up and running  
18. To stop the site simply enter (stop) in the REPL

For further web development tutorial, there will be one we assure you...

### License

EPL 1 or higher the same as clojure

### Copyright

2014 PT Zenius Education
