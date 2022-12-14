package springbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbackend.model.Blog;
import springbackend.repository.BlogRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService implements IBlogService{
    @Autowired
    private BlogRepository repository;

    @Override
    public Blog addBlog(Blog blog){
        blog.setBlogID(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs(){
        return repository.findAll();
    }

    @Override
    public Optional<Blog> findBlogById(String blogID){
        return repository.findById(blogID);
    }

    @Override
    public Blog findBlogByTitle(String title){
        return repository.findByTitle(title);
    }

    @Override
    public Blog updateBlog(Blog blog){
        Blog existingBlog = repository.findById(blog.getBlogID()).get();
        existingBlog.setContent(blog.getContent());
        existingBlog.setTitle(blog.getContent());
        return repository.save(existingBlog);
    }

    @Override
    public void deleteBlog(String blogID){
        repository.deleteById(blogID);
    }

}
