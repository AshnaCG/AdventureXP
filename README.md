# AdventureXP

Reservationssystem til Adventure Alley, udviklet som XP-projekt på 3. semester.

## Kør systemet

Til dig, der vil afprøve systemet. Kører den seneste version fra `main`, som CI har bygget og testet. Kræver kun [Docker](https://www.docker.com/products/docker-desktop/).

1. Hent `docker-compose.ghcr.yml` og `.env.example` fra mappen `adventurexp/`, og læg dem i samme mappe.
2. Kopiér `.env.example` til `.env`, og udfyld `MYSQL_PASSWORD` og `MYSQL_ROOT_PASSWORD`:
```
   cp .env.example .env
```
3. Start systemet:
```
   docker compose -f docker-compose.ghcr.yml pull
   docker compose -f docker-compose.ghcr.yml up -d
```
4. Åbn http://localhost:8090

Stop systemet med `docker compose -f docker-compose.ghcr.yml down`. Tilføj `-v` for også at slette databasen.

Vil du køre en bestemt version, så skift `:latest` i `docker-compose.ghcr.yml` ud med et tag fra GHCR, fx `:main-25be8a1`.

## Fejlfinding (Troubleshooting)

### Appen kan ikke åbnes på localhost / Genstarter i et loop
Hvis appen ikke vil indlæse på port 8090, kan det skyldes en klassisk Docker-adfærd, hvor MySQL genbruger forældede adgangskoder fra en tidligere initialisering. Hvis din log (`docker compose -f docker-compose.ghcr.yml logs`) viser en `Access denied for user`-fejl, skal databasen nulstilles, før rettelser i din `.env`-fil træder i kraft.

Kør følgende kommando for at slette de låste databasefiler og lav en genstart med de korrekte koder:
```bash
docker compose -f docker-compose.ghcr.yml down -v
docker compose -f docker-compose.ghcr.yml up -d
```
*Bemærk: Flaget `-v` sletter alt eksisterende testdata i databasen.*
