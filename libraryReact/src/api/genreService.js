import axios from "axios"

export async function getGenres() {
    let data = {}
    await axios.get(
        process.env.REACT_APP_API_URL + `/genres`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function getGenre(id) {
    let data = {}
    await axios.get(
        process.env.REACT_APP_API_URL + `/genres/${id}`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function deleteGenre(id) {
    let data = {}
    await axios.delete(
        process.env.REACT_APP_API_URL + `/genres/delete/${id}`
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function addGenre(genre) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/genres/add`,
        genre
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}

export async function updateGenre(genre) {
    let data = {}
    await axios.post(
        process.env.REACT_APP_API_URL + `/genres/update/${genre.id}`,
        genre
    )
    .then((response) => {
        data = response.data
    })
    .catch((error) => {
        console.log(error.message)
    });
    return data;
}