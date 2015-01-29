	<div class="myaccount-form-wrap">
	    <div class="headertext-label">Create Account</div>
		<ul>
			<li class="myaccount-form-li"><label for="fname" class="accountfieldlabel">First Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" id="fname" name="fname"></li>
			<li class="myaccount-form-li"><label for="lname" class="accountfieldlabel">Last Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" id="lname" name="lname"></li>
			<li class="myaccount-form-li"><label for="email" class="accountfieldlabel">Email:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" id="email" name="email"></li>
			<li class="myaccount-form-li"><label for="cemail" class="accountfieldlabel">Confirm Email:</label><input type="text" id="cemail" name="cemail"></li>
			
			<li class="myaccount-emp-form-li">
				<label for="empstatus" class="accountfieldlabel">Employment Status:</label>
				<select name="empstatus">
					<option>Select One</option>
					<option value="EMP">Employed</option>
					<option value="UEMP">Unemployed</option>
					<option value="STU">Student</option>
				</select>
			</li>
			<li><br></li>
			<li class="myaccount-empdetails-form-li" id="myaccount-empdetails-employed"><label class="accountfieldlabel">Select the country & state where you are currently employed:</label></li>
			<li class="myaccount-empdetails-form-li" id="myaccount-empdetails-student" style="display:none"><label class="accountfieldlabel">Select the country & state where you are currently studying:</label></li>
			<li class="myaccount-empdetails-form-li" id="myaccount-empdetails-student" style="display:none"><label class="accountfieldlabel">Select the country & state where you are currently residing:</label></li>
			<li class="myaccount-empdetails-form-li">
				<label for="empstatus" class="accountfieldlabel">Country:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<select name="empstatus">
					<option>Select One</option>
					<option value="USA">United States</option>
					<option value="CAN">Canada</option>					
					<option value="IND">India</option>
				</select>
			</li>
			<li class="myaccount-empdetails-form-li">
				<label for="empstatus" class="accountfieldlabel">State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<select name="empstatus">
					<option>Select One</option>
					<option value="USA">United States</option>
					<option value="CAN">Canada</option>					
					<option value="IND">India</option>
				</select>
			</li>
			<li class="savechanges-btn"><a href="javascript:void(0)" class="button_green" id="savechanges">Save Changes</a></li>
		</ul>
	</div>
