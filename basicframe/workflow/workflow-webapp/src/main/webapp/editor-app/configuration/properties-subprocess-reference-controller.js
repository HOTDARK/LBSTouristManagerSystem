/*
 * 子流程调用
 */

var WorkflowSubprocessReferenceCtrl = [ '$scope', '$modal','$http', function($scope, $modal,$http) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/subprocess-reference-popup.html?version=' + Date.now(),
        scope: $scope
    };
    

    
    $modal(opts);
   
}];

var WorkflowSubprocessDisplayCtrl = ['$scope','$http',function($scope,$http){
	window.mk = $scope;
	if($scope.property.value){
	    $http({
	    	method:'POST',
	    	url:KISBPM.URL.getProcessName(),
	    	data:{process_id:$scope.property.value},
		    ignoreErrors: true,
	        headers: {'Accept': 'application/json',
	                  'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
	        transformRequest: function (obj) {
	            var str = [];
	            for (var p in obj) {
	                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	            }
	            return str.join("&");
	        },
	    }).success(function(data){
	    	if(data){
	    		$scope.subprocessName = data.name;	
	    	}
	    });
	}
	
}];

var WorkflowSubprocessReferencePopupCtrl = ['$scope', '$q', '$translate','$http', function($scope, $q, $translate,$http) {

    if ($scope.property.value !== undefined && $scope.property.value !== null) {
        $scope.subprocess = $scope.property.value;
    }
    
    $scope.processList = [];
    $http({method: 'GET', url: KISBPM.URL.getAllProcess()}).success(function (data, status, headers, config) {
    	$scope.processList = data;
    });

    // Click handler for save button
    $scope.save = function() {

    	if($scope.subprocess){
            $scope.property.value = $scope.subprocess;
        } else {
            $scope.property.value = null;
        }

        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    $scope.cancel = function() {
        $scope.close();
    };

    // Close button handler
    $scope.close = function() {
        $scope.property.mode = 'read';
        $scope.$hide();
    };

}];