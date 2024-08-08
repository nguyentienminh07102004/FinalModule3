<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>Add Author</title>
</head>
<body>
<div class="row ml-4 mt-4">
    <form class="form-horizontal col-12" role="form" method="POST" id="formSubmit">
        <input name="id" type="hidden" value="${author.id}">
        <div class="form-group">
            <div class="col-12">
                <label for="firstname" class="col-3">Họ</label>
                <input id="firstname" type="text" name="firstname" class="col-12" value="${author.firstname}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-12">
                <label for="lastname" class="col-3">Tên</label>
                <input id="lastname" type="text" name="lastname" class="col-12" value="${author.lastname}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-12">
                <label for="address" class="col-3">Địa chỉ</label>
                <input id="address" type="text" name="address" class="col-12" value="${author.address}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-12">
                <label for="dateOfBirth" class="col-3">Ngày tháng năm sinh</label>
                <input id="dateOfBirth" type="date" name="dateOfBirth" class="col-12" value="${author.dateOfBirth}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-12">
                <div class="col-3"></div>
                <c:if test="${not empty author.id}">
                    <button type="submit" class="btn btn-success" id="buttonSubmit">Sửa tác giả</button>
                </c:if>
                <c:if test="${empty author.id}">
                    <button type="submit" class="btn btn-success" id="buttonSubmit">Thêm tác giả</button>
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
            document.querySelector("#formSubmit").action = "/authors/create";
        } else {
            document.querySelector("#formSubmit").action = "/authors/update";
        }
        document.querySelector("#formSubmit").submit();
    })
</script>
</body>
</html>
