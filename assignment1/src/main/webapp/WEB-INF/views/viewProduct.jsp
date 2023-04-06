<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="container-wrapper">
		<div class="container" >

			<h2>Product Detail</h2>
			<p class="lead"> Here is the detail information of the product </p>
			
			<div class="row">
				<div class="col-md-6">
					<c:choose>
						<c:when test="${product.category eq '가전'}">
							<img src="<c:url value='/resources/images/TV.jpeg' />" alt="TV Image" style="width:80%" />
						</c:when>
						<c:when test="${product.category eq '컴퓨터'}">
							<img src="<c:url value='/resources/images/notebook.jpeg' />" alt="Computer Image" style="width:80%" />
						</c:when>
						<c:when test="${product.category eq '잡화'}">
							<img src="<c:url value='/resources/images/shoes.jpeg' />" alt="Shoes Image" style="width:80%" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value='/resources/images/notebook.jpeg' />" alt="Default Image" style="width:80%" />
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="col-md-6">
					<h3> ${product.name }</h3>
					<p> <strong> Description: </strong> ${product.description} </p>
					<p> <strong> Manufacturer: </strong> ${product.manufacturer} </p>
					<p> <strong> Category: </strong> ${product.category} </p>
					<p> <strong> Price: </strong> ${product.price} 원 </p>
				</div>
			</div>
			
		</div>
	</div>