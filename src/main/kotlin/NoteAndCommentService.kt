interface NoteAndCommentService<E> {

    fun add(entity: E): E

    fun delete(id: Int)

    fun edit(id: Int, entity: E): Boolean

    fun read(id: Int): List<E>

    fun getById(i: Int): E

    fun restore(i: Int)
}