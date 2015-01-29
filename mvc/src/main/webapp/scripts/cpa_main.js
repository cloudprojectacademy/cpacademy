$("#signin").click(function(){	
    if($('#username').val() == null || $('#username').val().length == 0 || $('#password').val() == null || $('#password').val().length == 0){
		$('#error_msg').html('Email and password is required for login');
		return;
	}
    $('#cpa_form').submit();
});

$("#join_cpa_btn").click(function(){	
});

