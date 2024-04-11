import axios from "axios"

export async function getBooks() {
    let data = {}
    await axios.get(
        process.env.REACT_APP_API_URL + `/books`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function getBook(id) {
    let data = {}
    await axios.get(
        process.env.REACT_APP_API_URL + `/books/${id}`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function deleteBook(id) {
    let data = {}
    await axios.delete(
        process.env.REACT_APP_API_URL + `/books/delete/${id}`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function addBook(book) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/books/add`,
        book
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function updateBook(book) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/books/update/${book.id}`,
        book
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}