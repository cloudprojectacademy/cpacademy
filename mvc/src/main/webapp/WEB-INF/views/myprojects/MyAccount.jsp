<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<label for="country" class="accountfieldlabel">Country:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<select name="country" id="country">
					<option>Select One</option>
					<option value="USA">United States</option>
					<option value="CAN">Canada</option>					
					<option value="IND">India</option>
				</select>
			</li>
			<li class="myaccount-empdetails-form-li">
				<label for="stateOrProvince" class="accountfieldlabel">State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<select name="stateOrProvince" id="stateOrProvince">
					<option>Select One</option>
				</select>
			</li>
			<li class="savechanges-btn"><a href="javascript:void(0)" class="button_green" id="savechanges">Save Changes</a></li>
		</ul>
	</div>
	<div id="country_state_province_data" style="display:none">{"countryStateProvinceData":[{"country":"USA","stateOrProvince":[{"key":"AL","value":"Alabama"},{"key":"AK","value":"Alaska"},{"key":"AZ","value":"Arizona"},{"key":"AR","value":"Arkansas"},{"key":"AE","value":"Armed Forces"},{"key":"AP","value":"Armed Forces Pacific"},{"key":"CA","value":"California"},{"key":"CO","value":"Colorado"},{"key":"CT","value":"Connecticut"},{"key":"DE","value":"Delaware"},{"key":"DC","value":"District of Columbia"},{"key":"FL","value":"Florida"},{"key":"GA","value":"Georgia"},{"key":"GU","value":"Guam"},{"key":"HI","value":"Hawaii"},{"key":"ID","value":"Idaho"},{"key":"IL","value":"Illinois"},{"key":"IN","value":"Indiana"},{"key":"IA","value":"Iowa"},{"key":"KS","value":"Kansas"},{"key":"KY","value":"Kentucky"},{"key":"KL","value":"Kerala"},{"key":"LA","value":"Louisiana"},{"key":"ME","value":"Maine"},{"key":"MD","value":"Maryland"},{"key":"MA","value":"Massachusetts"},{"key":"MI","value":"Michigan"},{"key":"MN","value":"Minnesota"},{"key":"MS","value":"Mississippi"},{"key":"MO","value":"Missouri"},{"key":"MT","value":"Montana"},{"key":"NE","value":"Nebraska"},{"key":"NV","value":"Nevada"},{"key":"NH","value":"New Hampshire"},{"key":"NJ","value":"New Jersey"},{"key":"NM","value":"New Mexico"},{"key":"NY","value":"New York"},{"key":"NC","value":"North Carolina"},{"key":"ND","value":"North Dakota"},{"key":"NA","value":"Not Applicable"},{"key":"OH","value":"Ohio"},{"key":"OK","value":"Oklahoma"},{"key":"OR","value":"Oregon"},{"key":"PA","value":"Pennsylvania"},{"key":"PR","value":"Puerto Rico"},{"key":"RI","value":"Rhode Island"},{"key":"SC","value":"South Carolina"},{"key":"SD","value":"South Dakota"},{"key":"TN","value":"Tennessee"},{"key":"TX","value":"Texas"},{"key":"UT","value":"Utah"},{"key":"VT","value":"Vermont"},{"key":"VI","value":"Virgin Islands"},{"key":"VA","value":"Virginia"},{"key":"WA","value":"Washington"},{"key":"WV","value":"West Virginia"},{"key":"WI","value":"Wisconsin"},{"key":"WY","value":"Wyoming"}]},{"country":"IND","stateOrProvince":[{"key":"IN-AP","value":"Andhra Pradesh"},{"key":"IN-AR","value":"Arunachal Pradesh"},{"key":"IN-AS","value":"Assam"},{"key":"IN-BR","value":"Bihar"},{"key":"IN-CT","value":"Chhattisgarh"},{"key":"IN-GA","value":"Goa"},{"key":"IN-GJ","value":"Gujarat"},{"key":"IN-HR","value":"Haryana"},{"key":"IN-HP","value":"Himachal Pradesh"},{"key":"IN-JK","value":"Jammu and Kashmir"},{"key":"IN-JH","value":"Jharkhand"},{"key":"IN-KA","value":"Karnataka"},{"key":"IN-KL","value":"Kerala"},{"key":"IN-MP","value":"Madhya Pradesh"},{"key":"IN-MH","value":"Maharashtra"},{"key":"IN-MN","value":"Manipur"},{"key":"IN-ML","value":"Meghalaya"},{"key":"IN-MZ","value":"Mizoram"},{"key":"IN-NL","value":"Nagaland"},{"key":"IN-OR","value":"Odisha"},{"key":"IN-PB","value":"Punjab"},{"key":"IN-RJ","value":"Rajasthan"},{"key":"IN-SK","value":"Sikkim"},{"key":"IN-TN","value":"Tamil Nadu"},{"key":"IN-TG","value":"Telangana"},{"key":"IN-TR","value":"Tripura"},{"key":"IN-UT","value":"Uttarakhand"},{"key":"IN-UP","value":"Uttar Pradesh"},{"key":"IN-WB","value":"West Bengal"},{"key":"IN-AN","value":"Andaman and Nicobar Islands"},{"key":"IN-CH","value":"Chandigarh"},{"key":"IN-DN","value":"Dadra and Nagar Haveli"},{"key":"IN-DD","value":"Daman and Diu"},{"key":"IN-DL","value":"Delhi"},{"key":"IN-LD","value":"Lakshadweep"},{"key":"IN-PY","value":"Puducherry"}]},{"country":"CAN","stateOrProvince":[{"key":"AB","value":"Alberta"},{"key":"BC","value":"British Columbia"},{"key":"MB","value":"Manitoba"},{"key":"NB","value":"New Brunswick"},{"key":"NF","value":"Newfoundland"},{"key":"NT","value":"Northwest Territory"},{"key":"NS","value":"Nova Scotia"},{"key":"NU","value":"Nunavut"},{"key":"ON","value":"Ontario"},{"key":"PE","value":"Prince Edward Island"},{"key":"QC","value":"Quebec"},{"key":"SK","value":"Saskatchewan"},{"key":"YT","value":"Yukon Territory"}]}]}</div>
