<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Xóa thiết bị</title>
  <%@ include file="../../../components/library.jsp" %>
</head>
<body>
  
  <div class="my-3">
    <%@ include file="../../../components/header.jsp" %>

    <div class="my-3"></div>

    <div class="container">
      <h3>Xác nhận xóa thiết bị "<s:property value="tenthietbi"/>"?</h3>
      <div class="d-flex justify-content-start">
        <a type="button" class="badge rounded-pill text-bg-secondary text-decoration-none me-2" href='thietbi-list'>Hủy bỏ</a>
        <a type="button" class="badge rounded-pill text-bg-danger text-decoration-none me-2" href='thietbi-destroy?id=<s:property value="id"/>'>Xác nhận</a>
      </div>
    </div>
  </div>
</body>
</html>
