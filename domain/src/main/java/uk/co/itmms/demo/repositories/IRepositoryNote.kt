package uk.co.itmms.demo.repositories

import arrow.core.Either
import uk.co.itmms.demo.entities.Note

sealed interface NoteFailure

interface IRepositoryNote {

    suspend fun list(): Either<NoteFailure, List<Note>>
}