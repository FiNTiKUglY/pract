import axios from "axios"

export async function getAuthors() {
    let data = {}
    await axios.get(
        process.env.REACT_APP_API_URL + `/authors`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function getAuthor(id) {
    let data = {}
    await axios.get(
        process.env.REACT_APP_API_URL + `/authors/${id}`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function deleteAuthor(id) {
    let data = {}
    await axios.delete(
        process.env.REACT_APP_API_URL + `/authors/delete/${id}`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function addAuthor(author) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/authors/add`,
        author
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function updateAuthor(author) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/authors/update/${author.id}`,
        author
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}