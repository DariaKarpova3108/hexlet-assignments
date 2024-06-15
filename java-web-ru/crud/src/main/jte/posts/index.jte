@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page  
@param PostsPage numberPage

@template.layout.page(
content = @`
    <div>
    <main>
    <h1>Все посты</h1>
    <table>
        @for(var post : page.getPosts())
            <tr>
                <td>
                    ${post.getId()}
                </td>
                <td>
                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                </td>
                <td>
                    ${post.getBody()}
                </td>
            </tr>
        @endfor
    </table>

        <nav>
            <ul>
                <li>
                    <a href="?page=${page.getPage()>2?1:page.getPage()-1}"> Назад </a>
                    <span aria-hidden="true">&laquo;</span>
                </li>
                <li>
                    <a href="${NamedRoutes.postsPath()}?page=${page.getPage()}">${page.getPage()}</a>
                </li>
                <li>
                    <a href="?page=${page.getPage() + 1}">Вперед</a>
                    <span aria-hidden="true">&raquo;</span>
                </li>
            </ul>
        </nav>
    </main>
    </div>
`
)
