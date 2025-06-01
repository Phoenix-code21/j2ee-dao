<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<div class="container justify-content-center">
		<button class="navbar-toggler navbar-toggler-right border-0"
			type="button" data-toggle="collapse" data-target="#navbar9">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div
			class="collapse navbar-collapse text-center justify-content-center"
			id="navbar9">
			<ul class="navbar-nav">
				<li class="nav-item mx-2"><a class="nav-link"
					href="<%=request.getContextPath()%>/app/dashboard/register">Criar</a>
				</li>
				<li class="nav-item mx-2"><a
					class="nav-link navbar-brand mr-0 text-white" href="<%=request.getContextPath()%>/app/dashboard"><i
						class="fa d-inline fa-lg fa-stop-circle-o"></i> <b>Dashboard</b></a></li>
				<li class="nav-item mx-2"><a class="nav-link"
					href="<%=request.getContextPath()%>/app/dashboard/edit">Editar/Remover</a>
				</li>
			</ul>
		</div>
	</div>
</nav>