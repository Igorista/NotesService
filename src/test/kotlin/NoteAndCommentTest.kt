import org.junit.Test
import org.junit.Assert.*

class NoteAndCommentServiceImplTest {

    @Test
    fun addNote() {
        val note1 = Note(id = 14)
        val expected = 1
        val result = NoteServiceImpl.add(note1).id

        assertEquals(expected, result)
    }

    @Test
    fun addComent() {
        val comment1 = Comment(id = 3)
        val expected = 3
        val result = CommentServiceImpl.add(comment1).id

        assertEquals(expected, result)
    }

    @Test
    fun editNote() {
        val note1Corrected = Note(id = 11, title = "newTitle")
        NoteServiceImpl.add(note1Corrected)
        val expected = true
        val result = NoteServiceImpl.edit(1, note1Corrected)

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteNoNote() {
        val note1Corrected = Note(id = 6, title = "newTitle")
        NoteServiceImpl.add(note1Corrected)

        NoteServiceImpl.edit(5, note1Corrected)
    }

    @Test
    fun editComment() {
        val comment3 = Comment(id = 3, idOfNote = 1)
        val commentEdit = Comment(id = 2, idOfNote = 1, text = "newText")
        CommentServiceImpl.add(comment3)
        val expected = true
        val result = CommentServiceImpl.edit(3, commentEdit)

        assertEquals(expected, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentNoComment() {
        val commentEdit = Comment(id = 6, idOfNote = 1, text = "newText")
        CommentServiceImpl.add(commentEdit)
        CommentServiceImpl.edit(7, commentEdit)
    }

    @Test(expected = NoteNotFoundException::class)
    fun restoreNoteNoNote() {
        val noteEdit = Note(id = 6, text = "newText")
        NoteServiceImpl.add(noteEdit)
        NoteServiceImpl.edit(8, noteEdit)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentNoComment() {
        val commentEdit = Comment(id = 2, idOfNote = 1, text = "newText")
        CommentServiceImpl.add(commentEdit)
        CommentServiceImpl.edit(8, commentEdit)
    }
}