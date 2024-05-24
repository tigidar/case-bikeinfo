import './style.css'
import 'scalajs:main.js'

window.leafletApi = window.leafletApi || {};
window.leafletApi.showMap = function(lat, lon, name) {
    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'block';
    }

  // Oslo
  var map = L.map('map').setView([59.9139, 10.7522], 13);

  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);

  var marker = L.marker([lat, lon]).addTo(map);

  var circle = L.circle([51.508, -0.11], {
    color: 'red',
    fillColor: '#f03',
    fillOpacity: 0.5,
    radius: 500
  }).addTo(map);

  var polygon = L.polygon([
    [51.509, -0.08],
    [51.503, -0.06],
    [51.51, -0.047]
  ]).addTo(map);

};

window.leafletApi.hideMap = function() {

    var mapElement = document.getElementById('map');
    if (mapElement) {
        mapElement.style.display = 'none';
    }

}
