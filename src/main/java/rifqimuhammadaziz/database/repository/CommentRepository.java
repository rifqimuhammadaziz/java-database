package rifqimuhammadaziz.database.repository;

import rifqimuhammadaziz.database.entity.Comment;

import java.util.List;

public interface CommentRepository {

    // insert/create comment
    void insert(Comment comment);

    // select/get single comment by id (return value is Comment)
    Comment findById(Integer id);

    // select/get all comment (return value is Comment)
    List<Comment> findAll();

    // select/get all comment by email
    List<Comment> findAllByEmail(String email);
}
