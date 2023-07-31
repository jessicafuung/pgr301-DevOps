[![Java CI with Maven](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/ci.yml/badge.svg)](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/ci.yml)
[![Publish Docker image](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/docker.yml/badge.svg)](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/docker.yml)
[![Terraform CloudWatch](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/cloudwatch_dashboard.yml/badge.svg)](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/cloudwatch_dashboard.yml)

# Eksamen PGR301 DevOps i skyen - Høst 2022
Kandidat: 1044
Karakter: B 

## Scenario
Som DevOps-ekspert, ferdig utlært fra Høgskolen Kristiania blir du ansatt i en bedrift, "Shopifly" som selger droner, men også andre varer nå som det nærmer seg jul.

Shopifly har store utfordringer med utviklingsprosessen sin

De deployer kode første mandag i kvartalet.

De pleide å deploye oftere før- men dette førte til ustabilitet. Selskapet ansatte flere testere, og startet en prosess der utviklingsledere måtte se over og godkjenne alle leveranser. De senket samtidig frekvensen på leveransene sine for å få bedre stabilitet.

Når de deployer, feiler det fortsatt ofte. Da ruller de tilbake til forrige versjon, og ny funksjonalitet blir derfor ofte forsinket i månedsvis

Leveransen skjer ved at Utviklingsteamet bruker FTP til å overføre en Spring boot JAR sammen med dokumentasjon i en ZIP. En egen avdeling tar i mot disse filene og installerer i AWS / Produksjon.

For å løse problemene sine, leide selskapet så inn DevOps-kompetanse fra Gaffel Consulting. Etter å ha sendt fire juniorkonsulenter som fakturerte for en liten formue ble det klart at de aldri kom til å klare å levere, og kontrakten ble sagt opp. "Jim" den "mest senior" av juniorkonsulentene har lagt inn noen kommentarer i koden som kan være til hjelp.

Det Gaffel Consulting klarte å levere på den medgåtte tiden ligger i dette repositoryet.

Nå er det din tur til å ta over!

## Del 1: DevOps-prinsipper

Beskriv med egne ord;

*Spørsmål: Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse disse? Hvilke DevOps prinsipper blir brutt?*

**Svar**\
Shopifly sliter med flyt i utviklinsprosessen. De kun deployer koden èn gang i kvartalet. Med sjeldne deployments som dette er sannsynligheten for å produsere unødvendig kode (waste) stor. Det kan til og med være de bruker tid på å kode funksjonaliteter før det trengs (som også er waste). Sjeldne deployments fører også til forsinkelser i å levere ny funksjonalitet til kundene. I tillegg er det tydelig at de ofte oppstår feil når de deployer, som igjen fører til ytterligere forsinkelser og mangel på stabilitet i produksjonsmiljøet deres.

Innføring av DevOps-prinsipper kan hjelpe å løse de nevnte utfordringene. Et utgangspunkt kan være ved å implementere kontinuerlig integrasjon og kontinuerlig levering (CI/ CD) med GitHub. Da kan selskapet deploye kode oftere, og kode med høyere kvalitet. Dette kan også bidra til å redusere forsinkelser i leveransen av ny funksjonalitet, og øke stabiltiet i produksjonsmiljøet deres.

En annen løsning er innføring av automatisert testing. Dette kan bidra til økt kodekvalitet som deployes, og identifisere eventuelle feil raskere. Dette bidrar til å redusere antall feil som oppstår ved deployments, og dermed redusere behovet for å rulle tilbake til tidligere versjoner. 

DevOps-prinsippene som har blitt brutt i dagens utviklingsprosess er bl.a kontinuerlig levering og automatisert testing.
  
*Spørsmål: En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?*

**Svar**\
Responsen kan imidlertid være en ineffektiv tilnærming fra et DevOps-perspektiv, da det kan føre til forsinkelser i leveransen av ny funksjonalitet, og en økt risiko for at det oppstår feil ved deploy. Flyten blir rett og slett dårlig. 

En bedring tilnærming kan være å jobbe med å forbedre kvaliteten på koden som blir utviklet - ved bruk av automatisert testing og kontinuerlig integrasjon. Dette kan også bidra til å sikre at koden er stabil og fungerer som forventet, før den evt deployes til produksjon.

En annen bedre tilnærming kan være å øke frekvensen på leveransene deres, feks ved å implementere kontinuerlig levering (CD). Ved å gjøre leveransene oftere kan selskapet levere ny funksjonalitet til kundene raskere, samtidig som de kan fange opp eventuelle feil raskt og enkelt. 

*Spørsmål: Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?*

**Svar**\
En utfordring ved at teamet overleverer kode til en annen avdeling som har ansvar for drift, er at det kan føre til dårligere samarbeid og kommunikasjon mellom utviklingsteamet og produksjonsavdelingen. Dette kan igjen gøre til forsinkelser i leveransen av ny funksjonalitet, samt en økt riski for at det oppstår feil ved deploy.

Fra et DevOps-perspektiv kan en bedre løsning være å ha ett team som har ansvar for både drift og utvikling. Ved å "samle" ansvarsområdene i ett team gjør at samarbeidsprosessen blir tettere og det en bedre forståelse for hverandres utfordringer og behov. Utviklerne får også skin in the game - dersom koden de produserer blir dårlig, er det de selv som må ta konsekvensene av det. 

*Spørsmål: Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveraner.*

