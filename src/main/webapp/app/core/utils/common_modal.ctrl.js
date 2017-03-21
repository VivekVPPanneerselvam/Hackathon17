(function() {
	'use strict';
	stimulateApp.controller('commonModalCtrl',commonModalCtrl);
	
	commonModalCtrl.$inject = ['$scope', '$uibModalInstance', 'MODAL', 'modalParam'];
	
	function commonModalCtrl($scope, $uibModalInstance, MODAL, modalParam) {
		
		if(modalParam.secondaryBtnTxt == undefined || modalParam.secondaryBtnTxt === ''){
			$scope.isReq = false;
		}else{
			$scope.isReq = true;
			$scope.secondaryBtnTxt = modalParam.secondaryBtnTxt;
		}
		if(modalParam.modalType == MODAL.TYPES.CONFIRM){
			$scope.modalTitleClass = 'fa fa-check-circle';
			$scope.headerColor = '#00AE4D';
			$scope.buttonTextColor = '#00AE4D';
			$scope.headerTextColor = '#FFFFFF';
			$scope.buttonClass = 'modal-success-button';
			$scope.headerClass = 'modal-success-header';
			$scope.headerStyle = 'color: \'#FFFFFF\'; background-color: \'#00AE4D\';';
		}else if(modalParam.modalType == MODAL.TYPES.INFO){
			$scope.modalTitleClass = 'fa fa-info-circle';
			$scope.headerColor = '#FFC20E';
			$scope.buttonTextColor = '#6D2F06';
			$scope.headerTextColor = '#6D2F06';
			$scope.buttonClass = 'modal-info-button';
			$scope.headerClass = 'modal-info-header';
			$scope.headerStyle = 'color: \'#6D2F06\'; background-color: \'#FFC20E\';';
		}else if(modalParam.modalType == MODAL.TYPES.SUCESS){
			$scope.modalTitleClass = 'fa fa-check-circle';
			$scope.headerColor = '#00AE4D';
			$scope.buttonTextColor = '#00AE4D';
			$scope.headerTextColor = '#FFFFFF';
			$scope.buttonClass = 'modal-success-button';
			$scope.headerClass = 'modal-success-header';
			$scope.headerStyle = 'color: \'#FFFFFF\'; background-color: \'#00AE4D\';';
		}else if(modalParam.modalType == MODAL.TYPES.ERROR){
			$scope.modalTitleClass = 'fa fa-exclamation-circle';
			$scope.headerColor = '#F1292A';
			$scope.buttonTextColor = '#F1292A';
			$scope.headerTextColor = '#FFFFFF';
			$scope.buttonClass = 'modal-error-button';
			$scope.headerClass = 'modal-error-header';
			$scope.headerStyle = 'color: \'#FFFFFF\'; background-color: \'#F1292A\';';
		}else if(modalParam.modalType == MODAL.TYPES.WARN){
			$scope.modalTitleClass = 'fa fa-exclamation-triangle';
			$scope.headerColor = '#FFC20E';
			$scope.buttonTextColor = '#6D2F06';
			$scope.headerTextColor = '#6D2F06';
			$scope.buttonClass = 'modal-info-button';
			$scope.headerClass = 'modal-info-header';
			$scope.headerStyle = 'color: \'#6D2F06\'; background-color: \'#FFC20E\';';
		}
		$scope.modalTitle = modalParam.modalTitle;
		$scope.modalMsg = modalParam.modalMsg;
		$scope.modalMsg2 = modalParam.modalMsg2;
		$scope.modalMsg3 = modalParam.modalMsg3;
		$scope.primaryBtnTxt=modalParam.primaryBtnTxt;
		$scope.secondaryBtnTxt=modalParam.secondaryBtnTxt;
		
		$scope.ok = function() {
			$uibModalInstance.close();
		};
		$scope.cancel = function() {
			$uibModalInstance.dismiss();
		};
	}
})();