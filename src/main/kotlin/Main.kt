fun main(args: Array<String>) {
    NoteServiceImpl.add(Note(12,1,"Заметка","Скоро НГ",221221,1,12,null,false))
    println(NoteServiceImpl.notes.joinToString ())

}

object NoteServiceImpl : NoteAndCommentService<Note> {

    var notes = mutableListOf(Note())
    private var noteId: Int = 1

    override fun add(note: Note): Note {
        notes.add(note.copy(id = noteId))
        noteId++
        return notes.last()
    }

    override fun delete(id: Int) {
        notes.forEach { note ->
            if (id == note.id) note.deleted = true
        }
    }

    override fun edit(noteId: Int, newNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id) {
                notes[index] = note.copy(
                    id = note.id, ownerId = note.ownerId, title = newNote.title,
                    text = newNote.text
                )
                return true
            }
        }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun read(ownerId: Int): List<Note> {
        notes.forEach { note ->
            if (note.ownerId == ownerId) return notes
        }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun getById(id: Int): Note {
        notes.forEach { note ->
            if (note.id == id) return note
        }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun restore(id: Int) {
        notes.forEach { note ->
            if (note.id == id && note.deleted == true) note.deleted = false
        }
        throw NoteNotFoundException("Заметка не найдена")
    }
}

object CommentServiceImpl : NoteAndCommentService<Comment> {
    var comments = mutableListOf(Comment())

    override fun add(comment: Comment): Comment {
        comments.add(comment)
        return comments.last()
    }

    override fun delete(id: Int) {
        comments.forEach { comment ->
            if (id == comment.id) comment.deleted = true
        }
    }

    override fun edit(id: Int, newComment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (id == comment.id) {
                    comments[index] = comment.copy(
                        idOfNote = comment.idOfNote, id = comment.id,
                        deleted = false, text = comment.text
                    )
                    return true
                }
            }
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun read(noteId: Int): List<Comment> {
        comments.forEach { comment ->
            if (comment.idOfNote == noteId) return comments
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun getById(id: Int): Comment {
        comments.forEach { comment ->
            if (comment.id == id) return comment
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun restore(id: Int) {
        comments.forEach { comment ->
            if (comment.id == id && comment.deleted == true) comment.deleted = false
        }
        throw CommentNotFoundException("Комментарий не найден")
    }
}

