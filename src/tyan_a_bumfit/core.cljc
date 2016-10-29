(ns tyan-a-bumfit.core
  (:require
    [clojure.string :as s]
    [tyan-a-bumfit.cardinals :refer [yan tyan tethera dick bumfit jigget]]))


; https://en.wikipedia.org/wiki/Yan_tan_tethera
(def yan-tan-tethera
  (concat
    '(Number 	Bowland 	Rathmell 	Nidderdale 	Swaledale 	Wharfedale 	Teesdale
      1 	    Yain 	    Aen 	    Yain 	      Yan 	Yan 	Yan
      2 	    Tain 	    Taen 	    Tain 	      Tan 	Tan 	Tean
      3 	    Eddera 	  Tethera 	Eddero 	    Tether 	Tether 	Tether
      4 	    Peddera 	Fethera 	Peddero 	  Mether 	nil	Mether
      5 	    Pit 	    Phubs 	  Pitts 	    Pip 	nil	Pip
      6 	    Tayter 	  Aayther 	Tayter 	    Azer 	nil	Lezar
      7 	    Layter 	  Layather 	Layter 	    Sezar 	nil	Azar
      8 	    Overa 	  Quoather 	Overo 	    Akker 	nil	Catrah
      9 	    Covera 	  Quaather 	Covero 	    Conter nil		Borna
      10 	    Dix 	    Dugs 	    Dix 	      Dick 	nil	Dick
      11 	    Yain-a-dix 	Aena-dugs 	Yaindix 	Yanadick 	nil	Yan-a-dick
      12 	    Tain-a-dix 	Taena-dugs 	Taindix 	Tanadick 	nil	Tean-a-dick
      13 	    Eddera-a-dix 	Tethera-dugs 	Edderodix 	Tetheradick 	nil	Tether-dick
      14 	    Peddera-a-dix 	Fethera-dugs 	Pedderodix 	Metheradick 	nil	Mether-dick
      15 	    Bumfit 	Buon 	Bumfit 	Bumfit 	nil	Bumfit
      16 	    Yain-a-bumfit 	Aena-buon 	Yain-o-Bumfit 	Yanabum 	nil	Yan-a-bum
      17 	    Tain-a-bumfit 	Taena-buon 	Tain-o-Bumfit 	Tanabum 	nil	Tean-a-bum
      18 	    Eddera-bumfit 	Tethera-buon 	Eddero-Bumfit 	Tetherabum 	nil	Tethera-bum
      19 	    Peddera-a-bumfit 	Fethera-buon 	Peddero-Bumfit 	Metherabum 	nil	Methera-bum
      20 	    Jiggit 	          Gun-a-gun 	Jiggit 	Jigget 	nil	Jiggit)
    '(Number 	Derbyshire 	Weardale 	Tong 	Kirkby-Lonsdale 	Wensleydale 	Derbyshire-Dales 	Lincolnshire
      1 	Yain 	Yan 	Yan 	Yaan 	Yain 	Yan 	Yan
      2 	Tain 	Teyan 	Tan 	Tyaan 	Tain 	Tan 	Tan
      3 	Eddero 	Tethera 	Tether 	Taed'ere 	Eddero 	Tethera 	Tethera
      4 	Pederro 	Methera 	Mether 	Mead'ere 	Peddero 	Methera 	Pethera
      5 	Pitts 	Tic 	Pick 	Mimp 	Pitts 	Pip 	Pimp
      6 	Tayter 	Yan-a-tic 	Sesan 	Haites 	Tayter 	Sethera 	Sethera
      7 	Later 	Teyan-a-tic 	Asel 	Saites 	Later 	Lethera 	Lethera
      8 	Overro 	Tethera-tic 	Catel 	Haoves 	Overro 	Hovera 	Hovera
      9 	Coverro 	Methera-tic 	Oiner 	Daoves 	Coverro 	Dovera 	Covera
      10 	Dix 	Bub 	Dick 	Dik 	Disc 	Dick 	Dik
      11 	Yain-dix 	Yan-a-bub 	Yanadick 	Yaan'edik 	Yain-disc nil		Yan-a-dik
      12 	Tain-dix 	Teyan-a-bub 	Tanadick 	Tyaan'edik 	Tain-disc 	nil	Tan-a-dik
      13 	Eddero-dix 	Tethera-bub 	Tetheradick 	Tead'eredik 	Ederro-disc 	nil	Tethera-dik
      14 	Peddero-dix 	Methera-bub 	Metheradick 	Mead'eredik 	Peddero-disc 	nil	Pethera-dik
      15 	Bumfitt 	Tic-a-bub 	Bumfit 	buum 	Bumfitt 	nil	Bumfit
      16 	Yain-o-bumfitt 	Yan-tic-a-bub 	Yanabum 	Yaan'eboon 	Bumfitt-yain 	nil	Yan-a-bumfit
      17 	Tain-o-bumfitt 	Teyan-tic-a-bub 	Tanabum 	Tyaan'eboon 	Bumfitt-tain 	nil	Tan-a-bumfit
      18 	Eddero-o-bumfitt 	Tethea-tic-a-bub 	Tetherabum 	Tead'ereboon 	Bumfitt-ederro 	nil	Tethera-bumfit
      19 	Peddero-o-bumfitt 	Methera-tic-a-bub 	Metherabum 	Mead'ereboon 	Bumfitt-peddero 	nil	Pethera-bumfit
      20 	Jiggit 	Gigget 	Jigget 	Buum'fit 	Jiggit 	nil	Figgot)
    '(Number 	Southwest-England 	West-Country-Dorset
      1 	Yahn 	Hant
      2 	Tayn 	Tant
      3 	Tether 	Tothery
      4 	Mether 	Forthery
      5 	Mumph 	Fant
      6 	Hither 	Sahny
      7 	Lither 	Dahny
      8 	Auver 	Downy
      9 	Dauver 	Dominy
      10 	Dic 	Dik
      11 	Yahndic 	Haindik
      12 	Tayndic 	Taindik
      13 	Tetherdic 	Totherydik
      14 	Metherdic 	Fotherydik
      15 	Mumphit 	Jiggen
      16 	Yahna-Mumphit 	Hain-Jiggen
      17 	Tayna-Mumphit 	Tain-Jiggen
      18 	Tethera-Mumphit 	Tother-Jiggen
      19 	Methera-Mumphit 	Fother-Jiggen
      20 	Jigif 	Full-Score)
    '(Number Coniston Borrowdale Eskdale Westmorland
      1 Yan Yan Yaena Yan
      2 Taen Tyan Taena Tahn
      3 Tedderte Tethera Teddera Teddera
      4 Medderte Methera Meddera Meddera
      5 Pimp Pimp Pimp Pimp
      6 Haata Sethera Seckera Settera
      7 Slaata Lethera Leckera Lettera
      8 Lowra Hovera Hofa Hovera
      9 Dowra Dovera Lofa Dovera
      10 Dick Dick Dec Dick
      11 Yan-a-Dick Yan-a-Dick nil Yan-Dick
      12 Taen-a-Dick Tyan-a-Dick nil Tahn-Dick
      13 Tedder-a-Dick Tethera-Dick nil Teddera-Dick
      14 Medder-a-Dick Methera-Dick nil Meddera-Dick
      15 Mimph Bumfit nil Bumfit
      16 Yan-a-Mimph Yan-a-bumfit nil Yan-a-Bumfit
      17 Taen-a-Mimph Tyan-a-bumfit nil Tahn-a-Bumfit
      18 Tedder-a-Mimph Tethera-Bumfit nil Teddera-Bumfit
      19 Medder-a-Mimph Methera-Bumfit nil Meddera-Bumfit
      20 Gigget Giggot nil Jiggot)
    '(Number 	Wilts 	Scots 	Lakes 	Dales 	Welsh
      1 	Ain 	Yan 	Auna 	Yain 	Un
      2 	Tain 	Tyan 	Peina 	Tain 	Dau
      3 	Tethera 	Tethera 	Para 	Edderoa 	Tri
      4 	Methera 	Methera 	Peddera 	Peddero 	Pedwar
      5 	Mimp 	Pimp 	Pimp 	Pitts 	Pump
      6 	Ayta 	Sethera 	Ithy 	Tayter 	Chwech
      7 	Slayta 	Lethera 	Mithy 	Leter 	Saith
      8 	Laura 	Hovera 	Owera 	Overro 	Wyth
      9 	Dora 	Dovera 	Lowera 	Coverro 	Naw
      10 	Dik 	Dik 	Dig 	Dix 	Deg
      11 	Ain-a-dik 	Yanadik 	Ain-a-dig 	Yain-dix 	Un-ar-ddeg
      12 	Tain-a-dik 	Tyanadik 	Pein-a-dig 	Tain-dix 	Deuddeg
      13 	Tethera-a-dik 	Tetheradik 	Para-a-dig 	Eddero-dix 	Tri-ar-ddeg
      14 	Methera-a-dik 	Metheradik 	Peddaer-a-dig 	Pedderp-dix 	Pedwar-ar-ddeg
      15 	Mit 	Bumfitt 	Bunfit 	Bumfitt 	Pymtheg
      16 	Ain-a-mit 	Yanabumfit 	Aina-a-bumfit 	Yain-o-bumfitt 	Un-ar-bymtheg
      17 	Tain-a-mit 	Tyanabumfitt 	Pein-a-bumfit 	Tain-o-bumfitt 	Dau-ar-bymtheg
      18 	Tethera-mit 	Tetherabumfitt 	Par-a-bunfit 	Eddero-bumfitt 	Deunaw
      19 	Gethera-mit 	Metherabumfitt 	Pedder-a-bumfit 	Peddero-bumfitt 	Pedwar-ar-bymtheg
      20 	Ghet 	Giggot 	Giggy 	Jiggit 	Ugain)))

