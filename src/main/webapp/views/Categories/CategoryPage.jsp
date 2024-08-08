<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>Category Page</title>
</head>
<body>
<a href="/categories/create" class="btn btn-success mb-4 mt-4 ml-4">Thêm thể loại</a>
<table class="table table-hover table-row-cell">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${categories}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>
                    <a href="<c:url value='/categories/delete?id=${item.id}' />" class="btn btn-info delete">Xoá</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<ul class="pagination" id="pagination"></ul>
<form action="/categories/" method="GET" id="formSubmit">
    <input type="hidden" value="${category.currentPage}" id="currentPage" name="currentPage" />
    <input type="hidden" value="${category.totalPages}" id="totalPages" name="totalPages" />
    <input type="hidden" value="${category.limit}" id="limit" name="limit" />
</form>
<br>
<c:if test="${not empty message}">
    <h1 style="color: red">Không thể xoá vì thể loại này có liên quan đến các cuốn sách khác ! Vui lòng xoá các cuốn sách liên quan trước !!!</h1>
</c:if>
<script>
    const totalPages = parseInt(document.querySelector("#totalPages").value);
    const currentPage = parseInt(document.querySelector("#currentPage").value);
    const limit = document.querySelector("#limit").value;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 2,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if(page !== currentPage){
                    $("#currentPage").val(page);
                    $("#formSubmit").submit();
                }
            }
        });
    });
</script>
<script>
    const updateBooks = document.querySelectorAll(".delete");
    updateBooks.forEach(item => item.addEventListener("click", (evt) => {
        evt.preventDefault();
        const confirmDelete = confirm("Bạn chắc chắn muốn xoá chứ ?");
        if(confirmDelete) {
            window.location.href = evt.target.href;
        } else {
            window.location.href = "/categories/";
        }
    }))
</script>
</body>
</html>
