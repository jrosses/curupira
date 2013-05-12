var app = angular.module("app", ["ngResource"])
  .constant("apiUrl", "http://curupira.herokuapp.com\/api")
//  .constant("apiUrl", "http://localhost:9000\:9000/api")
	.config(["$routeProvider", function($routeProvider) {
      // WARNING!
      // Never use a route starting with "/views/" or "/api/" or "/assets/"
      // For templateUrl, always start with "/views/"
      return $routeProvider.when("/", {
        templateUrl: "/views/index",
        controller: "AppCtrl"
      }).when("/page1", {
        templateUrl: "/views/page1"
      }).when("/login", {
        templateUrl: "/views/login"
      }).when("/dashboard", {
      templateUrl: "/views/dashboard"
      }).when("/page2", {
        templateUrl: "/views/page2"
      }).when("/colors/:id", {
        templateUrl: "/views/color",
        controller: "ColorCtrl"
      }).when("/users", {
        templateUrl: "/views/users",
        controller: "UserCtrl"
      }).when("/users/:id", {
        templateUrl: "/views/user",
        controller: "UserCtrl"
      }).when("/routing/public1", {
        templateUrl: "/views/public/public1"
      }).when("/routing/public2", {
        templateUrl: "/views/public/public2"
      }).when("/routing/authenticated", {
        templateUrl: "/views/authenticated/authenticated"
      }).when("/routing/admin1", {
        templateUrl: "/views/admin/admin1"
      }).when("/routing/admin2", {
        templateUrl: "/views/admin/admin2"
      }).otherwise({
        redirectTo: "/"
      });
    }
  ]).config([
    "$locationProvider", function($locationProvider) {
      return $locationProvider.html5Mode(true).hashPrefix("!");
    }
  ]);

// Global controller
// Contains a fake database
app.controller("AppCtrl", ["$scope", function($scope) {
  $scope.db = {
    1: {
      name: "black",
      hex: "000000"
    },
    2: {
      name: "white",
      hex: "FFFFFF"
    }
  };
}]);

app.controller("MapCtrl", ["$scope", "$routeParams", function($scope, $routeParams) {
  // Thanks to scope inheritance, we can access the "db" from the AppCtrl scope
  
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
  
  //
  
}])

app.controller("ColorCtrl", ["$scope", "$routeParams", function($scope, $routeParams) {
  // Thanks to scope inheritance, we can access the "db" from the AppCtrl scope
  $scope.color = $scope.db[$routeParams.id];
  if (!$scope.color) {
    $scope.msg = "There is no color for id "+$routeParams.id;
  } else {
    $scope.msg = undefined;
  }
}])

app.controller("UserCtrl", ["$scope", "$routeParams", "$resource", "apiUrl", function($scope, $routeParams, $resource, apiUrl) {
  var Users = $resource(apiUrl + "/users/:id", {id:"@id"});

  if($routeParams.id) {
    $scope.user = Users.get({id: $routeParams.id}, function() {
      if (!$scope.user.id) {
        $scope.msg = "There is no user for id "+$routeParams.id;
      } else {
        $scope.msg = undefined;
      }
    });
  } else {
    $scope.users = Users.query();
  }
}])
