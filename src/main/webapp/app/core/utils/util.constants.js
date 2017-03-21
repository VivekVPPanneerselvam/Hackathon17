/**
 * 
 */
(function(){
	var prototype_data = {
		'MODAL':{
			'DATA' : {
				primaryBtnTxt : 'OK',
				secondaryBtnTxt : '',
				modalType : 'success',
				modalTitle : 'Success',
				modalMsg : 'Operation completed successfully.',
				modalMsg2 : '',
				modalMsg3 : ''
			},
			'OPTIONS' : {
				animation : true,
				templateUrl : 'app/core/utils/common_modal.tmplt.html',
				backdrop : 'static',
				controller : 'commonModalCtrl',
				size : 'md',
				resolve : {}
			},
			'TYPES' : {
				SUCESS : 'success',
				INFO : 'info',
				ERROR : 'error',
				CONFIRM : 'confirmation',
				WARN : 'warning'
			}
		}
	};
	var constant_data = {
		'APP_CONSTANTS' : {
			
		}
	};
	angular.forEach(prototype_data, function(value, key) {
		angular.module('stimulateApp').constant(key, Object.seal(value));
	});
	angular.forEach(constant_data, function(value, key) {
		angular.module('stimulateApp').constant(key, Object.freeze(value));
	});
	
})();