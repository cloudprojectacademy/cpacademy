$("#signin").click(function(){	
    if($('#username').val() == null || $('#username').val().length == 0 || $('#password').val() == null || $('#password').val().length == 0){
		$('#error_msg').html('Email and password is required for login');
		return;
	}
    $('#cpa_form').submit();
});

$("#join_cpa_btn").click(function(){	
});

// This function will be invoked when you change the Country drop down so that we populate the state/province data
$('#country').change(function() {
	$('#stateOrProvince > option').remove();
	var data = JSON.parse($('#country_state_province_data').html());
    $.each(data.countryStateProvinceData, function(i, countryStateProvinceData) {
		if($('#country').val() == countryStateProvinceData.country){
			var stateOrProvinceArray = countryStateProvinceData.stateOrProvince;
			$.each(stateOrProvinceArray, function(i, stateOrProvince) {
				$('#stateOrProvince').append($('<option></option>').val(stateOrProvince.key).html(stateOrProvince.value));
			});	
		}
    });
});

