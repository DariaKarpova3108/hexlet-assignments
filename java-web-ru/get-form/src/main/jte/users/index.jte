@import exercise.dto.users.UsersPage
@param UsersPage page
@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" name="term" required value="${page.getTerm()}">
        <%--<input type="submit" value="search">--%>
    </form>
    @if(!page.getTerm().isEmpty())
    @for(var user: page.getPage())
            <ul>
                <li> ${user.getFirstName()}</li>
            </ul>
        @endfor
    @endif

`)