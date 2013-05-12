angular.module('app', ['ui.bootstrap']);

function MapController($scope, $window)
{
  $scope.map = null;
  $scope.osm = null;
  $scope.poinLayer = null;
  
  
  $scope.initMap = function()
  {  
    // Create a map
    $scope.map = new OpenLayers.Map("map");
    $scope.osm = new OpenLayers.Layer.OSM();
    $scope.pointLayer = new OpenLayers.Layer.Vector("Point Layer");
    $scope.map.addLayer($scope.osm);
    $scope.map.addLayer($scope.pointLayer);
    $scope.map.zoomToMaxExtent();
    
    // Simulate mouse click
    var clientX = 100;
    var clientY = 100;
    var pixel = new OpenLayers.Pixel(clientX, clientY);
    var position = $scope.map.getLonLatFromPixel(pixel);
    var latitude = position.lat;
    var longitude = position.lon;
    var feature = new OpenLayers.Feature.Vector( new OpenLayers.Geometry.Point(longitude, latitude), 
        { some: 'data' },
        { externalGraphic: 'http://www.openlayers.org/dev/img/marker.png', graphicHeight: 21, graphicWidth: 16 });
    
    $scope.pointLayer.addFeatures(feature);
    
  };  
    
  $scope.mapStyle = function()
  {
    return {
          width: '500px',
          height: '300px'
    };
  };
  
  $scope.mapShow = function()
  {
    $scope.map.updateSize();
    return true;
  };
  
  $scope.initMap();
  
}


