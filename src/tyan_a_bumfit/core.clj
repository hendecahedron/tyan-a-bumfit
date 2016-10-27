(ns tyan-a-bumfit.core
  (:require [clojure.string :as s]))

; https://en.wikipedia.org/wiki/Yan_tan_tethera
(def mappings
  (concat
    '(Number 	Bowland 	Rathmell 	Nidderdale 	Swaledale 	Wharfedale 	Teesdale
      1 	Yain 	Aen 	Yain 	Yan 	Yan 	Yan
      2 	Tain 	Taen 	Tain 	Tan 	Tan 	Tean
      3 	Eddera 	Tethera 	Eddero 	Tether 	Tether 	Tether
      4 	Peddera 	Fethera 	Peddero 	Mether 	nil	Mether
      5 	Pit 	Phubs 	Pitts 	Pip 	nil	Pip
      6 	Tayter 	Aayther 	Tayter 	Azer 	nil	Lezar
      7 	Layter 	Layather 	Layter 	Sezar 	nil	Azar
      8 	Overa 	Quoather 	Overo 	Akker 	nil	Catrah
      9 	Covera 	Quaather 	Covero 	Conter nil		Borna
      10 	Dix 	Dugs 	Dix 	Dick 	nil	Dick
      11 	Yain-a-dix 	Aena dugs 	Yaindix 	Yanadick 	nil	Yan-a-dick
      12 	Tain-a-dix 	Taena dugs 	Taindix 	Tanadick 	nil	Tean-a-dick
      13 	Eddera-a-dix 	Tethera dugs 	Edderodix 	Tetheradick 	nil	Tether-dick
      14 	Peddera-a-dix 	Fethera dugs 	Pedderodix 	Metheradick 	nil	Mether-dick
      15 	Bumfit 	Buon 	Bumfit 	Bumfit 	nil	Bumfit
      16 	Yain-a-bumfit 	Aena buon 	Yain-o-Bumfit 	Yanabum 	nil	Yan-a-bum
      17 	Tain-a-bumfit 	Taena buon 	Tain-o-Bumfit 	Tanabum 	nil	Tean-a-bum
      18 	Eddera-bumfit 	Tethera buon 	Eddero-Bumfit 	Tetherabum 	nil	Tethera-bum
      19 	Peddera-a-bumfit 	Fethera buon 	Peddero-Bumfit 	Metherabum 	nil	Methera-bum
      20 	Jiggit 	Gun-a-gun 	Jiggit 	Jigget 	nil	Jiggit)
    '(Number 	Derbyshire 	Weardale 	Tong 	Kirkby Lonsdale 	Wensleydale 	Derbyshire-Dales 	Lincolnshire
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
      15 	Bumfitt 	Tic-a-bub 	Bumfit 	Boon.buom.buum 	Bumfitt 	nil	Bumfit
      16 	Yain-o-bumfitt 	Yan-tic-a-bub 	Yanabum 	Yaan'eboon 	Bumfitt-yain 	nil	Yan-a-bumfit
      17 	Tain-o-bumfitt 	Teyan-tic-a-bub 	Tanabum 	Tyaan'eboon 	Bumfitt-tain 	nil	Tan-a-bumfit
      18 	Eddero-o-bumfitt 	Tethea-tic-a-bub 	Tetherabum 	Tead'ereboon 	Bumfitt-ederro 	nil	Tethera-bumfit
      19 	Peddero-o-bumfitt 	Methera-tic-a-bub 	Metherabum 	Mead'ereboon 	Bumfitt-peddero 	nil	Pethera-bumfit
      20 	Jiggit 	Gigget 	Jigget 	Buom'fit, buum'fit 	Jiggit 	nil	Figgot)
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
      16 	Yahna Mumphit 	Hain Jiggen
      17 	Tayna Mumphit 	Tain Jiggen
      18 	Tethera Mumphit 	Tother Jiggen
      19 	Methera Mumphit 	Fother Jiggen
      20 	Jigif 	Full Score)
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

(def numbers-by-region
  (into {}
    (map (juxt first (comp vec rest))
      (mapcat
       (fn [[h & n]]
         (apply map vector (cons h (take-nth 2 (rest n)))))
       (remove
         (partial = '((Number)))
         (partition-by
           (partial = '(Number))
           (partition-by
             (fn [x] (or (number? x) (= x 'Number))) mappings)))))))

(def name-to-number
  (reduce
    (fn [r n]
      (into r (map vector n (map inc (range)))))
    {}
    (vals numbers-by-region)))

(defn convert [n region]
  ((numbers-by-region region) (dec (name-to-number n))))

