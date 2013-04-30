'use strict';

/* Services */

angular.module('phonecatServices', ['ngResource']).
    factory('Phone', function($resource){
  return $resource('http://localhost:9000/phones/:phoneId', {}, {
    query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
  });
});
