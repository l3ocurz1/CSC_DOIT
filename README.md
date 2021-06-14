# CSC_DOIT
Piattaforma di progettazione collaborativa e matching di competenze sviluppata per il corso di Ingegneria del Software
   <b>CSC_DOIT</b>, progetto realizzato in <b>Ionic</b> e <b>Spring</b> per il corso di laurea <b>L-31</b> presso <b>Unicam</b>, <i>nell'anno accademico 2020/2021</i>, realizzato dagli studenti Leonardo Curzi, Emanuel Fermani e Giovanni Simeone per l'esame di <b>Ingegneria del Software</b> 
    <br><br><b>



# Panoramica e funzionalità 

**Il fine dell'applicativo proposto è quello di promuovere lo svolgimento collaborativo di progetti innovativi e facilitare l’inserimento delle persone all’interno degli stessi progetti sulla base delle loro competenze. Allo stesso tempo vuole realizzare una vetrina dei progetti svolti in cui un qualsiasi cittadino sia dunque capace di ricercare e visualizzare le informazioni sui progetti e chi li ha svolti.**

Il funzionamento di base del sistema si fonda sulla possibilità per ogni utente di visualizzare la lista dei **progetti** gestiti dall'applicativo, progetto che appartiene a un **organizzazione**, e le informazioni relative alle figure che ne fanno parte o lo gestiscono.

Il funzionamento avanzato del sistema richiede una registrazione e un login da parte dell'utente. Ciò permette di tenere traccia delle proprie **competenze** che gli permetteranno di accedere alla sezione delle **candidature** ai progetti. Inoltre permette la creazione di un organizzazione e tutto quello che ne concerne.
Il funzionamento avanzato del sistema richiede una registrazione e un login da parte dell'utente. Ciò permette di tenere traccia delle proprie **competenze** che gli permetteranno di accedere alla sezione delle **candidature** ai progetti. Inoltre permette la creazione di un'organizzazione e tutto quello che ne concerne.

Ogni progetto è contrassegnato da un **creatore** e da un **team** composto da **membri** del progetto a cui è assegnato un **ruolo**. Inoltre, ogni progetto ha collegata una lista di **competenze richieste** e una lista delle candidature.

Ogni organizzazione  è contrassegnata da un **fondatore** e la rispettiva lista dei **componenti**. I componenti si contraddistingono gerarchicamente e per gruppi funzionali:
- **Membro**: membro base dell'organizazzione, può creare un progetto, candidarsi a un progetto preesistente e visualizzare la lista delle proprie candidature.
Ogni organizzazione  è contrassegnata da un **fondatore** e la rispettiva lista dei **componenti**. I componenti si contraddistinguono gerarchicamente e per gruppi funzionali:
- **Membro**: membro base dell'organizzazione, può creare un progetto, candidarsi a un progetto preesistente e visualizzare la lista delle proprie candidature.
- **Collaboratore**: membro a cui viene assegnata una competenza di alto livello visibile esclusivamente all'interno dell'organizzazione. Inoltre, possono accettare e rifiutare i candidati ai progetti per i ruoli in cui il collaboratore risulta un esperto. 
- **Esperto**: membro a cui viene assegnata una competenza da esperto e viene associato all'organizzazione.Inoltre, può accettare e rifiutare i candidati ai progetti per i ruoli in cui l'esperto risulta un esperto.

Collaboratore, Esperto, Creatore Progetto e Fondatore Organizzazione possiedono gli stessi diritti di Membro.

Un Creatore di un progetto possiede tutti i permessi che concerne la gestione del progetto tra cui la cancellazione e modifica.

Un Fondatore di un organizzazione tutti i permessi che concerne la gestione dell'organizzazione tra cui la cancellazione, modifica e gestione dei membri, collaboratori ed esperti. Inoltre, possiede tutti i diritti di creatore riguardo i progetti associati alla sua organizzazione.
Un Fondatore di un'organizzazione tutti i permessi che concerne la gestione dell'organizzazione tra cui la cancellazione, modifica e gestione dei membri, collaboratori ed esperti. Inoltre, possiede tutti i diritti di creatore riguardo i progetti associati alla sua organizzazione.

# Processo di Sviluppo<a name = "processo"></a>
# ⚙ Processo di Sviluppo<a name = "processo"></a>

Per sviluppare l'applicativo è stato scelto di seguire il processo standardizzato **Unified Process (UP)**, processo iterativo incrementale, utilizzando come strumento di lavoro **Visual Paradigm** basato sul **Unified Modeling Language (UML)**.

Attualmente sono state svolte 3 iterazioni dove è stato possibile effettuare l'analisi dei requisi, la progettazione del sistema, l'implementazione e la fase di testing.
Attualmente sono state svolte 3 iterazioni dove è stato possibile effettuare l'analisi dei requisiti, la progettazione del sistema, l'implementazione e la fase di testing.