(def by-region
  (into {}
    (map (juxt first (comp vec (partial cons 'nil) rest))
      (mapcat
       (fn [[h & n]]
         (apply map vector (cons h (take-nth 2 (rest n)))))
       (remove
         (partial = '((Number)))
         (partition-by
           (partial = '(Number))
           (partition-by
             (fn [x] (or (number? x) (= x 'Number))) yan-tan-tethera)))))))

; https://en.wikipedia.org/wiki/Cardinal_number_(linguistics)
(def cardinal-by-region
  (into {}
    (map vec
      (partition tyan
       '(
          Borrowdale           [nil yan tyan tethera methera pimp sethera lethera hovera dovera dick yan-a-dick tyan-a-dick tethera-dick methera-dick bumfit yan-a-bumfit tyan-a-bumfit tethera-bumfit methera-bumfit giggot]
          Bowland              [nil yain tain eddera peddera pit tayter layter overa covera dix yain-a-dix tain-a-dix eddera-a-dix peddera-a-dix bumfit yain-a-bumfit tain-a-bumfit eddera-bumfit peddera-a-bumfit jiggit]
          Coniston             [nil yan taen tedderte medderte pimp haata slaata lowra dowra dick yan-a-dick taen-a-dick tedder-a-dick medder-a-dick mimph yan-a-mimph taen-a-mimph tedder-a-mimph medder-a-mimph gigget]
          Dales                [nil yain tain edderoa peddero pitts tayter leter overro coverro dix yain-dix tain-dix eddero-dix pedderp-dix bumfitt yain-o-bumfitt tain-o-bumfitt eddero-bumfitt peddero-bumfitt jiggit]
          Derbyshire           [nil yain tain eddero pederro pitts tayter later overro coverro dix yain-dix tain-dix eddero-dix peddero-dix bumfitt yain-o-bumfitt tain-o-bumfitt eddero-o-bumfitt peddero-o-bumfitt jiggit]
          Derbyshire-Dales     [nil yan tan tethera methera pip sethera lethera hovera dovera dick nil nil nil nil nil nil nil nil nil nil]
          Eskdale              [nil yaena taena teddera meddera pimp seckera leckera hofa lofa dec nil nil nil nil nil nil nil nil nil nil]
          Kirkby-Lonsdale      [nil yaan tyaan taed'ere mead'ere mimp haites saites haoves daoves dik yaan'edik tyaan'edik tead'eredik mead'eredik buum yaan'eboon tyaan'eboon tead'ereboon mead'ereboon buum'fit]
          Lakes                [nil auna peina para peddera pimp ithy mithy owera lowera dig ain-a-dig pein-a-dig para-a-dig peddaer-a-dig bunfit aina-a-bumfit pein-a-bumfit par-a-bunfit pedder-a-bumfit giggy]
          Lincolnshire         [nil yan tan tethera pethera pimp sethera lethera hovera covera dik yan-a-dik tan-a-dik tethera-dik pethera-dik bumfit yan-a-bumfit tan-a-bumfit tethera-bumfit pethera-bumfit figgot]
          Nidderdale           [nil yain tain eddero peddero pitts tayter layter overo covero dix yaindix taindix edderodix pedderodix bumfit yain-o-bumfit tain-o-bumfit eddero-bumfit peddero-bumfit jiggit]
          Rathmell             [nil aen taen tethera fethera phubs aayther layather quoather quaather dugs aena-dugs taena-dugs tethera-dugs fethera-dugs buon aena-buon taena-buon tethera-buon fethera-buon gun-a-gun]
          Scots                [nil yan tyan tethera methera pimp sethera lethera hovera dovera dik yanadik tyanadik tetheradik metheradik bumfitt yanabumfit tyanabumfitt tetherabumfitt metherabumfitt giggot]
          Southwest-England    [nil yahn tayn tether mether mumph hither lither auver dauver dic yahndic tayndic tetherdic metherdic mumphit yahna-mumphit tayna-mumphit tethera-mumphit methera-mumphit jigif]
          Swaledale            [nil yan tan tether mether pip azer sezar akker conter dick yanadick tanadick tetheradick metheradick bumfit yanabum tanabum tetherabum metherabum jigget]
          Teesdale             [nil yan tean tether mether pip lezar azar catrah borna dick yan-a-dick tean-a-dick tether-dick mether-dick bumfit yan-a-bum tean-a-bum tethera-bum methera-bum jiggit]
          Tong                 [nil yan tan tether mether pick sesan asel catel oiner dick yanadick tanadick tetheradick metheradick bumfit yanabum tanabum tetherabum metherabum jigget]
          Weardale             [nil yan teyan tethera methera tic yan-a-tic teyan-a-tic tethera-tic methera-tic bub yan-a-bub teyan-a-bub tethera-bub methera-bub tic-a-bub yan-tic-a-bub teyan-tic-a-bub tethea-tic-a-bub methera-tic-a-bub gigget]
          Welsh                [nil un dau tri pedwar pump chwech saith wyth naw deg un-ar-ddeg deuddeg tri-ar-ddeg pedwar-ar-ddeg pymtheg un-ar-bymtheg dau-ar-bymtheg deunaw pedwar-ar-bymtheg ugain]
          Wensleydale          [nil yain tain eddero peddero pitts tayter later overro coverro disc yain-disc tain-disc ederro-disc peddero-disc bumfitt bumfitt-yain bumfitt-tain bumfitt-ederro bumfitt-peddero jiggit]
          West-Country-Dorset  [nil hant tant tothery forthery fant sahny dahny downy dominy dik haindik taindik totherydik fotherydik jiggen hain-jiggen tain-jiggen tother-jiggen fother-jiggen full-score]
          Westmorland          [nil yan tahn teddera meddera pimp settera lettera hovera dovera dick yan-dick tahn-dick teddera-dick meddera-dick bumfit yan-a-bumfit tahn-a-bumfit teddera-bumfit meddera-bumfit jiggot]
          Wharfedale           [nil yan tan tether nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil nil]
          Wilts                [nil ain tain tethera methera mimp ayta slayta laura dora dik ain-a-dik tain-a-dik tethera-a-dik methera-a-dik mit ain-a-mit tain-a-mit tethera-mit gethera-mit ghet]
          )))))

(def number-of
  (reduce
    (fn [r n]
      (into r (map vector n (range))))
    {}
    (vals cardinal-by-region)))

(defn convert [cardinal region]
  ((cardinal-by-region region) (number-of cardinal)))

(def cardinals
  (reduce
    (fn [r [region numbers]]
      (reduce
        (fn [r [i naym]]
          (assoc-in r [i region] naym))
         r (map vector (range) numbers)))
  {} cardinal-by-region))

(defn floor [x]
  #?(:cljs (.floor js/Math (double x)) :clj (Math/floor (double x))))

(defn pad [l] (apply max (map (comp count name) l)))

(defn padding [p x] (apply str (repeat (- p (count (name x))) " ")))

; (spit "./src/tyan_a_bumfit/cardinals.cljc" (apply str "(ns tyan-a-bumfit.cardinals)\n\n" (mapcat (fn [i] (map (fn [v] (str "(def " v " " i ")\n")) (remove nil? (vals (cardinals i))))) (range 1 21))))
; (let [p (pad (keys by-region))] (doall (map (fn [[k v]] (println k (padding p k) (map (fn [t] (if t (s/lower-case (name t)))) v))) (sort-by key by-region))) 'bumfit)