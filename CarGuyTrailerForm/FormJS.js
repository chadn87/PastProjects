/////////////////////////////////////////////////////////////////////////////////
//Car guy trailer cost form JavaScript
//Author:Chad Nelson
//Last Update:02/16/2014
/////////////////////////////////////////////////////////////////////////////////

//make sure json doc is loading correctly
//console.log(trailer);

//globals for creating select options
//used so choices can be sorted automatically
var brands=[];
var models=[];
var modelPrice=[];
var shipping = 0;
var trailerPrice = 0;
var option_index = 0;
var display_options= [];

//globals for google map api
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

//alert for public version
alert("Prices do not reflect actual costs");

//Start of google map api(used to calculate shipping info)
////////////////////////////////////////////////////////////////////////////////////
function initialize() {
  directionsDisplay = new google.maps.DirectionsRenderer();
  var shop = new google.maps.LatLng(38.821911, -90.502877);
  var mapOptions = {
    zoom:9,
	center: shop,
	mapTypeId: google.maps.MapTypeId.STREET
  }
  map = new google.maps.Map(document.getElementById('map'), mapOptions);
  directionsDisplay.setMap(map);
  
}

//Main program start
$(document).ready(function(){

function calcRoute() {
  var start = document.getElementById('start').value;
  var end = document.getElementById('end').value;
  var request = {
      origin:start,
      destination:end,
	  unitSystem: google.maps.UnitSystem.IMPERIAL,
      travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
		computeTotalDistance(directionsDisplay.getDirections());
		displayShipping();
		displayTotal();
	  //alert(directionsDisplay.distance);
    }
  });
}

function computeTotalDistance(result) {
  var myroute = result.routes[0];
  for (i = 0; i < myroute.legs.length; i++) {
    shipping += myroute.legs[i].distance.value;
  }
  shipping = shipping/1000; //convert meters to km
  shipping = shipping * .6214; //then km to miles
}

////////////////////////////////////////////////////////////////////////////////////



//Function to create the printable view of the order
////////////////////////////////////////////////////////////////////////////////////
var print = function(html){
var printWindow = window.open();
printWindow.document.write('<html><head><title>Customer Order</title><link rel="stylesheet" type="text/css" href="FormCSS.css"></head><body>');
printWindow.document.write('<html><head><title>Customer Order</title><style>.del_opt{display:none;}</style></head><body>');
printWindow.document.write(html);
return true;
}

////////////////////////////////////////////////////////////////////////////////////

	//event for model generator for brands
	getBrands();

	//event handlers to display results at bottom of form
		$('#name').on('change',function(){
		displayName();
			});	
		$('#phone').on('change',function(){
		displayPhone();
			});	
		$('#email').on('change',function(){
		displayEmail();
			});
		$('#trailer_brand').on('change',function(){
		getModels($('#trailer_brand').val());
		displayBrand();
		displayModel(); 
		displayTotal();
		$('#order_table').slideDown("slow");//Show customer Table
			});
		$('#trailer_model').on('mouseup',function(){
		displayModel();
		displayTotal();
			});
		$('#colorChoice').on('change',function(){
		displayModel();
		});
		$('#end').on('change',function(){
		calcRoute();//displayTotal() gets called in calcRoute()
			});
		$('#start').on('change',function(){
		calcRoute();//displayTotal() gets called in calcRoute()
			});	
		$('#charge').on('change',function(){
		displayCustomerCharge();
		});
	
		//event for add option button
		$("#add_option").click(function(){
			if(validateOptionPrice()){
			addOption(option_index);
			option_index++;
			displayTotal();
			}
		});
		//clear contents of text area on click
		$('#option_price').click(function(event){
			$(this).val('');
			$(this).unbind(event);
		});
		$('#trailer_option').focus(function(event){
			$(this).val('');
			$(this).unbind(event);
		});
		$('#end').click(function(event){
			$(this).val('');
			$(this).unbind(event);
		});
		
			
		//event handler to print to file
		$("#print-button").click(function (){
		print($("#order_table").html())
		});
	
//get the brands of trailers from JSON page and sort them
////////////////////////////////////////////////////////////////////////////
function getBrands(){
for(i=0;i<trailer.trailers.length;i++){
brands[i] = trailer.trailers[i].brand;
}
brands.sort();
brands.reverse();

//assign the brands to the HTML page
for(i=0;i<trailer.trailers.length;i++){
	if(brands[i] != brands[i+1]){
	$('#brand').after("<option>"+brands[i]+"</option>");
	}
  }
} 
////////////////////////////////////////////////////////////////////////////

