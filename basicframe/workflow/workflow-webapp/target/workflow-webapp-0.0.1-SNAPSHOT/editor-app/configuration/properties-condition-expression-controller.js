/*
 * Condition expression
 */
var KisBpmConditionExpressionCtrl = [ '$scope', '$modal', function($scope, $modal) {

    var opts = {
        template:  'editor-app/configuration/properties/condition-expression-popup.html?version=' + Date.now(),
        scope: $scope
    };

    $modal(opts);
}];


var WorkflowConditionExpressionParamPopupCtrl = ['$scope','$q','$timeout',function($scope,$q,$timeout){
	
    if ($scope.selectedParameters&&$scope.selectedParameters.length>0&&$scope.selectedParameters[0].params) {
        $scope.params = angular.copy($scope.selectedParameters[0].params);
    } else {
        $scope.params = [];
    }
    
    $scope.selectedParams = [];
    
    $scope.paramsGridOptions = {
        data: 'params',
        enableRowReordering: true,
        headerRowHeight: 28,
        multiSelect: false,
        keepLastSelected : false,
        selectedItems: $scope.selectedParams,
        columnDefs: [{ field: 'key', displayName: "名称" },
                     { field: 'value', displayName: "值" },
                    ]
    };
	
    // Click handler for add button
    $scope.addNewParameter = function() {
        $scope.params.push({ key : '',value : '',script:''});
    };

    // Click handler for remove button
    $scope.removeParameter = function() {
        if ($scope.selectedParams.length > 0) {
            var index = $scope.params.indexOf($scope.selectedParams[0]);
            $scope.paramsGridOptions.selectItem(index, false);
            $scope.params.splice(index, 1);

            $scope.selectedParams.length = 0;
            if (index < $scope.params.length) {
                $scope.paramsGridOptions.selectItem(index + 1, true);
            } else if ($scope.parameters.length > 0) {
                $scope.paramsGridOptions.selectItem(index - 1, true);
            }
        }
    };

    // Click handler for up button
    $scope.moveParameterUp = function() {
        if ($scope.selectedParams.length > 0) {
            var index = $scope.params.indexOf($scope.selectedParams[0]);
            if (index != 0) { // If it's the first, no moving up of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.params[index];
                $scope.params.splice(index, 1);
                $timeout(function(){
                    $scope.params.splice(index + -1, 0, temp);
                }, 100);

            }
        }
    };

    // Click handler for down button
    $scope.moveParameterDown = function() {
        if ($scope.selectedParams.length > 0) {
            var index = $scope.params.indexOf($scope.selectedParams[0]);
            if (index != $scope.params.length - 1) { // If it's the last element, no moving down of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.params[index];
                $scope.params.splice(index, 1);
                $timeout(function(){
                    $scope.params.splice(index + 1, 0, temp);
                }, 100);

            }
        }
    };

    $scope.save = function() {

        if ($scope.params.length > 0) {
            $scope.selectedParameters[0].params = $scope.params;
        } else {
        	$scope.selectedParameters[0].params = null;
        }

        $scope.close();
    };

    $scope.cancel = function() {
        $scope.close();
    };

    $scope.close = function() {
        $scope.$hide();
    };
}];

var KisBpmConditionExpressionPopupCtrl = ['$scope', '$q', '$translate','$timeout','$modal', function($scope, $q, $translate,$timeout,$modal) {

    // Put json representing form properties on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.conditions !== undefined
        && $scope.property.value.conditions !== null) {
        // Note that we clone the json object rather then setting it directly,
        // this to cope with the fact that the user can click the cancel button and no changes should have happened
        $scope.parameters = angular.copy($scope.property.value.conditions);
    } else {
        $scope.parameters = [];
    }

    // Array to contain selected properties (yes - we only can select one, but ng-grid isn't smart enough)
    $scope.selectedParameters = [];
    $scope.translationsRetrieved = true;
    
    $scope.labels = {};
    
    $q.all().then(function(results) { 
        // Config for grid
        $scope.gridOptions = {
            data: 'parameters',
            enableRowReordering: true,
            headerRowHeight: 28,
            multiSelect: false,
            keepLastSelected : false,
            selectedItems: $scope.selectedParameters,
            columnDefs: [{ field: 'expression', displayName: "表达式" },
                         { field: 'result', displayName: "结果" },
                         { field: 'code', displayName: "编码" },
                         { field: 'state', displayName: "提示状态",
                        	 cellTemplate:'<div class="ngCellText ng-scope"><span ng-if="row.getProperty(col.field)==\'normal\'">正常</span><span ng-if="row.getProperty(col.field)==\'error\'">异常</span><span ng-if="row.getProperty(col.field)==\'warn\'">警告</span></div>' }
                        ]
        };
    });
    
    $scope.showParamPoup = function(){
    	var opts = {
    			template:  'editor-app/configuration/properties/condition-expression-param-popup.html?version=' + Date.now(),
    			scope: $scope
    	};

	    $modal(opts);
    };

    // Click handler for add button
    $scope.addNewParameter = function() {
        $scope.parameters.push({ expression : '',
        	result : '',
        	code : '',
        	state:''});
    };

    // Click handler for remove button
    $scope.removeParameter = function() {
        if ($scope.selectedParameters.length > 0) {
            var index = $scope.parameters.indexOf($scope.selectedParameters[0]);
            $scope.gridOptions.selectItem(index, false);
            $scope.parameters.splice(index, 1);

            $scope.selectedParameters.length = 0;
            if (index < $scope.parameters.length) {
                $scope.gridOptions.selectItem(index + 1, true);
            } else if ($scope.parameters.length > 0) {
                $scope.gridOptions.selectItem(index - 1, true);
            }
        }
    };

    // Click handler for up button
    $scope.moveParameterUp = function() {
        if ($scope.selectedParameters.length > 0) {
            var index = $scope.parameters.indexOf($scope.selectedParameters[0]);
            if (index != 0) { // If it's the first, no moving up of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.parameters[index];
                $scope.parameters.splice(index, 1);
                $timeout(function(){
                    $scope.parameters.splice(index + -1, 0, temp);
                }, 100);

            }
        }
    };

    // Click handler for down button
    $scope.moveParameterDown = function() {
        if ($scope.selectedParameters.length > 0) {
            var index = $scope.parameters.indexOf($scope.selectedParameters[0]);
            if (index != $scope.parameters.length - 1) { // If it's the last element, no moving down of course
                // Reason for funny way of swapping, see https://github.com/angular-ui/ng-grid/issues/272
                var temp = $scope.parameters[index];
                $scope.parameters.splice(index, 1);
                $timeout(function(){
                    $scope.parameters.splice(index + 1, 0, temp);
                }, 100);

            }
        }
    };

    // Click handler for save button
    $scope.save = function() {

        if ($scope.parameters.length > 0) {
            $scope.property.value = {};
            $scope.property.value.conditions = $scope.parameters;
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