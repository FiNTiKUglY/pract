import axios from "axios"

export async function getBooks() {
    let response = await axios.get(
        process.env.REACT_APP_API_URL + `/books`
    )
    return response.data
}

export async function getBook(id) {
    let response = await axios.get(
        process.env.REACT_APP_API_URL + `/books/${id}`
    )
    return response.data
}

export async function deleteBook(id) {
    let response = await axios.delete(
        process.env.REACT_APP_API_URL + `/books/delete/${id}`
    )
    return response.data
}

export async function addBook(book) {
    try {
        let response = await axios.post(
            process.env.REACT_APP_API_URL + `/books/add`,
            book
        )
        return response.data
    }
    catch (error) {
        alert(error)
    }
}

export async function updateBook(book) {
    let response = await axios.post(
        process.env.REACT_APP_API_URL + `/books/update/${book.id}`,
        book
    )
    return response.data
}