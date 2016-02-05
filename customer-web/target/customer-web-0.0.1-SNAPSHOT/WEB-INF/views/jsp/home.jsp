<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Account management Service</title>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>

<body>
	<br>
	<div id="context" align="center">
		<h1>bank account manager</h1>
		<table id="tblAccounts"">
			<tr>
				<td><b>SWIFT</b></td>
				<td><b>IBAN</b></td>

				<td><b>BankId</b></td>
				<td><b>SessionId</b></td>
			</tr>
			<c:forEach items="${action.acounts}" var="account">
				<tr>
					<td><c:out value="${account.swift}" /></td>
					<td><c:out value="${account.iban}" /></td>
					<td><c:out value="${account.corporationID}" /></td>
					<td><c:out value="${action.user.sessionId}" /></td>
					<td><input class="delete" type="button" value="delete"
						data="${account.corporationID}"></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="text" id="iban" name="iban"></td>
				<td><input type="text" id="swift" name="swift"></td>
				<td><input id="create" type="button" id="add" value="add"></td>
				<td><input type="hidden" id="sessionId"
					value="${action.user.sessionId}"></td>
			</tr>

		</table>
	</div>



	<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
</body>
</html>