<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
			crossorigin="anonymous">
		<title>Đăng nhập</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">

					<h2 style="text-align:center">ĐĂNG NHẬP</h2>
					<form method="post" action="post-login">
						<fieldset>
							<input class="form-control" type="text" name="tendangnhap"
								placeholder="Tên đăng nhập" required><br> 
							<input class="form-control" type="password" name="matkhau"
								placeholder="Mật khẩu" required><br>

								<s:if test="hasActionMessages()">
									<div class="welcome">
										<s:actionmessage />
									</div>
								</s:if>

							<input class="btn btn-primary" type="submit" value="Đăng nhập">
						</fieldset>
					</form>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</body>
</html>
