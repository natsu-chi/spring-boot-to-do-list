<%@ include file="common/header.jspf" %>
<%@ include file="common/navbar.jspf" %>
    <h1>/update-todo</h1>
    <div>
        <form:form method="post" modelAttribute="todo">
            description:
            <form:input type="text" path="description" reqired="reqired"/>
            <form:errors path="description" cssClass="text-bg-warning"/>
            <br>

            <form:input type="hidden" path="id" />
            <br>

            <form:input type="hidden" path="targetDate" />
            <br>

            <form:input type="hidden" path="done" />
            <br>

            <input type="submit" value="Submit" class="btn btn-primary">
        </form:form>
    </div>
</div>
<%@ include file="common/footer.jspf" %>