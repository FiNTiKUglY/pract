import axios from "axios"

export async function getBooks() {
    let response = await axios.get(
        "http://localhost:8080/api/books"
    )
    return response.data
}

export async function deleteBook(id) {
    let response = await axios.delete(
        'http://localhost:8080/api/books/delete/' + id
    )
    return response.data
}