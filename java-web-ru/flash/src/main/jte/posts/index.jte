@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    @if(page.getPosts()!=null)
        @for(var post: page.getPosts())
     <table>
       <thead>
       <tr>
           <th>id</th>
           <th>name</th>
           <th>link</th>
       </tr>
       </thead>
         <tbody>
         <tr>
             <th>${post.getId()}</th>
             <td>${post.getName()}</td>
             <td><a href="${NamedRoutes.postPath(post.getId())}">Link</a></td>
         </tr>
         </tbody>
     </table>
        @endfor
    @endif
<div>
    <a href="${NamedRoutes.rootPath()}"></a>
</div>
`
)
