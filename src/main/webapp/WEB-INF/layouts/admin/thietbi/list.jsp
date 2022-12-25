<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Device record</title>
	<%@ include file="../../../components/library.jsp" %>
</head>
<body>
	<div class="my-3">
		<%@ include file="../../../components/header.jsp" %>

		<div class="my-3"></div>

		<div class="container">
			<div class="d-flex justify-context-start">
				<a class="badge rounded-pill text-bg-warning text-decoration-none me-2" href="thietbi-create">Thêm</a>
			</div>
		</div>

		<div class="my-3"></div>

		<div class="container">
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
						<th scope="col">Tên thiết bị</th>
						<th scope="col">Trạng thái</th>
						<th scope="col">Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="thietbi" value="listthietbi">
						<tr>
							<td><s:property value="id"/></td>
							<td><s:property value="tenthietbi"/></td>
							<td>
								<s:if test="%{#thietbi.trangthai == 'danghoatdong'}">
									Đang hoạt động
								</s:if>
								<s:else>
									Tắt hoạt động
								</s:else>
							</td>
							<td>
								<a class="badge rounded-pill text-bg-warning text-decoration-none me-2" id="refDulieuthietbi" href='dulieuthietbi-list?idthietbi=<s:property value="id"/>'>Dữ liệu</a>
								<a class="badge rounded-pill text-bg-warning text-decoration-none me-2" id="refSua" href='thietbi-edit?id=<s:property value="id"/>'>Sửa</a>
								<a class="badge rounded-pill text-bg-danger text-decoration-none me-2" id="refXoa" href='thietbi-delete?id=<s:property value="id"/>'>Xóa</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>

	<script>
		function main() {
      var today = moment().format('YYYY-MM-DD');
			document.getElementById("refDulieuthietbi").href += '&fromdate=' + today + '&todate=' + today;
		}

		main();
	</script>
</body>
</html>
