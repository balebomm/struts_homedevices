<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Device</title>
  <%@ include file="../../components/library.jsp" %>
</head>

<body>
  <div class="my-3">
    <%@ include file="../../components/header.jsp" %>

    <div class="my-3"></div>

    <div class="container">
      <%@ include file="../../components/device_card.jsp" %>
    </div>
  </div>
</body>
</html>
