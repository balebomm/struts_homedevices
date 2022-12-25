<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dữ liệu thiết bị</title>
  <%@ include file="../../../components/library.jsp" %>
</head>
<body>
  <div class="my-3">
    <%@ include file="../../../components/header.jsp" %>

    <div class="my-3"></div>

    <form method="GET" action="dulieuthietbi-list">
      <div class="container">
        <div class="row">
          <div hidden>
            <input name="idthietbi" value='<s:property value="idthietbi"/>'/>
          </div>
          <div class="col-sm-12 col-md-3">
            <label for="fromdate">Từ ngày</label>
            <input class="form-control" autocomplete="off" type="date" required name="fromdate" value='<s:property value="fromdate"/>'/>
          </div>
          <div class="col-sm-12 col-md-3">
            <label>Đến ngày</label>
            <input class="form-control" autocomplete="off" type="date" required name="todate" value='<s:property value="todate"/>'/>
          </div>
          <div class="col-sm-12 col-md-2 d-flex align-items-end">
            <button class="btn btn-primary" type="submit">Tìm kiếm</button>
          </div>
        </div>
    </form>

      <br/>

      <s:if test="hasActionMessages()">
				<div class="welcome">
					<s:actionmessage/>
				</div>
				<br/>
			</s:if>

      <table class="table table-success table-striped">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Giá trị</th>
            <th scope="col">Ngày tạo</th>
            <th scope="col">Thao tác</th>
          </tr>
        </thead>
        <tbody>
          <s:iterator var="dulieuthietbi" value="listdulieuthietbi">
            <tr>
              <td><s:property value="id"/></td>
              <td><s:property value="giatri"/></td>
              <td><s:property value="ngaytao"/></td>
              <td>
                <a class="badge rounded-pill text-bg-danger text-decoration-none me-2" href='dulieuthietbi-delete?id=<s:property value="id"/>'>Xóa</a>
              </td>
            </tr>
          </s:iterator>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
