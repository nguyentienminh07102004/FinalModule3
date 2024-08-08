<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@include file="/commons/taglib.jsp"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm thể loại</title>
</head>
<body>
<div class="row ml-4 mt-4">
    <form class="form-horizontal col-12" role="form" method="POST" id="formSubmit">
        <input name="id" type="hidden" value="${category.id}">
        <div class="form-group">
            <div class="col-12">
                <label for="name" class="col-3">Tên thể loại</label>
                <input id="name" type="text" name="name" class="col-12" value="${category.name}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-12">
                <c:if test="${not empty category.id}">
                    <button type="submit" class="btn btn-success" id="buttonSubmit">Sửa thể loại</button>
                </c:if>
                <c:if test="${empty category.id}">
                    <button type="submit" class="btn btn-success" id="buttonSubmit">Thêm thể loại</button>
                </c:if>
            </div>
        </div>
    </form>
</div>
<script>
    document.querySelector("#buttonSubmit").addEventListener("click", (evt) => {
        evt.preventDefault();
        const id = document.querySelector("#formSubmit").querySelector("input[name=id]").value;
        if(id == null || id === "") {
            document.querySelector("#formSubmit").action = "/categories/create";
        } else {
            document.querySelector("#formSubmit").action = "/categories/update";
        }
        document.querySelector("#formSubmit").submit();
    });
</script>
</body>
</html>
