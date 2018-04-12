/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Input parameters for call activity
 */

var KisBpmOutParametersCtrl = [ '$scope' , '$modal', '$timeout', '$translate', function($scope, $modal, $timeout, $translate) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/out-parameters-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmOutParametersPopupCtrl = [ '$scope', '$q', '$translate', function($scope, $q, $translate) {

    // Put json representing form properties on scope
    if ($scope.property.value) {
        $scope.parameters = angular.copy($scope.property.value);
    } else {
        $scope.parameters = [];
    }
    
    window.mk = $scope;

    // Array to contain selected properties (yes - we only can select one, but ng-grid isn't smart enough)
    $scope.selectedParameters = [];
    $scope.translationsRetrieved = true;
    
    
    $q.all().then(function(results) { 
        $scope.gridOptions = {
            data: 'parameters',
            enableRowReordering: true,
            headerRowHeight: 28,
            multiSelect: false,
            keepLastSelected : false,
            selectedItems: $scope.selectedParameters,
            columnDefs: [{ field: 'key', displayName: "参数名" },
                         { field: 'value', displayName: "值" }
                         ]
        };
    });

    // Click handler for add button
    $scope.addNewParameter = function() {
        $scope.parameters.push({ key : '',
        	value : ''});
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
            $scope.property.value = $scope.parameters;
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