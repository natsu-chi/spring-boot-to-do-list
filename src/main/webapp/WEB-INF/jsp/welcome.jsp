<%@ include file="common/header.jspf" %>
<%@ include file="common/navbar.jspf" %>
    <div class="container">
        <h1 class="text-3xl font-bold underline">
            Welcome! ${username}
        </h1>
        <div>
            <a href="/list-todos">To-do list</a>
        </div>
        <div>
            <img style="max-height: 90vh" src="https://www.animationmagazine.net/wordpress/wp-content/uploads/Haikyuu-1.jpg" alt="haikyu"/>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>