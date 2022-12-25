<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thêm thiết bi</title>
  <%@ include file="../../../components/library.jsp" %>
</head>
<body>

  <div class="my-3">
    <%@ include file="../../../components/header.jsp" %>

    <div class="my-3"></div>

    <div class="container">
      <h2>Thêm thiết bị</h2>
  
      <form action="thietbi-store" method="POST">
        <fieldset>
          <input class="form-control" type="text" name="tenthietbi" placeholder="Tên thiết bị" required><br>
          <select class="form-select" name="trangthai" aria-label="Default select example">
            <option selected>Trạng thái</option>
            <option value="danghoatdong" selected>Đang hoạt động</option>
            <option value="tathoatdong">Tắt hoạt động</option>
          </select><br/>
          <s:if test="hasActionMessages()">
            <div class="welcome">
              <s:actionmessage/>
            </div>
            <br/>
          </s:if>
          <input class="btn btn-primary" type="submit" value="Xác nhận">
        </fieldset>
      </form>
    </div>
  </div>
  
</body>
</html>
