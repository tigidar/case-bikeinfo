import 'leaflet/dist/leaflet.css';
import L from 'leaflet'

export class BikeMap {

  constructor(lat,lon,zoomLevel) {
    this.map = L.map('map').setView([lat,lon], zoomLevel);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(this.map);

  }

  showMap(lat,lon,name) {
    console.log('adding: ' + name);
    L.marker([lat, lon]).addTo(map);
    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'block';
    }
  }

  hideMap () {

    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'none';
      }
  }

}

