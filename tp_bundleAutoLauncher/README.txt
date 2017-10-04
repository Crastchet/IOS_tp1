A program that reads in a specific folder. When a new bundle .jar file is added or removed, it deploys or undeploys it automatically. It acts as a plugin system.
-----------------------------------------------------------------------------------------------------------------------


Dossier à chercher que j'ai ciblé :
UBUNTU : /home/thibault/School/IOS/auto-deploy/my_bundles/
WINDOWS : E:\\Code\\Java\\Workspace_IOS\\auto-deploy\\my_bundles
La variable est à modifier dans la classe me.crastchet.Activator, elle se nomme "pathDirectory".

Bundle exemple que j'ai utilisé :
helloworld-bundle-1.jar
Il se contente d'afficher un petit message de bonjour au lancement, et un petit message d'aurevoir à l'arrêt.



J'ai implémenté le design pattern Observer, le fonctionnement est le suivant : 

Une classe observable "MyObservable" :
C'est la classe principale du projet. Elle s'occupe de gérer l'observation dans un certain dossier, elle garde en mémoire la liste des Bundles, des noms des fichiers, des dates d'ajout de derniers.
Ici j'y ai réglé un Timer qui récupère la liste des fichiers dans le dossier toutes les secondes. Et chaque seconde des classes observer sont notifiées et effectuent chacune certaines tâches que je précise plus bas dans ce document.

Une classe filter "BundleFilter" :
Elle permet en fait de récupérer uniquement les fichiers .JAR présents dans le dossier. En revanche si le fichier est un fichier .JAR quelconque et ne contient pas de Bundle, elle n'est pas capable de le savoir.

Une classe observer "AddBundleObserver" :
Cette classe installe et lance les nouveaux Bundles (fichiers) ajoutées dans le dossier. Cela passe donc par vérifier l'ajout de nouveaux fichiers .JAR par rapport à ceux qui étaient présents auparavant (à savoir 1 seconde avant).

Une classe observer "RemoveBundleObserver" :
Cette classe stope et désinstalle les Bundles (fichiers) supprimées du dossier. Cela passse donc par vérifier la suppression de fichiers .JAR qui existaient auparavant dans le dossier (à savoir 1 seconde avant).

Une classe observer "UpdateBundleObserver" :
Cette classe update un Bundle dont le fichier source aurait été modifié. Etant donné que chaque Bundle est associé à un nom de fichier .JAR unique, et que la date de dernière modification du fichier est sauvegardé pour chaque nom de fichier dans une HashMap, il est possible de savoir si nous utilisons la dernière version du fichier ou pas. Si ce n'est pas le cas le Bundle est mis à jour.



Une chose que je tiens à préciser : 2 Bundles identiques peuvent se build dans 2 fichiers .JAR de nom différent. J'ai donc gérer comme j'ai pu cette situation pour éviter certaines incohérences au fil des ajouts et des suppressions de fichiers. J'ai décidé que chaque nom de fichier était associé à un unique Bundle, et que si un nouveau fichier ajouté contenait un Bundle déjà installé, celui ci ne s'installerait pas, et la console notifie de la situation (je sais que Java lance déjà une exception si cela arrive, j'ai juste rendu cela plus digeste au niveau de la console).

J'ai utilisé des "instanceof" dans les méthodes update() de mes observers. C'est une pratique que je n'apprécie pas particulièrement, mais je n'ai pas su éviter ça. Du moins je n'ai pas vraiment consacré de temps à fixer cela.

La prochaine fois je noterai mes problèmes rencontrés au fur et à mesure. Car je sais que j'ai rencontré et résolu d'autres problèmes au fil de la semaine, mais je ne me souviens pas suffisamment pour pouvoir les expliquer ici par écrit.
