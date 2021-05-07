<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Producto</title>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

</head>
<body>
	<div>
		<h2>Editar Producto</h2>
		<form:form action="/producto/update" method="post" modelAttribute="producto">
			<form:hidden path="id"/>
			<form:label path="nombre">Nombre Producto: </form:label>
			<form:input path="nombre" />
			<br>
			<form:label path="descripcion">Descripcion del Producto: </form:label>
			<form:input path="descripcion"  />
				<br>
			<form:label path="precio">Precio Producto: </form:label>
			<form:input path="precio" />
			<br>
			
			<form:label path="categorias">Categoria: </form:label>
			
			<form:select path="categorias">
				<option value="0">Seleccione curso...</option>
				<c:forEach var="categoria" items="${listaCategorias}">
					<option value="<c:out value="${categoria.id}"></c:out>"><c:out value="${categoria.nombre}"></c:out> </option>
				</c:forEach>
			</form:select>
			<input type="submit" value="Modificar">
		</form:form>
		<br>

	</div>
</body>
</html>