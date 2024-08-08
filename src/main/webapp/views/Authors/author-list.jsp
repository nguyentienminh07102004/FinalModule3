<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>Author List Page</title>
</head>
<body>
<a href="/authors/create" class="btn btn-success mb-4 mt-4 ml-4">Thêm tác giả</a>
<table class="table table-hover table-row-cell">
    <tr>
        <th>ID</th>
        <th>Họ và tên</th>
        <th>Ngày tháng năm sinh</th>
        <th>Địa chỉ</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="item" items="${authors}">
        <tr>
            <td>${item.id}</td>
            <td>${item.fullName}</td>
            <td>${item.dateOfBirth}</td>
            <td>${item.address}</td>
            <td>
                <a href="<c:url value='/authors/update?id=${item.id}' />" class="btn btn-info update">Sửa</a>
                <a href="<c:url value='/authors/delete?id=${item.id}' />" class="btn btn-danger delete">Xoá</a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="/authors/" method="GET" id="formSubmit">
    <input type="hidden" value="${pagination.currentPage}" id="currentPage" name="currentPage" />
    <input type="hidden" value="${pagination.totalPages}" id="totalPages" name="totalPages" />
    <input type="hidden" value="${pagination.limit}" id="limit" name="limit" />
</form>
<ul class="pagination" id="pagination"></ul><br>
<c:if test="${not empty message}">
    <h1 style="color: red">Không thể xoá vì tác giả này có liên quan đến các cuốn sách khác ! Vui lòng xoá các cuốn sách liên quan trước !!!</h1>
</c:if>
<script>
    const updateBooks = document.querySelectorAll(".delete");
    updateBooks.forEach(item => item.addEventListener("click", (evt) => {
        evt.preventDefault();
        const confirmDelete = confirm("Bạn chắc chắn muốn xoá chứ ?");
        if(confirmDelete) {
            window.location.href = evt.target.href;
        } else {
            window.location.href = "/authors/";
        }
    }))
</script>
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
</body>
</html>

