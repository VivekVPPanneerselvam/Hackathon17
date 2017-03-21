/**
 * 
 */
(function(){
	stimulateApp.service('utilFactory', utilFactory);
	utilFactory.$inject = ['$uibModal', 'MODAL'];
	function utilFactory($uibModal, MODAL){
		
		return {
			
			openModal : function(primaryBtnTxt,secondaryBtnTxt,modalType,modalTitle, modalMsg, modalMsg2, modalMsg3){
				MODAL.DATA.primaryBtnTxt = 'OK';
				MODAL.DATA.secondaryBtnTxt = '';
				MODAL.DATA.modalType = modalType;
				MODAL.DATA.modalTitle = modalTitle;
				MODAL.DATA.modalMsg = modalMsg;
				MODAL.DATA.modalMsg2 = modalMsg2;
				MODAL.DATA.modalMsg3 = modalMsg3;
				MODAL.OPTIONS.resolve = {
		    			modalParam : function() {	
		    			    return MODAL.DATA;
		    			}
					}
					return $uibModal.open(MODAL.OPTIONS);	
			},
			success : function(message1, message2, message3){
				MODAL.DATA.primaryBtnTxt = 'OK';
				MODAL.DATA.secondaryBtnTxt = '';
				MODAL.DATA.modalType = MODAL.TYPES.SUCESS;
				MODAL.DATA.modalTitle = 'Success';
				MODAL.DATA.modalMsg = message1;
				MODAL.DATA.modalMsg2 = message2;
				MODAL.DATA.modalMsg3 = message3;
				MODAL.OPTIONS.resolve = {
	    			modalParam : function() {	
	    			    return MODAL.DATA;
	    			}
				}
				return $uibModal.open(MODAL.OPTIONS);
			},
			info : function(message1, message2, message3){
				MODAL.DATA.primaryBtnTxt = 'OK';
				MODAL.DATA.secondaryBtnTxt = '';
				MODAL.DATA.modalType = MODAL.TYPES.INFO;
				MODAL.DATA.modalTitle = 'Information';
				MODAL.DATA.modalMsg = message1;
				MODAL.DATA.modalMsg2 = message2;
				MODAL.DATA.modalMsg3 = message3;
				MODAL.OPTIONS.resolve = {
	    			modalParam : function() {
	    			    return MODAL.DATA;
	    			}
				}
				return $uibModal.open(MODAL.OPTIONS);
			},
			warn : function(message1, message2, message3){
				MODAL.DATA.primaryBtnTxt = 'OK';
				MODAL.DATA.secondaryBtnTxt = '';
				MODAL.DATA.modalType = MODAL.TYPES.WARN;
				MODAL.DATA.modalTitle = 'Warning';
				MODAL.DATA.modalMsg = message1;
				MODAL.DATA.modalMsg2 = message2;
				MODAL.DATA.modalMsg3 = message3;
				MODAL.OPTIONS.resolve = {
	    			modalParam : function() {
	    			    return MODAL.DATA;
	    			}
				}
				return $uibModal.open(MODAL.OPTIONS);
			},
			error : function(message1, message2, message3){
				MODAL.DATA.primaryBtnTxt = 'OK';
				MODAL.DATA.secondaryBtnTxt = '';
				MODAL.DATA.modalType = MODAL.TYPES.ERROR;
				MODAL.DATA.modalTitle = 'Error';
				MODAL.DATA.modalMsg = message1;
				MODAL.DATA.modalMsg2 = message2;
				MODAL.DATA.modalMsg3 = message3;
				MODAL.OPTIONS.resolve = {
	    			modalParam : function() {
	    			    return MODAL.DATA;
	    			}
				}
				return $uibModal.open(MODAL.OPTIONS);
			},
			confirm : function(priBtnTxt, secBtnTxt , title, message1, message2, message3){
				if(priBtnTxt){
					MODAL.DATA.primaryBtnTxt = priBtnTxt;
				}else{
					MODAL.DATA.primaryBtnTxt = 'Yes';
				}
				if(secBtnTxt){
					MODAL.DATA.secondaryBtnTxt = secBtnTxt;
				}else{
					MODAL.DATA.secondaryBtnTxt = 'No';
				}
				if(title){
					MODAL.DATA.modalTitle = title;
				}else{
					MODAL.DATA.modalTitle = 'Confirmation';
				}
				MODAL.DATA.modalType = MODAL.TYPES.CONFIRM;
				MODAL.DATA.modalMsg = message1;
				MODAL.DATA.modalMsg2 = message2;
				MODAL.DATA.modalMsg3 = message3;
				MODAL.OPTIONS.resolve = {
	    			modalParam : function() {
	    			    return MODAL.DATA;
	    			}
				}
				return $uibModal.open(MODAL.OPTIONS);
			}
		};
	}
})();