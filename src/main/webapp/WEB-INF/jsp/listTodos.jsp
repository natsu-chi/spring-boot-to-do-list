<%@ include file="common/header.jspf" %>
<%@ include file="common/navbar.jspf" %>
<div class="container my-2">
    <h1 class="text-3xl font-bold underline">
        Your To-dos
    </h1>
<%--    <div>--%>
<%--        Your Todos: ${todos}--%>
<%--    </div>--%>
    <hr>
    <div class="my-5">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">username</th>
                <th scope="col">description</th>
                <th scope="col">targetDate</th>
                <th scope="col">done</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <th scope="row">${todo.id}</th>
                    <td>${todo.username}</td>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="/delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</a></td>
                    <td><a href="/update-todo?id=${todo.id}" class="btn btn-light">UPDATE</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="my-2">
        <a href="add-todo" class="btn btn-outline-warning">Add Todo</a>
    </div>
<%--    <div class="position-relative">--%>
<%--        <div class="position-absolute top-50 start-50 translate-middle">--%>
<%--            <a href="add-todo" class="btn btn-primary">Add Todo</a>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>
<%@ include file="common/footer.jspf" %>