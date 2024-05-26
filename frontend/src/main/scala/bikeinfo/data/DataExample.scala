package bikeinfo.data

import bikeinfo.model.*

object DataExample:

  def data = """[
  {
    "stationId": 574,
    "lat": 59.9107393,
    "lon": 10.7609998,
    "name": "Annette Thommessens Plass",
    "address": "Annette Thommesens Plass",
    "description": "",
    "availableDocks": 10,
    "availableBikes": 1
  },
  {
    "stationId": 619,
    "lat": 59.942780248542554,
    "lon": 10.722993731144015,
    "name": "Bak Niels Treschows hus nord",
    "address": "Problemveien P. A. Munchs Hus",
    "description": "",
    "availableDocks": 0,
    "availableBikes": 42
  },
  {
    "stationId": 507,
    "lat": 59.91914743024347,
    "lon": 10.76412958416006,
    "name": "Jens Bjelkes Gate",
    "address": "Jens Bjelkes gate / Trondheimsveien",
    "description": "",
    "availableDocks": 20,
    "availableBikes": 0
  },
  {
    "stationId": 455,
    "lat": 59.922337,
    "lon": 10.7617503,
    "name": "Sofienbergparken sør",
    "address": "Sofienberggata 17",
    "description": "",
    "availableDocks": 12,
    "availableBikes": 0
  },
  {
    "stationId": 444,
    "lat": 59.925265,
    "lon": 10.750462,
    "name": "AHO",
    "address": "Arkitektur og Designhøgskolen i Oslo",
    "description": "",
    "availableDocks": 13,
    "availableBikes": 6
  },
  {
    "stationId": 735,
    "lat": 59.90321254732584,
    "lon": 10.7673442932541,
    "name": "Oslo Hospital",
    "address": "Oslo Gate 39",
    "description": "",
    "availableDocks": 9,
    "availableBikes": 6
  },
  {
    "stationId": 458,
    "lat": 59.928349,
    "lon": 10.77837,
    "name": "Jenny Braatens Plass",
    "address": "Trondheimsveien 135",
    "description": "",
    "availableDocks": 1,
    "availableBikes": 14
  },
  {
    "stationId": 563,
    "lat": 59.9126844,
    "lon": 10.7229003,
    "name": "Huitfeldts gate",
    "address": "Huitfeldts gate 24",
    "description": "",
    "availableDocks": 14,
    "availableBikes": 2
  },
  {
    "stationId": 381,
    "lat": 59.91252,
    "lon": 10.76224,
    "name": "Grønlands torg",
    "address": "Grønlands torg",
    "description": "",
    "availableDocks": 14,
    "availableBikes": 1
  },
  {
    "stationId": 624,
    "lat": 59.9287502718149,
    "lon": 10.767546377298158,
    "name": "Dælenenggata",
    "address": "Dælenenggata 38",
    "description": "",
    "availableDocks": 1,
    "availableBikes": 19
  },
  {
    "stationId": 388,
    "lat": 59.9163309,
    "lon": 10.716349,
    "name": "Skovveien",
    "address": "Skovveien ved Frognerveien",
    "description": "",
    "availableDocks": 0,
    "availableBikes": 18
  },
  {
    "stationId": 616,
    "lat": 59.906922167198275,
    "lon": 10.746530272588245,
    "name": "Salt",
    "address": "Salt ved Langkaia",
    "description": "",
    "availableDocks": 15,
    "availableBikes": 0
  },
  {
    "stationId": 589,
    "lat": 59.89853221724158,
    "lon": 10.674403950323182,
    "name": "Huk 1",
    "address": "Huk",
    "description": "",
    "availableDocks": 15,
    "availableBikes": 10
  },
  {
    "stationId": 436,
    "lat": 59.9244031,
    "lon": 10.7130691,
    "name": "Vestkanttorvet",
    "address": "Vestkanttorvet",
    "description": "",
    "availableDocks": 0,
    "availableBikes": 13
  },
  {
    "stationId": 2357,
    "lat": 59.91111544486938,
    "lon": 10.730396895054923,
    "name": "Aker Brygge 2 mot Rådhusplassen",
    "address": "Brynjulf Bulls plass 2, 0250 Oslo",
    "description": "",
    "availableDocks": 4,
    "availableBikes": 16
  },
  {
    "stationId": 2329,
    "lat": 59.91271286014364,
    "lon": 10.727170827087008,
    "name": "Via Vika",
    "address": "Dronning Mauds gate 10",
    "description": "",
    "availableDocks": 8,
    "availableBikes": 7
  },
  {
    "stationId": 1101,
    "lat": 59.91065301806209,
    "lon": 10.737365277561025,
    "name": "Stortingstunellen",
    "address": "Rådhusgata 34",
    "description": "",
    "availableDocks": 13,
    "availableBikes": 11
  },
  {
    "stationId": 541,
    "lat": 59.9080552,
    "lon": 10.7479977,
    "name": "Langkaia",
    "address": "rådhusgata / langkaia",
    "description": "",
    "availableDocks": 6,
    "availableBikes": 5
  },
  {
    "stationId": 608,
    "lat": 59.93068679863987,
    "lon": 10.746273349149249,
    "name": "Colletts gate",
    "address": "Colletts/Diriks gate",
    "description": "",
    "availableDocks": 0,
    "availableBikes": 19
  },
  {
    "stationId": 400,
    "lat": 59.919927,
    "lon": 10.735428,
    "name": "OsloMet",
    "address": "Pilestredet 35",
    "description": "",
    "availableDocks": 6,
    "availableBikes": 17
  },
  {
    "stationId": 1755,
    "lat": 59.91118372188379,
    "lon": 10.730034556850455,
    "name": "Aker Brygge 1 mot Nasjonalmuseet",
    "address": "Aker Brygge",
    "description": "",
    "availableDocks": 21,
    "availableBikes": 12
  },
  {
    "stationId": 411,
    "lat": 59.923240042155456,
    "lon": 10.792831438028703,
    "name": "Hasle Torg",
    "address": "Hasle Torg, Grenseveien 54",
    "description": "",
    "availableDocks": 0,
    "availableBikes": 11
  },
  {
    "stationId": 620,
    "lat": 59.92377440938114,
    "lon": 10.734712874574,
    "name": "Bislettgata",
    "address": "Bislettgata",
    "description": "",
    "availableDocks": 17,
    "availableBikes": 23
  },
  {
    "stationId": 425,
    "lat": 59.932792,
    "lon": 10.734457,
    "name": "Adamstuen",
    "address": "Adamstuen",
    "description": "",
    "availableDocks": 1,
    "availableBikes": 6
  },
  {
    "stationId": 526,
    "lat": 59.9138973,
    "lon": 10.7423101,
    "name": "Lille Grensen",
    "address": "Lille Grensen - Akersgata 43",
    "description": "",
    "availableDocks": 7,
    "availableBikes": 8
  },
  {
    "stationId": 501,
    "lat": 59.935995,
    "lon": 10.765413,
    "name": "Krebs' gate",
    "address": "Vogstgate 60",
    "description": "",
    "availableDocks": 2,
    "availableBikes": 15
  }
  ]"""

  import ujson.*

  def bikes: Vector[BikeStation] =
    upickle.default.read(data)

