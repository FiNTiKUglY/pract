import React, { useState } from 'react';
import { getAuthors } from "../api/authorService"
import { getGenres } from "../api/genreService"
import Select  from "react-select";
import { useNavigate } from "react-router-dom";
import { addBook } from "../api/bookService"

export default function BooksAdd() {
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [shortDescription, setShortDescription] = useState('');
    const [cost, setCost] = useState('');
    const [downloadLink, setDownloadLink] = useState('');
    const [imageLink, setImageLink] = useState('');
    const [author, setAuthor] = useState();
    const [genres, setGenres] = useState();

    let authorsList = [];
    let genresList = [];

    loadAuthors();
    loadGenres();

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Название"
                    className="form-control"
                    type="text"
                    onChange={(e) => setTitle(e.target.value)}
                />
                <Select
                    options={authorsList}
                    placeholder="Автор"
                    value={author}
                    onChange={handleSelectAuthor}
                    isSearchable={true}
                />
                <textarea
                    placeholder="Описание"
                    className="form-control"
                    type="text"
                    onChange={(e) => setShortDescription(e.target.value)}
                />
                <Select
                    options={genresList}
                    placeholder="Жанры"
                    value={genres}
                    onChange={handleSelectGenre}
                    isSearchable={true}
                    isMulti
                />
                <input
                    placeholder="Цена"
                    className="form-control"
                    type="number"
                    onChange={(e) => setCost(e.target.value)}
                />
                <input
                    placeholder="Ссылка загрузки"
                    className="form-control"
                    type="text"
                    onChange={(e) => setDownloadLink(e.target.value)}
                />
                <input
                    placeholder="Ссылка изображения"
                    className="form-control"
                    type="text"
                    onChange={(e) => setImageLink(e.target.value)}
                />
                <button
                    onClick={addBookClick}>
                    Добавить
                </button>
            </div>
        </section>
    );

    function handleSelectAuthor(data) {
        setAuthor(data);
    }

    function handleSelectGenre(data) {
        setGenres(data);
    }

    async function loadAuthors() {
        const authors = await getAuthors()
        authors.map(author =>
            authorsList.push({ value: author, label: `${author.surname} ${author.name}`})    
        )
    }

    async function loadGenres() {
        const genres = await getGenres()
        genres.map(genre =>
            genresList.push({ value: genre, label: genre.name})    
        )
    }

    async function addBookClick() {
        let book = {}
        book.id = crypto.randomUUID();
        book.title = title
        book.shortDescription = shortDescription
        book.cost = cost
        book.author = author.value
        book.genres = genres.map(genre => genre.value)
        book.downloadLink = downloadLink
        book.imageLink = imageLink
        await addBook(book)
        navigate('/books')
    }
}