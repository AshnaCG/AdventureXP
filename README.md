# AdventureXP

Reservationssystem til Adventure Alley, udviklet som XP-projekt på 3. semester.

## Kør systemet

Til dig, der vil afprøve systemet. Kører den seneste version fra `main`, som CI har bygget og testet. Kræver kun [Docker](https://www.docker.com/products/docker-desktop/).

1. Hent `docker-compose.ghcr.yml` og `.env.example` fra mappen `adventurexp/`, og læg dem i samme mappe.
2. Kopiér `.env.example` til `.env`, og udfyld `MYSQL_PASSWORD` og `MYSQL_ROOT_PASSWORD`, før du starter systemet:
```
   cp .env.example .env
```
   Har din browser gemt filen som `env.example` (uden punktum), så brug `cp env.example .env`.
3. Start systemet:
```
   docker compose -f docker-compose.ghcr.yml pull
   docker compose -f docker-compose.ghcr.yml up -d
```
4. Åbn http://localhost:8090

Stop systemet med `docker compose -f docker-compose.ghcr.yml down`. Tilføj `-v` for også at slette databasen.

Vil du køre en bestemt version, så skift `:latest` i `docker-compose.ghcr.yml` ud med et tag fra GHCR, fx `:main-25be8a1`.

## Fejlfinding

### Appen svarer ikke på port 8090 eller genstarter igen og igen

Se i loggen:
```
docker compose -f docker-compose.ghcr.yml logs app
```

Står der `Access denied for user 'adventurexp'`, findes der en database fra en tidligere kørsel med andre passwords end dem i din `.env`. MySQL sætter kun passwords, første gang databasen oprettes, og databasen bliver liggende, selvom systemet stoppes med `down`.

Nulstil databasen, så den oprettes forfra med passwords fra `.env`:
```
docker compose -f docker-compose.ghcr.yml down -v
docker compose -f docker-compose.ghcr.yml up -d
```
*Bemærk: `-v` sletter alle data i databasen.*
