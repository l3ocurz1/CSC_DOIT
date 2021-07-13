# CSC_DOIT
Piattaforma di progettazione collaborativa e matching di competenze 
   <b>CSC_DOIT</b>, progetto realizzato in <b>ANGULAR</b> e <b>Spring</b> per il corso di laurea <b>L-31</b> presso <b>Unicam</b>, <i>nell'anno accademico 2020/2021</i>, realizzato dagli studenti Leonardo Curzi, Emanuel Fermani per l'esame di <b>Progettazione di Applicazioni Web e Mobile</b> e <b>Ingegneria del Software</b>
    <br><br><b>



# Panoramica e funzionalità 

Il fine dell'applicativo web proposto è quello di promuovere lo svolgimento collaborativo di progetti innovativi e facilitare l’inserimento delle persone all’interno degli stessi progetti sulla base delle loro competenze. Allo stesso tempo vuole realizzare una vetrina dei progetti svolti in cui un qualsiasi cittadino sia dunque capace di ricercare e visualizzare le informazioni sui progetti e chi li ha svolti.

Il funzionamento di base del sistema si fonda sulla possibilità per ogni utente di visualizzare la lista dei **progetti** gestiti dall'applicativo, progetto che appartiene a un **ente** o un privato **progettista**, e le informazioni relative alle figure che ne fanno parte o lo gestiscono.

Il funzionamento avanzato del sistema richiede una registrazione e un login da parte dell'utente. Ciò permette di tenere traccia delle proprie **competenze** che gli permetteranno di accedere alla sezione delle **candidature** ai progetti. Inoltre permette la creazione di un ente e tutto quello che ne concerne.


Ogni progetto è contrassegnato da un **progettista** e da un **ente** composto a sua volta da **progettisti** . Inoltre, ogni progetto ha collegata una lista di **competenze richieste** e una lista delle candidature. I **progettisti** dovrannanno a loro volta, specificare le proprie competenze al momento della registrazione o nel loro profilo.

 
Progettista, Esperto, Ente e Privato Proponente possiedono gli stessi diritti di Utente.

Il creatore di un progetto possiede tutti i permessi che concerne la gestione del progetto tra cui la cancellazione e modifica.



# Processo di Sviluppo


Per sviluppare l'applicativo è stato scelto di seguire il processo standardizzato **Unified Process (UP)**, processo iterativo incrementale, utilizzando come strumento di lavoro **Visual Paradigm** basato sul **Unified Modeling Language (UML)**.

Attualmente sono state svolte 3 iterazioni dove è stato possibile effettuare l'analisi dei requisi, la progettazione del sistema, l'implementazione e la fase di testing.
   
Le varie iterazioni hanno dato origine ai seguenti artefatti:
- Diagramma dei casi d'uso: raccolta e specifica dei requisiti e funzionalità del sistema.
- Diagramma classi di analisi: identificano i concetti che è necessario il sistema rappresenti e sia capace di manipolare.
- Diagrammi di sequenza: descrivono come le classi di analisi interagiscono tra di loro per realizzare il comportamento definito nei casi d'uso.
- Diagramma classi di progetto: realizzato sfruttando il principio LRG (Low Representational Gap) per derivare le classi di progetto partendo dalle classi di analisi, il diagramma verrà utilizzato per le attività di implementazione.
- Code Base



 # Tecnologie utilizzate

Il lato backend si basa sul linguaggio **Java** e rende disponibile per l'interazione delle **Api Rest**, la cui scrittura e gestione, anche sotto l'ottica della sicurezza, sono state rese possibili grazie al framework **Spring Boot**. Per il testing del codice scritto ci si è affidati al framework **SpingBootTest** mentre per il building automatizzato del sistema si è impiegato il tool **Maven**.
Il lato back end si basa sul linguaggio **Java** e rende disponibile per l'interazione delle **Api Rest**, la cui scrittura e gestione, anche sotto l'ottica della sicurezza, sono state rese possibili grazie al framework **Spring Boot**. Per il testing del codice scritto ci si è affidati al framework **SpingBootTest** mentre per il building automatizzato del sistema si è impiegato il tool **Maven**.

Per quanto concerne la persistenza delle informazioni processate a livello di backend si è deciso di sfruttare i servizi offerti da **h2DB** e dal relativo framework per linguaggio Java.


Il frontend è interamante scritto utilizzando il framework  **Angular** basato su **TypeScript**.  L'applicativo in sostanza è un applicativo web, utilizzando la metodologia Single Page Application, che interagisce con il back end tramite chiamate alle Api Rest rese disponibili. L'applicativo sarà visibile tramite il browser sia da web che da mobile una volta caricata nel server. Il front-end viene eseguito attraverso il runtime system node.js

Per quanto concerne lo scambio di informazioni tra client e server si è deciso di sfruttare lo standard JavaScript Object Notation (JSON). 


# Autori 

- [Leonardo Curzi](https://github.com/l3ocurz1)
- [Emanuel Fermani](https://github.com/TheFermani71)

