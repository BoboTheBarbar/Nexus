## Zugriff via DemoController

curl localhost:8085/users/

gibt '[]' zurueck.

## Zugriff via Camel REST DSL

curl localhost:5555/users/

gibt '[]' zurueck.

## Abruf der Metriken

curl localhost:8085/actuator/prometheus

zeigt Metriken CamelRoutePolicy und CamelMessageHistory.
(Metriken tauchen erst auf, nachdem Exchanges gelaufen sind.)