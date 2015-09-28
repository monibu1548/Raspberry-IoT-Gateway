var Data101 = $('#data101');

var Data102 = $('#data102');

var loadingData = function(){
	
	
	$.ajax({
    url: "http://growingdever.cafe24.com:3001/1",
    type: 'GET',
    success: function(res) { 
        Data101.val(res.temp +"          "+res.hum + "          " + res.inuse);
    },
	error: function(res){
		alert("errer 101");
		console.log(res);
	}	
		
});
	
	$.ajax({
    url: "http://growingdever.cafe24.com:3001/2",
    type: 'GET',
    success: function(res) { 
        Data102.val(res.temp +"          "+res.hum + "          " + res.inuse);
    },
	error: function(res){
		alert("error 102");
		console.log(res);
	}
	});
	
	
	
}



