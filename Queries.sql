SELECT country_general.name,
       current_heads_of_states.first_name,
       current_heads_of_states.last_name
FROM country_general
LEFT JOIN current_heads_of_states
ON country_general.head_of_state_id=current_heads_of_states.head_of_state_id;

SELECT country_general.name,
       COUNT(*) AS number_of_seas
FROM country_general
JOIN country_sea
ON country_general.country_id = country_sea.country_id
JOIN seas
ON country_sea.sea_id = seas.sea_id
GROUP BY country_general.name;

WITH c_p as (
Select country_id,
name,
first_name,
last_name,
capital_id
from country_general
join current_heads_of_states
on country_general.head_of_state_id=current_heads_of_states.head_of_state_id)
select c_p.name, c_p.first_name, c_p.last_name, currency.name, cities.name
from c_p
join currency
on c_p.currency_id = currency.currency_id
join country_capital
on c_p.coutry_id = country_capital.country_id
join cities
on country_capital.city_id = cities.city_id;

WITH c_c AS (
    SELECT country_general.name AS country, 
           cities.name AS capital, 
           currency.currency_code AS currency, 
           languages.name AS language, 
           country_general.head_of_state_id AS head
FROM country_general
JOIN country_capital
ON country_general.country_id = country_capital.country_id
JOIN cities
ON country_capital.city_id = cities.city_id
LEFT JOIN currency
ON country_general.currency_id = currency.currency_id
LEFT JOIN languages
ON country_general.language_id = languages.language_id
)

SELECT country, 
       capital, 
       currency, 
       language, 
       current_heads_of_states.first_name, 
       current_heads_of_states.last_name
FROM c_c
LEFT JOIN current_heads_of_states
ON c_c.head = current_heads_of_states.head_of_state_id;