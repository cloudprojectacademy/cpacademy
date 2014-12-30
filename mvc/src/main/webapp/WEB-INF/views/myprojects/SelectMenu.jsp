<%@ page session="false" %>

<jsp:include page="Header.jsp" flush="true"/>
<div class="col30">
    <div class="topbutton expandable">
      <h3 class="s3">My Account</h3>
      <h5 class="s2">Your account information.</h5>
    </div>
    <div class="topbutton expandable">
      <h3 class="s3">Purchase Project</h3>
      <h5 class="s2">Select & purchase your project.</h5>
    </div>
</div>	
<div class="col50">
	<div class="logo"><a href="/"><img src="http://54.69.215.212/images/CPA_Logo.png" alt="logo"></a></div>
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
<div class="col20">
	<div class="form-wrap">How does this work?</div>
</div>


