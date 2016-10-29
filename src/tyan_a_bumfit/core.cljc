(ns tyan-a-bumfit.core
  (:require [clojure.string :as s]))

; https://en.wikipedia.org/wiki/Yan_tan_tethera
; https://en.wikipedia.org/wiki/Cardinal_number_(linguistics)
(def cardinal-by-region
  (into {}
    (map vec
      (partition 2
       '(
          Southwest-England   [nil yahn tayn tether mether mumph hither lither auver dauver dic yahndic tayndic tetherdic metherdic mumphit yahna tayna tethera methera jigif]
          Bowland             [nil yain tain eddera peddera pit tayter layter overa covera dix yain-a-dix tain-a-dix eddera-a-dix peddera-a-dix bumfit yain-a-bumfit tain-a-bumfit eddera-bumfit peddera-a-bumfit jiggit]
          Rathmell            [nil aen taen tethera fethera phubs aayther layather quoather quaather dugs aena taena tethera fethera buon aena taena tethera fethera gun-a-gun]
          West-Country-Dorset [nil hant tant tothery forthery fant sahny dahny downy dominy dik haindik taindik totherydik fotherydik jiggen mumphit mumphit mumphit mumphit full]
          Tong                [nil yan tan tether mether pick sesan asel catel oiner dick yanadick tanadick tetheradick metheradick bumfit yanabum tanabum tetherabum metherabum jigget]
          Wilts               [nil ain tain tethera methera mimp ayta slayta laura dora dik ain-a-dik tain-a-dik tethera-a-dik methera-a-dik mit ain-a-mit tain-a-mit tethera-mit gethera-mit ghet]
          Dales               [nil yain tain edderoa peddero pitts tayter leter overro coverro dix yain-dix tain-dix eddero-dix pedderp-dix bumfitt yain-o-bumfitt tain-o-bumfitt eddero-bumfitt peddero-bumfitt jiggit]
          Derbyshire-Dales    [nil yan tan tethera pethera pimp sethera lethera hovera covera dik yan-a-dik tan-a-dik tethera-dik pethera-dik bumfit yan-a-bumfit tan-a-bumfit tethera-bumfit pethera-bumfit nil]
          Lakes               [nil auna peina para peddera pimp ithy mithy owera lowera dig ain-a-dig pein-a-dig para-a-dig peddaer-a-dig bunfit aina-a-bumfit pein-a-bumfit par-a-bunfit pedder-a-bumfit giggy]
          Kirkby              [nil yaan tyaan taed'ere mead'ere mimp haites saites haoves daoves dik yaan'edik tyaan'edik tead'eredik mead'eredik boon.buom.buum yaan'eboon tyaan'eboon tead'ereboon mead'ereboon buom'fit]
          Lonsdale            [nil yain tain eddero peddero pitts tayter later overro coverro disc yain-disc tain-disc ederro-disc peddero-disc bumfitt bumfitt-yain bumfitt-tain bumfitt-ederro bumfitt-peddero buum'fit]
          Westmorland         [nil yan tahn teddera meddera pimp settera lettera hovera dovera dick yan-dick tahn-dick teddera-dick meddera-dick bumfit yan-a-bumfit tahn-a-bumfit teddera-bumfit meddera-bumfit jiggot]
          Teesdale            [nil yan tean tether mether pip lezar azar catrah borna dick nil nil nil nil bumfit nil nil nil nil jiggit]
          Derbyshire          [nil yain tain eddero pederro pitts tayter later overro coverro dix yain-dix tain-dix eddero-dix peddero-dix bumfitt yain-o-bumfitt tain-o-bumfitt eddero-o-bumfitt peddero-o-bumfitt jiggit]
          Borrowdale          [nil yan tyan tethera methera pimp sethera lethera hovera dovera dick yan-a-dick tyan-a-dick tethera-dick methera-dick bumfit yan-a-bumfit tyan-a-bumfit tethera-bumfit methera-bumfit giggot]
          Eskdale             [nil yaena taena teddera meddera pimp seckera leckera hofa lofa dec nil nil nil nil nil nil nil nil nil nil]
          Scots               [nil yan tyan tethera methera pimp sethera lethera hovera dovera dik yanadik tyanadik tetheradik metheradik bumfitt yanabumfit tyanabumfitt tetherabumfitt metherabumfitt giggot]
          Welsh               [nil un dau tri pedwar pump chwech saith wyth naw deg un-ar-ddeg deuddeg tri-ar-ddeg pedwar-ar-ddeg pymtheg un-ar-bymtheg dau-ar-bymtheg deunaw pedwar-ar-bymtheg ugain]
          Wensleydale         [nil yan tan tethera methera pip sethera lethera hovera dovera dick nil nil nil nil nil nil nil nil nil jiggit]
          Weardale            [nil yan teyan tethera methera tic yan-a-tic teyan-a-tic tethera-tic methera-tic bub yan-a-bub teyan-a-bub tethera-bub methera-bub tic-a-bub yan-tic-a-bub teyan-tic-a-bub tethea-tic-a-bub methera-tic-a-bub gigget]
          Wharfedale          [nil yan tan tether nil nil nil nil nil nil nil yanadick tanadick tetheradick metheradick nil yanabum tanabum tetherabum metherabum nil]
          Coniston            [nil yan taen tedderte medderte pimp haata slaata lowra dowra dick yan-a-dick taen-a-dick tedder-a-dick medder-a-dick mimph yan-a-mimph taen-a-mimph tedder-a-mimph medder-a-mimph gigget]
          Swaledale           [nil yan tan tether mether pip azer sezar akker conter dick yaindix taindix edderodix pedderodix bumfit yain-o-bumfit tain-o-bumfit eddero-bumfit peddero-bumfit jigget]
          Nidderdale          [nil yain tain eddero peddero pitts tayter layter overo covero dix dugs dugs dugs dugs bumfit buon buon buon buon jiggit]
          )))))

(def number
  (reduce
    (fn [r n]
      (into r (map vector n (range))))
    {}
    (vals cardinal-by-region)))

(defn convert [cardinal region]
  ((cardinal-by-region region) (number cardinal)))

(def cardinals
  (reduce
    (fn [r [region numbers]]
      (reduce
        (fn [r [i naym]]
          (assoc-in r [i region] naym))
         r (map vector (range) numbers)))
  {} cardinal-by-region))