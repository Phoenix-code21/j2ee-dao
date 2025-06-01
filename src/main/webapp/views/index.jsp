<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>

<div class="py-5 text-center">
	<div class="container">
		<div class="row">
			<div class="mx-auto col-lg-6 col-10">
				<h1>Tela de login</h1>
				<c:if test="${not empty requestScope.error}">
					<div class="alert alert-danger">
						<c:out value="${requestScope.error}"></c:out>
					</div>
				</c:if>
				<form method="POST" action="<%=request.getContextPath()%>/app"
					class="text-left">
					<div class="form-group">
						<label for="user">Usuário</label> <input type="text"
							class="form-control" id="user" name="user"
							placeholder="seu usuário">
					</div>
					<div class="form-group">
						<label for="password">Senha</label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="sua senha">
					</div>
					<button type="submit" class="btn btn-primary">Entrar</button>
				</form>
			</div>
		</div>
	</div>
</div>

<jsp:include page="includes/footer.jsp"></jsp:include>