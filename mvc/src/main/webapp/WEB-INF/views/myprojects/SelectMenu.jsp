<%@ page session="false" %>

<jsp:include page="Header.jsp" flush="true"/>

<div class="col30">
    <div class="myaccountbutton">
      <h3 class="buttontext">My Account</h3>
    </div>
    <div class="purchaseprojectbutton">
      <h3 class="buttontext">Purchase Project</h3>
    </div>
</div>	
<!-- Include the data here as well -->
<div class="col50">
	<jsp:include page="MyAccount.jsp" flush="true"/>
</div>
<div class="col20"></div>

<jsp:include page="Footer.jsp" flush="true"/>

