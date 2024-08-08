<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
    <div class="row ml-4 mt-4">
        <form class="form-horizontal col-12" role="form" method="POST" id="formSubmit" enctype="multipart/form-data">
            <input name="id" type="hidden" value="${book.id}">
            <div class="form-group">
                <div class="col-12">
                    <label for="name" class="col-3">Tên sách</label>
                    <input id="name" type="text" name="name" class="col-12" value="${book.name}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <label for="desc" class="col-3">Mô tả sách</label>
                    <textarea id="desc" type="text" name="description" class="col-12" rows="7" >${book.description}</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <label for="price" class="col-3">Giá sách</label>
                    <input id="price" type="number" name="price" class="col-12" value="${book.price}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <hr> Tác giả <hr>
                    <div class="row">
                        <c:forEach var="item" items="${authorList}">
                            <div class="col-3">
                                <c:if test="${book.authorIds.contains(item.key)}" >
                                    <input id="author_${item.key}" type="checkbox" name="authorIds" value="${item.key}" checked />
                                </c:if>
                                <c:if test="${not book.authorIds.contains(item.key)}" >
                                    <input id="author_${item.key}" type="checkbox" name="authorIds" value="${item.key}" />
                                </c:if>
                                <label for="author_${item.key}">${item.value}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                </div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <hr> Thể loại <hr>
                    <div class="row">
                        <c:forEach var="item" items="${categoryList}">
                            <div class="col-3">
                                <c:if test="${book.categoryIds.contains(item.key)}" >
                                    <input id="category_${item.key}" type="checkbox" name="categoryIds" value="${item.key}" checked />
                                </c:if>
                                <c:if test="${not book.categoryIds.contains(item.key)}" >
                                    <input id="category_${item.key}" type="checkbox" name="categoryIds" value="${item.key}"/>
                                </c:if>
                                <label for="category_${item.key}">${item.value}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                </div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <label for="status" class="col-3">Trạng thái sách</label>
                    <select name="status" id="status" class="form-control">
                        <option value="1" <c:out value='${book.status == 1 ? "selected" : ""}' /> />Hoạt động</option>
                        <option value="2" <c:out value='${book.status == 2 ? "selected" : ""}' /> >Dừng bán</option>
                        <option value="0" <c:out value='${book.status == 0 ? "selected" : ""}' /> >Xoá sách</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <input type="file" class="form-control-file" name="image" accept="image/*"/>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <label for="status" class="col-3"></label>
                    <c:if test="${not empty book.id}">
                        <button type="submit" class="btn btn-success" id="buttonSubmit">Sửa sách</button>
                    </c:if>
                    <c:if test="${empty book.id}">
                        <button type="submit" class="btn btn-success" id="buttonSubmit">Thêm sách</button>
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
            document.querySelector("#formSubmit").action = "/books/create";
        } else {
            document.querySelector("#formSubmit").action = "/books/update";
        }
        document.querySelector("#formSubmit").submit();
    })
</script>
</body>
</html>
