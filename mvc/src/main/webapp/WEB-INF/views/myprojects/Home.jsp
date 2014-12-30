<%@ page session="false" %>

<jsp:include page="Header.jsp" flush="true"/>
<div class="home_left">
	<div class="form-wrap">About us</div>
</div>	
<div class="signup-page">
	<div class="form-wrap">
	    <h3 class="error" id="error_msg"></h3>
		<form method="post" id="cpa_form" name="cpa_form" action="/cpacademy/pacademyhome">
		<input type="text" name="username" id="username" placeholder="Email" value="" tabindex="1">
		<div class="forgot">
			<span> <a href="/forgot">Forgot?</a> </span>
		</div>
		<input type="password" name="password" id="password" placeholder="Password" tabindex="2">
		<a href="javascript:void(0)" class="button_green" id="signin">Sign in to your account</a>
		<div class="check"> <input type="checkbox" name="remember" value="1"> Remember me </div>
		<input type="submit" name="submitter" value="submit" style="display: none;">
		</form>
	</div>
</div>
<div class="home_right">
	<div class="form-wrap">How does this work?</div>
</div>

<jsp:include page="Footer.jsp" flush="true"/>