**Svar**\
Å release kode for ofte kan by på problemer som ikke-fungerende havner i produksjon. For å redusere eller fjerne risikoen ved hyppige realese kan man benytte seg av DevOps-prinsipper som automatisert testing og kontinuerlig integrasjon. Automatisert testing kan bidra til å sikre at koden som utvikles er stabil, og fungerer som forventet før den eventuelt deployes til produksjon. Dette kan redusere feil som kan oppstå ved deployment, og dermed også redusere behovet for å rulle tilbake til ridlgiere versjoner. Kontinuerlig integrasjon kan også bidra til å redusere risikoen fordi ved å integrere endringer i koden kontinuerlig kan man sikre at nye endringer ikke bryter med eksisterende funksjonalitet, og dermed unngå å introdusere feil i produksjonsmiljøet. 

## Del 2: CI, 
### Oppgave 3
*Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at:*
*Ingen kan pushe kode direkte på main branch*
*Kode kan merges til main branch ved å lage en Pull request med minst en godkjenning*
*Kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.*

**Svar:**\
PS! Branch protection kan ikke konfigureres når repositoryen er satt som private.

Fremgangsmåte:
1. Gå til "settings -> branches", og deretter "protection rules".
2. Klikk "add", og deretter velg "main" som branch.
3. Velg "require a pull request before merging".
4. Velg "require status check to pass before merging".
5. I søkefeltet skriv inn teksten "build" som skal la deg velge "github actions".
 
Nå skal det ikke være mulig å merge en pull request inn i "main"-branch uten at status er i orden. Ingen i teamet kan heller "snike" seg unna med denne sjekken ved å committe kode rett på main branch.

Peer review:
1. Gå til "settings -> branches, og deretter "protection rules".
2. Klikk "main" branch.
3. Velg "edit" for eksisterende branch protection rule.
4. Under "require a pull request before passing", og deretter kryss av "require approvals".

## Del 3 - Docker
### Oppgave 1
*Beskriv hva du må gjøre for å få workflow til å fungere med din DockerHub konto? Hvorfor feiler workflowen?*
**Svar:**\
Feilmeldingen i Actions forteller at brukernavn og passord mangler. 
I "docker.yml" står det at den bruker secrets for å logge inn på DockerHub. 

Legg inn credentials slik:
1. Settings -> Secrets -> Actions
2. Klikk "New repository secret" -> Fyll inn "DOCKER_HUB_USERNAME" og brukernavnet mitt til DockerHub.
3. Klikk "New repository secret" -> Fyll inn "DOCKER_HUB_TOKEN" og passordet mitt til DockerHub.

Push til main, og et container image skal nå kunne pushes til DockerHub.

### Oppgave 3
*Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.*
**Svar:** 
1. Sett opp et eget ECR repository på AWS
2. Gå inn på IAM via AWS
3. Gå inn på "users" og søk på brukernavnet ditt
4. Klikk "security credentials" -> "create access key"
5. Bruk deretter de genererte secrets til å legge inn repository secrets, for å gi Github Actions nøkler.
6. I docker.yml filen tar vi bort workflowen som ble satt opp for å koble til DockerHub
7. Setter inn ny workflow som kobler til ECR repositoryen (Se .github/workflows/docker.yml).

## Del 5 - Terraform og CloudWatch Dashboards
Konsulentene i Gaffel consulting hadde ambisiøse planer om å få Terraform-koden i dette repoet til å kjøre
i GitHub Actions. Workflowen kjørte bra første gang, men nå feiler den hver gang, og klager over at en bucket med samme navn allerede eksisterer.
Shopifly har tenkt på bruke denne bucketen til data-analyse.

```text
Error: creating Amazon S3 (Simple Storage) Bucket (analytics-jim): BucketAlreadyOwnedByYou: 
Your previous request to create the named bucket succeeded and you already own it.
```

De kommenterte derfor bare ut S3 bucket koden, og gikk videre til neste oppgave.
Se på ```providerx.tf filen```. 

### Oppgave 1
*Forklar med egne ord. Hva er årsaken til dette problemet? Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer?*
**Svar:**\
Feilmeldingen skyldes av at terraform.tfstate filen har blitt slettet. 
En tfstate-fil inneholder informasjon om infrastrukturen som er bygd ved hjelp
av Terraform.
Denne filen ligger inni en katalog som inneholder terraform provider for AWS, slik at Terraform kan lage, endre og slette infrastrukturen i AWS.
Første gang Jim kjørte ```terraform apply``` bygde han en S3-bucket og lagret informasjonen om dette i hans tfstate-fil. 
Når denne tfstate-filen har blitt slettet og samtidig kjører ```terraform apply``` på nytt, vil Terraform prøve å opprette bucketen på nytt fordi den ikke lenger vet at den har blitt opprettet - 
Nemlig fordi denne informasjonen ligger i den såkalte "state" filen som har blitt slettet.
For å starte med blanke ark må man slette bucketen som allerede eksisterer inne på AWS kontoen, og fjerne evt terraform.state, hele .terraform katalogen og alle filer som starter med ```.terraform```.

**Kommentar:**\
Jeg opplevde et "chicken/egg problem" mens jeg løste oppgaven. Fikk en feilmelding om at S3-bucketen ikke eksisterer. 
Jeg opprettet en state lokalt først (terraform init, plan og apply). 
Deretter la jeg inn backend i provideren, og flyttet eksisterende state til s3-bucketen som ble laget. 

### Oppgave 2
**Kommentar:**\
I oppgaveteksten står det *"et annet **problem** er at "terraform apply" bare blir kjørt hver  gang noen lager en Pull request."*,
og videre i oppgaven står det *"terraform plan på når det lages en Pull request"*. Jeg tolket oppgaven som at det er ønskelig å
kun kjøre terraform plan når det lages en pull request.
