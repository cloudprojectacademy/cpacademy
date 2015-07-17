<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Trtnsitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>CloudProjectAcademy - Login</title>
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="http://54.69.215.212/styles/cpa.css">
		<link rel="stylesheet" href="http://54.69.215.212/styles/cpa-font-awesome.css">
		<link rel="stylesheet" href="http://54.69.215.212/styles/cpa-font-css.css">
		<meta name="viewport" content="width=device-width, user-scalable=no">
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="http://54.69.215.212/scripts/json2_min.js"></script>
	</head>
	
	
	<!-- body starts here -->
	<body class="signup">
	  <div class="header">
		<a href="/"><img src="http://54.69.215.212/images/CPA_Logo.png" alt="logo"></a>
		usertoken: ${usertoken.loggedInUserid}
		<c:if test="${empty usertoken}">
			<a href="javascript:void(0)" class="button_green" id="join_cpa_btn">Join the Project Academy</a>
		</c:if>
	  </div>