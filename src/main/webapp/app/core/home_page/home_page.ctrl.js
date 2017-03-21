/**
 * 
 */
(function(){
	stimulateApp.controller('homePageCtrl', homePageCtrl);
	homePageCtrl.$inject = ['$location', '$scope'];
	function homePageCtrl($location, $scope){
		$scope.file = document.getElementById("file");
		$scope.upload = upload;
		
		function upload(){
			alert("im in");
			var url = '/iterateSheet?file='+$scope.file.files[0].name;
			$http.get(url).then(function(response){
				console.log(response);
			}, function(){
				alert('Error');
			});
		}
		
	}
})();

