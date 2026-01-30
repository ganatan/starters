/* -------------------- CONTINENTS --------------------------------------- */
SELECT
  t1.CODE as code,
  t1.NAME as name,
  t1.WIKIPEDIA_LINK as "wikipediaLink",
  t1.AREA as "area",
  t1.POPULATION as "population",
  t1.COUNTRIES_NUMBER as "countriesNumber"
FROM
  RAG_CONTINENT t1;

SELECT
  t1.ID as "id",
  t1.NAME as "name",
  t1.WIKIPEDIA_LINK as "wikipediaLink",
  t2.ID as "countryId",
  t2.NAME as "countryName",
  t2.WIKIPEDIA_LINK as "countryWikipediaLink"
FROM
  RAG_CONTINENT t1
  INNER JOIN RAG_COUNTRY t2 ON t2.CONTINENT_ID = t1.ID
WHERE
  t1.ID = 1000;

/* -------------------- COUNTRIES --------------------------------------- */
SELECT
  t1.ID as "id",
  t1.NAME as "name",
  t1.WIKIPEDIA_LINK as "wikipediaLink",
  t1.ISO_NUMERIC as "isoNumeric",
  t1.ISO_ALPHA2 as "isoAlpha2",
  t1.ISO_ALPHA3 as "isoAlpha3",
  t1.FLAG as flag,
  t2.ID as "continentId",
  t2.NAME as "continentName",
  t2.WIKIPEDIA_LINK as "continentWikipediaLink"
FROM
  RAG_COUNTRY t1
  INNER JOIN RAG_CONTINENT t2 ON t2.ID = t1.CONTINENT_ID
WHERE
  t1.ID = 1000;

/* -------------------- CITIES --------------------------------------- */
SELECT
  t1.ID as id,
  t1.NAME as name,
  t1.WIKIPEDIA_LINK as wikipediaLink,
  t2.ID as countryId,
  t2.NAME as countryName,
  t2.WIKIPEDIA_LINK as countryWikipediaLink,
  t3.ID as continentId,
  t3.NAME as continentName,
  t3.WIKIPEDIA_LINK as continentWikipediaLink
FROM
  RAG_CITY t1
  INNER JOIN RAG_COUNTRY t2 ON t2.ID = t1.COUNTRY_ID
  INNER JOIN RAG_CONTINENT t3 ON t3.ID = t2.CONTINENT_ID;

/* -------------------- PERSONS --------------------------------------- */
SELECT
  t1.ID as id,
  t1.NAME as name,
  t1.BIRTH_DATE as birthDate,
  t1.WIKIPEDIA_LINK as wikipediaLink,
  t2.NAME as city,
  t3.NAME as country,
  t4.NAME as continent
FROM
  RAG_PERSON t1
  INNER JOIN RAG_CITY t2 ON t2.ID = t1.BIRTH_CITY_ID
  INNER JOIN RAG_COUNTRY t3 ON t3.ID = t2.COUNTRY_ID
  INNER JOIN RAG_CONTINENT t4 ON t4.ID = t3.CONTINENT_ID;
