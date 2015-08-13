var tempData101 = $('#temp101');
var humData101 = $('#hum101');
var useData101 = $('#user101');

var btnLight101 = $('#btnLight101');
var btnBeam101 = $('#btnBeam101');
var btnAircon101 = $('#btnAircon101');

var tempData102 = $('#temp102');
var humData102 = $('#hum102');
var useData102 = $('#user102');

var btnLight102 = $('#btnLight102');
var btnBeam102 = $('#btnBeam102');
var btnAircon102 = $('#btnAircon102');

var loadingData = function(){
	
	alert("code1");
	$.get( 
                  "http://localhost/1",
                  { id: "101" },
                  function(data) {
                     alert("gaga");
                  }
		)
}



