<%@ include file="common/header.jspf" %>
<%@ include file="common/navbar.jspf" %>
<div class="container">
    <h1 class="text-3xl font-bold underline">
        Hello world! ${username}
    </h1>
    <pre>${errorMsg}</pre>
    <form method="post">
        <label id="username">username:
            <input type="text" name="username" />
        </label>
        <br>
        <label id="password">password:
            <input type="password" name="password" />
        </label>
        <br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>