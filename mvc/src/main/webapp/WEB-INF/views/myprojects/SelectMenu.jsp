<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="Header.jsp" flush="true"/>

<form id="cpaform" name="cpaform" action="" method="post">
${cpafields}
<div class="col30">
    <c:if test="${!empty usertoken}">   
    <div class="myaccountbutton">
      <h3 class="buttontext">My Account</h3>
    </div>
    <div class="purchaseprojectbutton">
      <h3 class="buttontext">Purchase Project</h3>
    </div>
	</c:if>
</div>	
<!-- Include the data here as well -->
<div class="col50">
	<jsp:include page="MyAccount.jsp" flush="true"/>
</div>
<div class="col20"></div>

<jsp:include page="Footer.jsp" flush="true"/>
</form>
