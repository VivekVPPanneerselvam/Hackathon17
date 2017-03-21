/**
 * 
 */
var stimulateApp;
(function(){
//	Declare app level module which depends on views, and components
	stimulateApp = angular.module('stimulateApp', ['ui.bootstrap', 'ngRoute', 'ui.select', 'blockUI', 'ngAnimate', 'ngSanitize']);

	stimulateApp.config(['$routeProvider',function ($routeProvider) {
		$routeProvider.when('/', {
			templateUrl: 'app/core/home_page/home_page.html',
			controller: 'homePageCtrl'
		}).when('/cart',{
			templateUrl: 'app/core/store_cart/store_cart.html',
			controller: 'storeCartCtrl' 
		}).otherwise({
			redirectTo: '/'
		});
	}]);

	stimulateApp.config(['blockUIConfig',function (blockUIConfig) {
		blockUIConfig.template = '<div class=\"block-ui-overlay\"></div><div class=\"block-ui-message-container\" aria-live=\"assertive\" aria-atomic=\"true\"><div ng-class=\"$_blockUiMessageClass\"><img src="images/loading_orange.gif"/></div></div>';
		// Tell the blockUI service to ignore certain requests
		blockUIConfig.requestFilter = function (config) {
			if (config.url.match('/getAppSuggestions')) {
				console.log('url = ' + config.url);
				return false; // 
			}
		}; 
	}]);


	stimulateApp.controller('appCtrl',['$scope', '$uibModal',function ($scope, $uibModal){
		var mainCtrl = this;
	}]);

	stimulateApp.service('commonDataService', function(){
		this.selectedAppId = undefined;
	});

	stimulateApp.directive('input', function() {
		return {
			restrict: 'E',
			require: '?ngModel',
			link: function(scope, elm, attr, ctrl) {
				if (!ctrl) {
					return;
				}

				elm.bind('focus', function () {
					elm.addClass('has-focus');

					scope.$apply(function () {
						ctrl.hasFocus = true;
					});
				});

				elm.bind('blur', function () {
					elm.removeClass('has-focus');
					elm.addClass('has-visited');

					scope.$apply(function () {
						ctrl.hasFocus = false;
						ctrl.hasVisited = true;
					});
				});

				if (attr.type == 'text' && attr.ngPattern === '/[0-9]/') {
					elm.bind('keyup',function (){
						var text = this.value;
						console.log(text);
						this.value = text.replace(/[a-zA-Z]/g,'');
					});
				}  
			}
		};
	});


})();