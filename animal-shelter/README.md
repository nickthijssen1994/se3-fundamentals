# Abstracte klassen en interfaces

De onderwerpen van deze opdracht maken het mogelijk om onze applicatie wat
duidelijker te structuren. Door klassen abstract te maken voorkomen we dat er
een instantie aangemaakt kan worden; interfaces trekken dit nog een stapje
verder door: een interface mag zelfs helemaal geen functionele code bevatten.
Waarom zouden we dit willen? In de opdracht voor dit leerdoel passen we de
applicatie aan zodat een situatie gegeven wordt waarin deze concepten nuttig
zijn.

## De opdracht

Het aanmaken van een `Animal` instantie is, als we er even over nadenken, vrij
onlogisch. Een `Cat` of `Dog` is duidelijk een concreet dier, maar wat is een
`Animal` nu precies? Het is dus ongewenst om deze klasse te instantiëren: nu
willen we dit ook afdwingen in de code zodat we dit niet per ongeluk doen. Maak
de `Animal` klasse `abstract` door dit woord op te nemen in de klassedefinitie.
Probeer nadat je dit gedaan hebt eens even uit van welke klassen je nu nog wel
een instantie aan kunt maken en wanneer dit onmogelijk geworden is.

### Interfaces

Vervolgens gaan we verder met het concept interfaces. De interface die we gaan
definiëren noemen we `ISellable` en voorzien we van twee properties: een
`string Name` en een `decimal Price`. Wat we hiermee modelleren is een
verkoopbaar "iets", waarbij "iets" tenminste een naam en een prijs heeft.

Om wat meer geld in het laatje te brengen gaat het dierenasiel namelijk
producten zoals dierenvoer verkopen. Breidt je applicatie uit zodat het
mogelijk wordt om dierenvoer toe te voegen aan de winkel. Het is aangeraden om
hiervoor een nieuwe klasse (bijvoorbeeld `Webshop`) aan te maken om de te
verkopen producten in te beheren. Via de GUI kan de gebruiker dan een nieuw
product aanmaken, voorzien van een naam en prijs. Toon deze producten in een
lijst op het scherm, en zorg dat het mogelijk wordt om een product te "kopen".

Daarnaast kunnen ook de dieren verkocht worden. Maar hoe zorgen we nu dat deze
ook in de webshop terecht kunnen komen? Dit doen we door ook de `Animal` klasse
de `ISellable` interface te laten implementeren. Dit heeft tot gevolg dat ieder
dier een prijs moet hebben.

### Prijsbepaling van dieren

De prijs van een dier wordt per soort bepaald. Voor katten geldt dat de prijs
afhankelijk is van de lengte van de `BadHabits` string. De maximumprijs van een
kat is € 350,-. Voor ieder karakter in de `BadHabits` string gaat er echter €
20,- van de prijs af. De gedachte hierachter is dat hoe meer tekst nodig is om
de slechte eigenschappen te beschrijven, hoe minder gewild de kat zal zijn.
Een kat kan echter nooit goedkoper zijn dan € 35,-.

Voor honden geldt een andere logica. Het asiel krijgt vooral katten, maar wil
eigenlijk wat meer honden behouden. Om deze reden zijn honden die als eerste in
het asiel komen het duurste; naar mate er meer en meer honden gebracht worden
zakt de prijs van deze later gebrachte honden. De eerste hond die binnenkomt
zal een prijs krijgen van € 500,-. Iedere hond die daarna binnen komt zal
steeds € 50,- goedkoper worden. Een hond zal nooit goedkoper zijn dan € 50,-.
*Tip: voor het bijhouden van het aantal aangemaakte honden kan `static` goed
van pas komen.*

## Inleveren

Ben je zover gekomen als je kon? Lever dan bij de assignment op Canvas de link
in naar je GitLab-project. Geef als toelichting aan wat er goed ging of waar je
juist niet uit kwam zodat de docent zo gericht mogelijk feedback kan geven.

