<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>Book Page</title>
</head>
<body>
    <a href="/books/create" class="btn btn-success mb-4 mt-4 ml-4">Thêm sách</a>
    <table class="table table-hover table-row-cell">
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Mô tả ngắn</th>
            <th>Giá</th>
            <th>Thể loại</th>
            <th>Tác giả</th>
            <th>Trạng thái</th>
            <th>Ảnh</th>
            <th>Hành động</th>
        </tr>
        <c:forEach var="item" items="${booksList}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>
                <td>${item.categories}</td>
                <td>${item.authors}</td>
                <td>${item.status}</td>
                <td>
                    <img src="<c:url value='${item.image}' />" alt="${item.name}" width="200px" height="auto" />
                </td>
                <td>
                    <a href="<c:url value='/books/update?id=${item.id}' />" class="btn btn-info updateBook">Sửa</a>
                    <a href="<c:url value='/books/delete?id=${item.id}' />" class="btn btn-danger deleteBook">Xoá</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="/books/" method="GET" id="formSubmit">
        <input type="hidden" value="${book.currentPage}" id="currentPage" name="currentPage" />
        <input type="hidden" value="${book.totalPages}" id="totalPages" name="totalPages" />
        <input type="hidden" value="${book.limit}" id="limit" name="limit" />
    </form>
    <ul class="pagination" id="pagination"></ul>
    <script>
        const updateBooks = document.querySelectorAll(".deleteBook");
        updateBooks.forEach(item => item.addEventListener("click", (evt) => {
            evt.preventDefault();
            const confirmDelete = confirm("Bạn chắc chắn muốn xoá chứ ?");
            if(confirmDelete) {
                window.location.href = evt.target.href;
            } else {
                window.location.href = "/books/";
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
