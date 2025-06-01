<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="includes/header.jsp"></jsp:include>
<jsp:include page="includes/navbar.jsp"></jsp:include>
<div class="py-5 text-center">
	<div class="container">
		<div class="row">
			<div class="mx-auto col-lg-6 col-10">
				<h1>Cadastro de usuário</h1>
				<p class="mb-3">Preencha o formulário abaixo para cadastrar o
					usuário</p>
				<c:if test="${not empty requestScope.error}">
					<div class="alert alert-danger">
						<c:out value="${requestScope.error}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty requestScope.success}">
					<div class="alert alert-success">
						<c:out value="${requestScope.success}"></c:out>
					</div>
				</c:if>
				<form action="<%=request.getContextPath()%>/app/dashboard/register"
					method="POST" class="text-left">
					<div class="form-group">
						<label for="user">Usuário: </label> <input type="text"
							class="form-control" id="user" name="user" placeholder="Usuário">
					</div>
					<div class="form-group">
						<label for="password">Senha: </label> <input type="text"
							class="form-control" id="password" name="password"
							placeholder="Senha">
					</div>
					<button type="submit" class="btn btn-success">Cadastrar</button>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="includes/footer.jsp"></jsp:include>