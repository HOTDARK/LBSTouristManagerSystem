/*
 * 总结论
 */

var WorkflowConclusionCtrl = [ '$scope', '$modal','$http', function($scope, $modal,$http) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/conclusion-popup.html?version=' + Date.now(),
        scope: $scope
    };
    
    $modal(opts);
   
}];

var WorkflowConclusionPopupCtrl = ['$scope', '$q', '$translate', function($scope, $q, $translate) {

    if ($scope.property.value !== undefined && $scope.property.value !== null) {
        $scope.conclusion = $scope.property.value;
    }else{
    	$scope.conclusion={};
    }

    // Click handler for save button
    $scope.save = function() {

    	if($scope.conclusion){
            $scope.property.value = $scope.conclusion;
        } else {
            $scope.property.value = null;
        }

        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    $scope.cancel = function() {
        $scope.close();
    };
    
    $scope.clear = function(){
    	$scope.conclusion = null;
        $scope.property.value = null;

        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function() {
        $scope.property.mode = 'read';
        $scope.$hide();
    };

}];