Come strumento di versioning è stato utilizzato **Git** attraverso il quale sono stati distinti due brach per sviluppo.
Come strumento di versioning è stato utilizzato **Git** attraverso il quale sono stati distinti tre brach per sviluppo.
- master: utilizzato per pubblicare la baseline (artefatti) sviluppati a fine iterazione.
- develop: utilizzato per sviluppo dell'iterazione corrente.
- develop: utilizzato per lo sviluppo fino alla terza iterazione in corrispondenza alla consegna per Ingegneria del Software.
- pawm: utilizzato per lo sviluppo dell'iterazione corrente in concomitanza alla consegna per Progettazione di Applicazioni Web e Mobili.

Le varie iterazioni hanno dato origine ai seguenti artefatti:
- Diagramma dei casi d'uso: raccolta e specifica dei requisiti e funzionalità del sistema.
- Diagramma classi di analisi: identificano i concetti che è necessario il sistema rappresenti e sia capace di manipolare.
- Diagrammi di seguenza: descrivono come le classi di analisi interagiscono tra di loro per realizzare il comportamento definito nei casi d'uso.
- Diagrammi di sequenza: descrivono come le classi di analisi interagiscono tra di loro per realizzare il comportamento definito nei casi d'uso.
- Diagramma classi di progetto: realizzato sfruttando il principio LRG (Low Representational Gap) per derivare le classi di progetto partendo dalle classi di analisi, il diagramma verrà utilizzato per le attività di implementazione.
- Code Base


 # Tecnologie utilizzate

Il lato backend si basa sul linguaggio **Java** e rende disponibile per l'interazione delle **Api Rest**, la cui scrittura e gestione, anche sotto l'ottica della sicurezza, sono state rese possibili grazie al framework **Spring Boot**. Per il testing del codice scritto ci si è affidati al framework **JUnit** mentre per il building automatizzato del sistema si è impiegato il tool **Gradle**.
Il lato back end si basa sul linguaggio **Java** e rende disponibile per l'interazione delle **Api Rest**, la cui scrittura e gestione, anche sotto l'ottica della sicurezza, sono state rese possibili grazie al framework **Spring Boot**. Per il testing del codice scritto ci si è affidati al framework **JUnit** mentre per il building automatizzato del sistema si è impiegato il tool **Gradle**.

Per quanto concerne la persistenza delle informazioni processate a livello di backend si è deciso di sfruttare i servizi offerti da **MongoDB** e dal relativo framework per linguaggio Java.
Per quanto concerne la persistenza delle informazioni processate a livello di back end si è deciso di sfruttare i servizi offerti dal DBMS non relazionale **MongoDB** e dal relativo framework per linguaggio Java.

Il frontend è interamante scritto utilizzando il framework **Ionic** e scegliendo come user interface il framework **Angular** basato su **TypeScript**. L'applicativo si sostanzia in un app mobile Ibrida, utilizzando la metodologia Single Page Application, che interagisce con il backend tramite chiamate HTTP alle Api Rest rese disponibili. 
L'applicativo sarà installabile su Android o IOS tramite l'utilizzo di **Capacitor** che permette di effettuare chiamate rest più specifiche per la piattaforma scelta.
Il frontend è interamente scritto utilizzando il framework **Ionic** e scegliendo come user interface il framework **Angular** basato su **TypeScript**. L'applicativo si sostanzia in un app mobile Ibrida, utilizzando la metodologia Single Page Application, che interagisce con il back end tramite chiamate HTTP alle Api Rest rese disponibili. 
L'applicativo sarà installabile su Android o IOS tramite l'utilizzo di **Capacitor** che permette di effettuare chiamate rest più specifiche per la piattaforma scelta. 
Inoltre, è usufruibile come PWA tramite Web e come applicazione Desktop tramite l'utilizzo del framework **Electron**.

Inoltre abbiamo utilizzato la funzionalita enviroment, messa a disposizione da ionic, che permette di andare ad utillizare due diverse "classi" per le variabili d'ambiente, una dove abbiamo le variabili per il testing , dove si richiama un database locale ed uno (--prod) per la produzione , che permette di ottimizzare la compilazione andando a rendere il typescript più efficente, dove abbiamo un collegamento ad un server, dove gira il backend pronto per il release ed in attesa del rilascio del prodotto.

Per quanto concerne le informazioni di autenticazione tra client e server si è deciso di sfruttare lo standard **JSON Web Token (JWT)**. 
Si è deciso di implementare un autorization server sfruttando le librerie messe a disposizione dal cloud provider **Auth0**, quest'ultime sono state utilizzate soltanto per la generazione e validazione del token in locale.
Si è deciso di implementare un authorization server sfruttando le librerie messe a disposizione dal cloud provider **Auth0**, quest'ultime sono state utilizzate soltanto per la generazione e validazione del token in locale.


# Autori 

- [Leonardo Curzi](https://github.com/l3ocurz1)
- [Emanuel Fermani](https://github.com/TheFermani71)
- [Giovanni Simeone](https://github.com/giosimeo)
