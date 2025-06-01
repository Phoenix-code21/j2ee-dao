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
				<h1>Editar usuário</h1>
				<p class="mb-3">Selecione o usuário da lista abaixo para editar
					os dados.</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<c:if test="${not empty requestScope.success}">
					<div class="alert alert-success">
						<c:out value="${requestScope.success}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty requestScope.error}">
					<div class="alert alert-danger">
						<c:out value="${requestScope.error}"></c:out>
					</div>
				</c:if>
				<div class="list-group">
					<c:if test="${not empty requestScope.edit}">
						<form method="POST"
							action="<%=request.getContextPath()%>/app/dashboard/edit?<%=request.getQueryString()%>">
							<div class="row">
								<div class="col-md-12 text-left">
									<c:forEach var="u" items="${edit}">
										<div class="form-group">
											<label for="user">Usuário</label> <input type="text"
												class="form-control" id="user" aria-describedby="user"
												name="user" placeholder="Entre com nome de usuário"
												value='<c:out value="${u.user}"></c:out>'>
										</div>
										<div class="form-group">
											<label for="password">Password</label> <input type="password"
												class="form-control" id="password" name="password"
												placeholder="Password"
												value='<c:out value="${u.password}"></c:out>'>
										</div>
									</c:forEach>
									<button type="submit" class="btn btn-primary">Salvar</button>
								</div>
							</div>
						</form>
					</c:if>
					<c:if test="${not empty requestScope.remove}">
						<form
							action="<%=request.getContextPath()%>/app/dashboard/edit?<%=request.getQueryString()%>"
							method="POST">
							<input type="hidden" name="action" value="remove">
							<c:forEach var="u" items="${remove}">
								<div class="row">
									<div class="col-md-12">
										<div class="alert alert-danger">
											<span>Deseja remover o usuário <strong>${u.user}?</strong></span>
										</div>
									</div>
									<div class="col-md-12">
										<button type="submit" class="btn btn-success">Sim</button>
										<a href="<%=request.getContextPath()%>/app/dashboard/edit"
											class="btn btn-danger">Não</a>
									</div>
								</div>
							</c:forEach>
						</form>
					</c:if>
					<c:if test="${not empty requestScope.users}">
						<table class="table table-striped">
							<c:forEach var="u" items="${users}">
								<tr>
									<th><c:out value="${u.user}"></c:out></th>
									<th><a
										href="<%= request.getContextPath() %>/app/dashboard/edit?action=edit&id=${u.id}"
										class="fa-solid fa-pen" title="Editar"></a></th>
									<th><a
										href="<%= request.getContextPath() %>/app/dashboard/edit?action=remove&id=${u.id}"
										class="fa-solid fa-xmark" title="Remover" style="color: red"></a></th>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="includes/footer.jsp"></jsp:include>