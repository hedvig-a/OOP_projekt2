tööjuhend, enne esitamist eemaldada:
--------------------------------
projekti põhjalik kirjeldus, kus on kirjas programmi eesmärk ja selgitus programmi üldisest tööst, vajadusel lühike kasutusjuhis;
iga klassi kohta eraldi selle eesmärk ja olulisemad meetodid;
projekti tegemise protsessi kirjeldus (erinevad etapid ja rühmaliikmete osalemine neis);
iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt);
tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);
hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist);
selgitus ja/või näited, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt.
--------------------------------

AUTORID: Angelika Glaser ja Hedvig Annast

Programmi kirjeldus:
Programm kujutab endast suhtepäevikut. See võimaldab salvestada suhtes olulisi sündmusi ja salvestada ning genereerida
ideid edasisteks koosviibimisteks. Programmi kasutamiseks tuleb kompileerida ja käivitada MainFX.java fail.

Klassid:
MainFX - JavaFX fail, mis käivitab programmi ning kuvab peamenüü ning sealt saab nuppudega liikuda edasi sissekannete või ideede juurde.
ManageIdeasUI - järgmine ekraan pärast pealehel Ideede nupule vajutamist. Seal on näha kõik tegevused, mida saab ideedega teha ning ta kutsub välja iga tegevusele vastava dialoogi faili.
Entry - abstraktne klass, kus on abstraktne meetod faili kirjutamiseks, meetodid failist lugemiseks, kindlate sissekannete leidmiseks ja getterid-setterid
ChangeEntry - Entry alamklass, mõeldud sissekannete muutmiseks
NewEntry - Entry alamklass, mõeldud uute sissekannete loomiseks
Model pakis on kõik seonduv ideede objektidega.
model.Idea - model.Idea klass on selleks, et luua idee objekt ning seal hoian ka kõik getterid ja setterid, mis on ideega seotud.
model.DateIdeaGenerator - See klass laiendab model.Idea klassi ning on eraldi loodud date/kohtingu tüüpi ideede jaosk.
model.HangoutIdeaGenerator - See klass laiendab model.Idea klassi ning on eraldi loodud hangout/kokkusaamise tüüpi ideede jaosk.
model.IdeaGenerator - see klass vastutab kõikide ideede eest, seal asuvad kõik funktsionaalsused, mis on eri ideede tegevustega seotud (lisamine, eemaldamine, muutmine jne).
Sinna on kogutud ka osad funktsionaalsused, mis tegelevad kasutajaga suhtlemisega, et hoida main fail puhtamana.
Tulevikus tahan seda natuke paremini jaotada.
Dialogs pakis on kõik dialoogi aknad, eri tegevused, mida sa saad ideedega teha, need tulevad nähtavale, kui vajutad ManageIdeasUI faili lehel ühte nuppudest.
DropDownDialogBox on kõige uuem, kuna algselt oli selle asemel tavaline tekstiväljana sisestuse ekraan, mis tegi olukorra keeruliseks.
Need on kõik suhteliselt sarnase põhimõttega, aga iga nupu jaoks vajalikud.
Utils paki all on DialogUtils, mis tagab, et dialoogi ekraanid oleksid valdavalt sarnased.
Lisaks on veel resources paki all data kaustas kõik tekstifailid, mida projekt vajab.
Data kausta kõrval on style.css file, mis hõlmab enda all enamust korduvat disaini projektis.

Rühmaliikmete panus:
Hedvig - lõi klassid Entry, ChangeEntry, NewEntry ja lõi Maini põhja, ligikaudu 8 tundi tööd. Tegi projekti ringi javaFX projektiks.
Angelika - pakkus projekti idee, lõi klassid model.Idea, model.IdeaGenerator, model.DateIdeaGenerator, model.HangoutIdeaGenerator, täiendas vana.Main klassi.
Angelika tegi kogu JavaFX osa, jaotas projekti kaustadesse. tegi disaini ning muutis kõiki funktsionaalsuseid, et kõik töötaks. Aega kulus 10 tundi esimese projekti osa peale ja
väga palju rohkem teise osa peale, aga seda üle mitme päeva ja isegi nädala seega täpset aega raske öelda. Algsed muudatused ja muutmised enam-vähem 10h, aga pärast seda oli palju muutmisi ja ümber tegemisi.

Murekohad:
Hedvig - Ei tundnud väga, et oleks teadmistest puudu jäänud - olen kursuselt piisavalt õppinud, et sain programmis
enamike meetodite loomisega oma teadmiste peal hakkama.
Angelika - Sain ka oma osaga päris hästi hakkama, ainuke murekoht oli, idee osas, tagasimineku lisamisega, kuna eri valikute variandid sain kasutaja vastuseid erinevalt,
 edaspidi tahaks muuta nii, et oleks võimalik ühtlustada tagasimineku funktsionaalsust. Aega kulus ca 10h.
JavaFX-iga oli keeruline algse projekti muutmine, nii, et see sobitus javaFX-iga kokku. Nuppude ja pealkirjade enda osa ei olnud keeruline, siiski nende disain ja paika saamine oli üpriski ajakulukas.
Algselt sai kõik kirjutatud mainFX ja ManageIdeasUI failide vahelel, aga mida rohkem projekt edasi liikus, seda raskem oli kõike hallata ja leida.
Huvitaval kombel oli ka stiili faili leidmine ülejäänud projektifailide poolt omaette katsumus.
Ekraanide sobivumus omavahel oli raske, kuna ideede ekraanilt peamenüüsse liikumisel ei säilinud ükski eelnevalt seotud kujutus ning seda toimima saada võttis kuskil tund, kuna tolleks hetkeks oli projekt piisavalt sisukas, et mida muuta ja
kontrollida oli palju.

Hinnang oma töö tulemusele:
Hedvig - Mina olen programmiga väga rahul. Kõik ideed, mis alguses kokku leppisime, said projektile lisatud.
Angelika - Olen rahul, kõik toimib nii nagu soovisime ning sain oma osa täpselt nii detailseks kui plaanisin.
Olen ka JavaFX osaga rahul, tuli päris äge, ning lõpuks töötab ilusti, päris omamoodi huvitav ja keeruline projekt tuli.

Programmi testimine:
Hedvig - testisin enda klasse Mainis siis, kui põhiprogramm polnud veel kirjutatud. Kui olin veendunud, et funktsioonid
töötavad nii, kuidas pidid, kirjutasin algse põhiprogrammi.
Angelika - Kuna ma plaanisin välja kõik põhifunktsionaalsused, siis tegin algselt üle-üldsise main faili osa ideedele, ning iga funktsionaalsuse valmimise järgselt täiendasin seda ja testisin,
et kõik toimiks nii nagu peab. Algselt ei olnud tagasiminekut plaanis, kui nägin töö valmimisel, et seda on vaja ning lisasin selle juurde, kui see osutus küllaltki keeruliseks, kuna üritasin koodi hoida puhtana.
Kui kõik funktsionaalsused olid valmis, siis testisin käsitsi kõiki variante ja tegin muudatuse vastavalt vajadusele.
Testisin käsitsi kõiki osasid enne estitamist, aga kindlasti ei proovinud, kuidas katki teha, kuna projekt on juba piisavalt suur siis ei taha rohkem funktsionaalsuseid hetkel lisada.