//get the models of trailers from JSON page and sort them and remove existing models
///////////////////////////////////////////////////////////////////////////////////////
function getModels(selected_Brand){
removeModels();
var z=0;
for(i=0;i<trailer.trailers.length;i++){
  if (selected_Brand == trailer.trailers[i].brand){
	models[z] = trailer.trailers[i].model;
	modelPrice[z] = trailer.trailers[i].price;
	z++;
	 }
  }
	//models.sort(); going to have to use my own sorting algorithm to ensure prices stay with models
	//models.reverse();
//put the models into the html option box
	for(i=0;i<models.length;i++){
	$('#trailer_model').append("<option>"+models[i]+"</option>");
	}

}

//emptys model array and and removes model option tags
function removeModels(){
	models = [];
	modelPrice=[];
	$('#trailer_model').empty();
}	
//////////////////////////////////////////////////////////////////////////////////////


//Functions to get and display customer order information(**rename function)
/////////////////////////////////////////////////////////////////////////////////////
function displayName(){
$('#customer_name > span').empty();
$('#customer_name > span').append($('#name').val());
}

function displayPhone(){
$('#customer_phone > span').empty();
$('#customer_phone > span').append($('#phone').val());
}

function displayEmail(){
$('#customer_email > span').empty();
$('#customer_email > span').append($('#email').val());
}

function displayBrand(){
$('.brand').empty();
$('.brand').append($('#trailer_brand').val());
}

function displayModel(){  //displays model and price
$('.model').empty();
$('.model_price').empty();
var selected_model = $('#trailer_model').val();
$('.model').append($('#colorChoice').val()+" "+selected_model);
	for(i=0;i<models.length;i++){
		if(selected_model == models[i]){
		trailerPrice = parseInt(modelPrice[i]);
		$('.model_price').append("$"+modelPrice[i]);
		}
	}
}

function displayShipping(){
//if shipping is within 200 miles it is free
//shipping is not true cost of company
var destination = $('#end').val();
$('.shipping > span').empty();
$('.ship_price').empty();
$('.shipping > span').append(destination + " " +parseInt(shipping) + " mi");
if (parseInt(shipping) >= 201){
		shipping -= 200;
	$('.ship_price').append("$"+parseInt(shipping));
	}
	else{shipping = 0;
		$('.ship_price').append("Free");}
	
}
function displayTotal(){ // gets total and displays it
$('.total').empty();
var total = getTotal();
$('.total').append("Trailer Cost: $"+ total);
}

function displayCustomerCharge(){
$('.amt_charged').empty();
$('.amt_charged').append("Customer Price: $" +$('#charge').val());
}
///////////////////////////////////////////////////////////////////////////////////////

//functions to get total
///////////////////////////////////////////////////////////////////////////////////
function getTotal() {
var total = 0;
total += parseInt(trailerPrice);
total += parseInt(shipping);
	//calculate option total
	var price = $('.price').text();
	price = price.split("$")
	for(i=1;i<price.length;i++){
	  var temp = price[i];
	  temp = temp.replace(/\D/g,'');//get rid of the dollar sign
	  total += parseInt(temp);
  }
return parseInt(total);
}

//////////////////////////////////////////////////////////////////////////////////////////

//functions to add and remove option dynamically to the order
////////////////////////////////////////////////////////////////////////////////
function addOption(index) {
var opt_price = $('#option_price').val();
		//fill the options array
		display_options.push($("<tr><td class=\"option\" id = \""+index+"\">"+$('#trailer_option').val()+"</td>"
		+"<td class=\"price\" id = \""+index+"\">$"+$( '#option_price').val()+
		" "+"<input type=\"button\" class=\"del_opt\" id = \""+index+"\" value=\"remove\"></input></td><td></td></tr>"));
$('#model_row').after(display_options);			
$('#trailer_option').val(" ");
$('#option_price').val(" ");

//event handler to remove the option, has to be called in this scope to work
	var removeOption = function(){
	var i = $(this).attr('id');
	$('#'+i).remove();//remove option description before calling the price
	var price = $('#'+i).text();
	price = price.replace(/\D/g,'');
	$('#'+i).remove();
	displayTotal();
	}		
$(".del_opt").click(removeOption);
}
///////////////////////////////////////////////////////////////////////////////

//Input Validation functions
///////////////////////////////////////////////////////////////////////////////////

function validateOptionPrice(){
var price = $('#option_price').val();
var reg = /\D/g;//make sure only numbers are typed in
var ok = reg.exec(price);
	if( !ok){
	return true;}
	alert("Only use numbers when entering the price of a option.");
	return false;
}

///////////////////////////////////////////////////////////////////////////////////

});
google.maps.event.addDomListener(window, 'load', initialize);


//this code will be used if this form to get the JSON if it is ever used on-line
////////////////////////////////////////////////////////////////////
//$(document).ready(function(){
	//$.load('TrailerData.js',function(data){
	//var trailers = $.parseJSON(data);
	//});
//});
////////////////////////////////////////////////////////////////////

