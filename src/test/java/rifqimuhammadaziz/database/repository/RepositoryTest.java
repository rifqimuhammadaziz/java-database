package rifqimuhammadaziz.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rifqimuhammadaziz.database.entity.Comment;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("xenosty@test.com", "hello, im xenosty");
        commentRepository.insert(comment);

        // id cannot be null (make sure the id is not null)
        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment commentFound = commentRepository.findById(102); // get comment where id 102
        Assertions.assertNotNull(commentFound); // the result of id cannot be null
        System.out.println(commentFound.getId());
        System.out.println(commentFound.getEmail());
        System.out.println(commentFound.getComment());

        Comment commentNotFound = commentRepository.findById(1111);
        Assertions.assertNull(commentNotFound); // the result of id must be null
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        System.out.println(comments.size()); // count data in table
    }

    @Test
    void testFindByEmail() {
        List<Comment> comments = commentRepository.findAllByEmail("xenosty@gmail.com");
        System.out.println(comments.size()); // count data in table
    }
}
