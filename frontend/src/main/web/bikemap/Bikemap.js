import 'leaflet/dist/leaflet.css';
import L from 'leaflet'

export default class BikeMap {




  constructor(lat, lon, zoomLevel, clickCallbackFn) {
    this.map = L.map('map');
    this.lat = lat;
    this.lon = lon;
    this.zoomLevel = zoomLevel;
    this.tileLayer = 
      L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    this.positionMarker = new L.LayerGroup();
    this.positionMarker.addTo(this.map);

    this.closeByMarkers = new L.LayerGroup([]);
    this.closeByMarkers.addTo(this.map);

    this.map.on('click', (event) => {
      const {lat,lng} = event.latlng;
      clickCallbackFn(lat,lng);
    });

    this.svgIcon = L.divIcon({
      className: 'svg-marker',
      html: '<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5z" fill="#FF5733"/></svg>',
      iconSize: [24, 24],
      iconAnchor: [12, 24],
      popupAnchor: [0, -24]
    });

  }

  showStation(lat,lon,name) {
    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'block';
    }

    this.map.setView([this.lat, this.lon], this.zoomLevel);
    this.tileLayer.addTo(this.map);

    const marker = L.marker([lat,lon], this.svgIcon);
    marker.bindPopup(`<b>${name}</b>`).openPopup();
    this.positionMarker.addLayer(marker);
  }

  showAll(stations) {

    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'block';
    }

    this.map.setView([this.lat, this.lon], this.zoomLevel);
    this.tileLayer.addTo(this.map);

    stations.map ((s) => {
      const {lat,lon,name} = s;
      const marker = L.marker([lat,lon]);
      marker.bindPopup(`<b>${name}</b>`);
      this.closeByMarkers.addLayer(marker);
    });
  }

  clearMap() {
    this.positionMarker.clearLayers();
    this.closeByMarkers.clearLayers();
  }

  hideMap () {
    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'none';
      }
    this.clearMap();
  }

}

