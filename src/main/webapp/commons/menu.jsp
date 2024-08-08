<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list-group">
    <a href="/books/" class="list-group-item list-group-item-action" id="books" aria-current="true">
        Quản lý sách
    </a>
    <a href="/categories/" class="list-group-item list-group-item-action" id="categories">Quản lý thể loại</a>
    <a href="/authors/" class="list-group-item list-group-item-action" id="authors">Quản lý tác giả</a>
</div>

<script>
    let url = new URL(window.location.href);
    const tagId = url.pathname.split("/")[1];
    document.querySelectorAll("a").forEach(item => {
        if(item.id === tagId) item.classList.add("active");
        else item.classList.remove("active");
    })
</script>
