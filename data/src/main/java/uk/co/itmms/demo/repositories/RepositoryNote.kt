package uk.co.itmms.demo.repositories

import arrow.core.Either
import arrow.core.right
import uk.co.itmms.demo.entities.Note
import java.util.Date

class RepositoryNote : IRepositoryNote {
    override suspend fun list(): Either<NoteFailure, List<Note>> =
        listOf(
            Note(1, "Nota 1", Date(), null),
        ).right()
}