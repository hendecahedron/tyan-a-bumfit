(ns tyan-a-bumfit.core)

(def yan     1)
(def tyan    2)
(def tethera 3)

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

(def regions
  (into #{} (keys cardinal-by-region)))

(def ranges
  (into {}
    (map
      (juxt key (comp count (partial take-while identity) rest val))
      cardinal-by-region)))

(def number-of
  (reduce
    (fn [r n]
      (into r (map vector n (range))))
    {}
    (vals cardinal-by-region)))

(defn cardinal-of
  [region n]
    ((cardinal-by-region region) n))

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
  #?(:cljs (long (.floor js/Math (double x)))
     :clj  (long (Math/floor (double x)))))

(defn xp
  ([n i]
   (if (== n 1)
      (cons '+ (repeat i 1))
    (if (< i n)
      i
      (xp n i (floor (/ i n))))))
  ([n i m]
    (xp n i m (- i (* n m))
      (if (== 1 m) n (list '* n (xp n m)))))
  ([n i m r y]
   (if (== 0 r)
    y
    (list '+ r y))))

(defn description
  ([region i]
   (if (<= i (ranges region))
     ((cardinal-by-region region) i)
     (description (cardinal-by-region region) (ranges region) (xp (ranges region) i))))
  ([cardinal n [op r m]]
    (if (list? m)
      (if (= op '+)
        (str (cardinal r) " and " (description cardinal n m))
        (str (description cardinal n m) " " (cardinal n)))
      (if (= '+ op)
        (str (cardinal r) " and " (cardinal m))
        (str (cardinal m) " " (cardinal r))))))