[![Java CI with Maven](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/ci.yml/badge.svg)](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/ci.yml)
[![Publish Docker image](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/docker.yml/badge.svg)](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/docker.yml)
[![Terraform CloudWatch](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/cloudwatch_dashboard.yml/badge.svg)](https://github.com/jessicafuung/pgr301-DevOps/actions/workflows/cloudwatch_dashboard.yml)

# Eksamen PGR301 DevOps i skyen - Høst 2022
Kandidat: 1044

* [ ] Du kan jobbe i et public-, eller privat repo, og deretter gjøre det public noen timer etter innleveringsfrist hvis du er bekymret for plagiat fra medstudenter.

## Utvikling i Cloud 9
Hvis dere får følgende feilmelding når dere bygger koden med maven i Cloud9, må dere bare gjøre en "mvn clean"

```text
java.lang.Error: 
Unresolved compilation problem: 
        The method builder() is undefined for the type Cart
        at no.shoppifly.CartServiceTest.shouldRemoveCartAfterCheckout(CartServiceTest.java:13)
```

### Bonusoppgave - 5 Poeng

Vi fant aldi ut av hvorfor ovnernevnte problem oppstår av og til med Maven i Cloud9. Hvis du klarer å reprodusere feilen konsekvent
og kan komme med en forklaring på hvorfor dette skjer, og hva vi kan gjøre for å fikse det, gis 5 ekstra poeng. 

## Del 1: DevOps-prinsipper

Beskriv med egne ord;

* Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse
  disse? Hvilke DevOps prinsipper blir brutt?
* En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?
* Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling? 
* Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere
  eller fjerne risiko ved hyppige leveraner.

## Del 2: CI, oppgave 3
*Oppgave:* 
*Branch protection og status sjekker - Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at:*
* [x] *Ingen kan pushe kode direkte på main branch*
* [x] *Kode kan merges til main branch ved å lage en Pull request med minst en godkjenning*
* [ ] *Kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.*

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
### Oppgave 2
* [ ] Du må også rydde opp i ```docker.yml``` workflow filen... Fjern ønødvendige "steps".
* 
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

## Del 4 - Metrics, overvåkning og alarmer
### Oppgave 2
Endre Javakoden slik at den rapporterer følgende Metrics til CloudWatch
* [ ] "carts" -  Antall handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget. (gauge)  
* [ ] "cartsvalue" - Total sum med penger i handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget. (gauge)
* [ ] "checkouts" - Totalt antall  handlevogner er blitt sjekket ut (counts)
* [ ] "checkout_latency" - Gjennomsnittlig responstid for Checkout metoden i Controller-klassen.

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

### Oppgave 3
* [ ] Fullfør cloudwatch_dashboard.tf slik at koden lager et CloudWatch Dashboard med *fire widgets*. Disse skal vise metrikkene fra oppgave 2, Del 4. 
* [ ] Antall handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
* [ ] Total sum med penger i handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
* [ ] Totalt antall  handlevogner er blitt "sjekket ut" per time
* [ ] Gjennomsnittlig responstid for Checkout metoden i Controller-klassen.

### Alarmer
Lag Terraform-kode som oppretter
* [ ] En CloudWatch Alarm  som løses ut dersom antall handlekurver over tre repeternde perioder,på fem minutter, overstiger verdien 5
* [ ] Alarmen skal sendes som e-post til en addresse som gis i workflow filen ```cloudwatch_dashboard.yml``` 

