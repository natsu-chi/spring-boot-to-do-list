<%@ include file="common/header.jspf" %>
<%@ include file="common/navbar.jspf" %>
<div class="container">
    <h1>/add-todo</h1>
    <div>
        <form:form method="post" modelAttribute="todo">
            <fieldset>
                <form:label path="description">Description: </form:label>
                <form:input type="text" path="description" reqired="reqired"/>
                <form:errors path="description" cssClass="text-bg-warning"/>
            </fieldset>

            <fieldset>
                <form:label path="targetDate">Target Date: </form:label>
                <form:input type="text" path="targetDate" required="required"/>
                <form:errors path="targetDate" cssClass="text-warning"/>
            </fieldset>
            <form:input type="hidden" path="id" />
            <form:input type="hidden" path="done" />

            <input type="submit" value="Submit" class="btn btn-primary">
        </form:form>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#targetDate').datepicker({
            format: 'yyyy-mm-dd'
        });
    })
</script>
<%@ include file="common/footer.jspf" %>